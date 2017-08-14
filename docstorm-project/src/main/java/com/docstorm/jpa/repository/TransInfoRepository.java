package com.docstorm.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docstorm.repository.entities.Document;
import com.docstorm.repository.entities.TransInfo;

@Repository
public interface TransInfoRepository extends JpaRepository<TransInfo, Integer> {
	TransInfo findByDocumentAndLanguageCodeStartsWith(Document document, String languageCode);
	
	List<TransInfo> findAllByDocument(Document document);
	
	TransInfo findByLanguageCode(String languageCode);
}
