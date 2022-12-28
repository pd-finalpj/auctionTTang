package com.encore.auction.controller.imagefile.api;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.encore.auction.controller.imagefile.responses.ImageFileDeleteResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileListCreateResponse;
import com.encore.auction.service.imagefile.ImageFileService;
import com.encore.auction.utils.security.Permission;

@RestController
@RequestMapping(value = "/api/image-file", consumes = "multipart/form-data")
public class ImageFileController {

	private final ImageFileService imageFileService;

	public ImageFileController(ImageFileService imageFileService) {
		this.imageFileService = imageFileService;
	}

	@Permission
	@PostMapping
	public ResponseEntity<ImageFileListCreateResponse> storeImageFiles(@RequestHeader("Token") String token,
		@Valid @RequestParam("file") MultipartFile[] files) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(imageFileService.storeAndSaveImageFiles(files));
	}

	@Permission
	@DeleteMapping("/{image-file-id}")
	public ResponseEntity<ImageFileDeleteResponse> deleteImageFile(@PathVariable("image-file-id") Long imageFileId) {
		return ResponseEntity.ok().body(imageFileService.deleteImageFile(imageFileId));
	}

}
