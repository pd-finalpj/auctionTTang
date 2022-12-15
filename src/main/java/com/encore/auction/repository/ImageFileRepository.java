package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.encore.auction.model.imagefile.ImageFile;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
}
