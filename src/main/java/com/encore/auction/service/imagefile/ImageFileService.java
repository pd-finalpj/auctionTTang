package com.encore.auction.service.imagefile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.encore.auction.controller.imagefile.responses.ImageFileCreateResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileDeleteResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileListCreateResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.imagefile.ImageFile;
import com.encore.auction.repository.AuctionItemRepository;
import com.encore.auction.repository.ImageFileRepository;
import com.encore.auction.utils.mapper.ImageFileMapper;

@Service
public class ImageFileService {

	private Path fileStorageLocation;

	private AuctionItemRepository auctionItemRepository;

	private ImageFileRepository imageFileRepository;

	public ImageFileService(AuctionItemRepository auctionItemRepository,
		ImageFileRepository imageFileRepository) {
		this.auctionItemRepository = auctionItemRepository;
		this.imageFileRepository = imageFileRepository;

		String storageLocation = "adsasda";

		this.fileStorageLocation = Paths.get(storageLocation).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (IOException ex) {
			throw new NonExistResourceException(
				"Could not create the directory where the uploaded files will be stored");
		}
	}

	@Transactional
	public ImageFileCreateResponse storeAndSaveImageFile(String auctionItemId, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String contentType = file.getContentType();

		if (file.isEmpty())
			throw new WrongRequestException("File is empty");

		if (fileName.contains(".."))
			throw new WrongRequestException("File name contains invalid path sequence" + fileName);

		if (ObjectUtils.isEmpty(contentType))
			throw new NonExistResourceException("Wrong file is selected: " + fileName + ". Please check the file.");

		if (contentType.equals("jpeg") || contentType.equals("jpg") || contentType.equals("png"))
			throw new WrongRequestException("Please uplaod jpeg, jpg, png files");

		Path targetLocation = this.fileStorageLocation.resolve(fileName).normalize();
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		String newFileName =
			LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
				+ auctionItemId + 1;

		AuctionItem auctionItem = auctionItemRepository.findById(Long.parseLong(auctionItemId))
			.orElseThrow(() -> new NonExistResourceException("The auction item does not exist"));

		ImageFile newImageFile = ImageFileMapper.of()
			.imageFileCreateRequestsToEntity(auctionItem, newFileName, String.valueOf(targetLocation));

		ImageFile savedImageFile = imageFileRepository.save(newImageFile);

		return new ImageFileCreateResponse(savedImageFile.getId(), savedImageFile.getAuctionItem().getId(),
			savedImageFile.getFileName(), contentType, savedImageFile.getFileLocation());
	}

	@Transactional
	public ImageFileListCreateResponse storeAndSaveImageFiles(String auctionItemId, MultipartFile[] files) throws
		IOException {
		if (files.length > 0)
			throw new IllegalArgumentException("Files for upload are not selected");

		List<ImageFileCreateResponse> imageFiles = new ArrayList<>();

		int imageNumber = 1;

		for (MultipartFile file : files) {
			if (!file.isEmpty())
				throw new WrongRequestException("The file " + file.getName() + " is empty");

			ImageFileCreateResponse savedImageFile = storeAndSaveImageFile(auctionItemId, file);

			String newFileName = savedImageFile.getFileName() + imageNumber;

			ImageFileCreateResponse newSavedImageFile = new ImageFileCreateResponse(savedImageFile.getId(),
				savedImageFile.getAuctionItemId(), newFileName, savedImageFile.getFileExtension(),
				savedImageFile.getFileLocation());

			imageFiles.add(newSavedImageFile);

			imageNumber++;
		}
		return new ImageFileListCreateResponse(imageFiles);
	}

	public ImageFileDeleteResponse deleteImageFile(Long imageFileId) {

		ImageFile imageFile = imageFileRepository.findById(imageFileId)
			.orElseThrow(() -> new NonExistResourceException("ImageFile does not exist"));

		File file = new File(imageFile.getFileLocation() + "\\" + imageFile.getFileName());

		if (file.exists())
			file.delete();

		imageFileRepository.delete(imageFile);

		return new ImageFileDeleteResponse(imageFileRepository.findById(imageFile.getId()).isEmpty());
	}
}
