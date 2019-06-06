package com.vtvpmc.InernshipBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtvpmc.InernshipBackend.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
