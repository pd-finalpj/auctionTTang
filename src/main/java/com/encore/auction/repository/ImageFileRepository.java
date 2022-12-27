package com.encore.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.encore.auction.model.imagefile.ImageFile;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
	List<ImageFile> findByAuctionItemId(Long auctionItemId);

	List<ImageFile> findByUrlIn(@Param("urls") List<String> imageUrlList);
}
