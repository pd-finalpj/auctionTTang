package com.encore.auction.service.imagefile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.encore.auction.controller.imagefile.responses.ImageFileDeleteResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileListCreateResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.imagefile.ImageFile;
import com.encore.auction.repository.ImageFileRepository;
import com.encore.auction.utils.mapper.ImageFileMapper;

@Service
public class ImageFileService {

	private final ImageFileRepository imageFileRepository;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.s3.dir}")
	private String dir;

	private final AmazonS3 amazonS3;

	public ImageFileService(ImageFileRepository imageFileRepository, AmazonS3 amazonS3) {
		this.imageFileRepository = imageFileRepository;
		this.amazonS3 = amazonS3;
	}

	@Transactional
	public ImageFileListCreateResponse storeAndSaveImageFiles(MultipartFile[] files) throws
		IOException {
		if (files == null)
			throw new WrongRequestException("files are null");

		if (files.length == 0)
			throw new IllegalArgumentException("Files for upload are not selected");

		List<String> imageFilesUrlList = new ArrayList<>();

		int imageNumber = 1;

		for (MultipartFile file : files) {
			if (file.isEmpty())
				throw new WrongRequestException("The file " + file.getName() + " is empty");

			String contentType = checkValidationAndGetContentType(file);

			String newFileName = createFileName(imageNumber, contentType);

			ImageFile savedImageFile = putImageToS3AndGetImageFile(file, newFileName);

			imageFilesUrlList.add(savedImageFile.getUrl());

			imageNumber++;
		}
		return new ImageFileListCreateResponse(imageFilesUrlList);
	}

	public ImageFileDeleteResponse deleteImageFile(Long imageFileId) {
		ImageFile imageFile = imageFileRepository.findById(imageFileId)
			.orElseThrow(() -> new NonExistResourceException("ImageFile does not exist"));

		File file = new File(imageFile.getUrl());

		if (file.exists())
			file.delete();

		imageFileRepository.delete(imageFile);

		return new ImageFileDeleteResponse(imageFileRepository.findById(imageFile.getId()).isEmpty());
	}

	private String checkValidationAndGetContentType(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String contentType = fileName.substring(fileName.lastIndexOf(".") + 1);

		if (file.isEmpty())
			throw new WrongRequestException("File is empty");

		if (fileName.contains(".."))
			throw new WrongRequestException("File name contains invalid path sequence" + fileName);

		if (ObjectUtils.isEmpty(contentType))
			throw new NonExistResourceException("Wrong file is selected: " + fileName + ". Please check the file.");

		if (!contentType.contains("jpeg") && !contentType.contains("jpg") && !contentType.contains("png"))
			throw new WrongRequestException("Please upload jpeg, jpg, png files");
		return contentType;
	}

	private String createFileName(int number, String contentType) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
			+ number + "." + contentType;
	}

	private ImageFile putImageToS3AndGetImageFile(MultipartFile file, String newFileName) throws IOException {
		ObjectMetadata objMeta = new ObjectMetadata();

		objMeta.setContentLength(file.getInputStream().available());

		objMeta.setContentType(file.getContentType());

		amazonS3.putObject(bucket + dir, newFileName, file.getInputStream(), objMeta);

		String fileUrl = amazonS3.getUrl(bucket + dir, newFileName).toString();

		ImageFile newImageFile = ImageFileMapper.of()
			.imageFileCreateRequestsToEntity(fileUrl);

		return imageFileRepository.save(newImageFile);
	}
}
