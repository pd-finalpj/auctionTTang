package com.encore.auction.controller.imagefile.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.imagefile.requests.ImageFileCreateRequest;
import com.encore.auction.controller.imagefile.responses.ImageFileCreateResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileDeleteResponse;
import com.encore.auction.service.imagefile.ImageFileService;

@RestController
@RequestMapping("/imageFile")
public class ImageFileController {

	private final ImageFileService imageFileService;

	public ImageFileController(ImageFileService imageFileService) { this.imageFileService = imageFileService; }

	@PostMapping("/create")
	public ResponseEntity<ImageFileCreateResponse> createImageFile(@RequestHeader("Token") String token,
		@Valid @RequestBody ImageFileCreateRequest imageFileCreateRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(imageFileService.createImageFile(imageFileCreateRequest, token));
	}

	@DeleteMapping("/{image-file-id}")
	public ResponseEntity<ImageFileDeleteResponse> deleteImageFile(@PathVariable("image-file-id") Long imageFileId, @RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(imageFileService.deleteImageFile(imageFileId, token));
	}

}
