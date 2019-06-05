package com.tvtpmc.InernshipBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvtpmc.InernshipBackend.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
