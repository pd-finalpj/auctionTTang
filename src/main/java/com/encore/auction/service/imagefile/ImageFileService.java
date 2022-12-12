package com.encore.auction.service.imagefile;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.imagefile.requests.ImageFileCreateRequest;
import com.encore.auction.controller.imagefile.responses.ImageFileCreateResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileDeleteResponse;

@Service
public class ImageFileService {
	public ImageFileCreateResponse createImageFile(ImageFileCreateRequest imageFileCreateRequest, String token) {

		return null;
	}

	public ImageFileDeleteResponse deleteImageFile(Long imageFileId, String token) {

		return null;
	}
}
