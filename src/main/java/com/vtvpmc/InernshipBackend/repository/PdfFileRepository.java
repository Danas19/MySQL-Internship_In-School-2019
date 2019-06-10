package com.vtvpmc.InernshipBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtvpmc.InernshipBackend.model.PdfFile;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
	
}
