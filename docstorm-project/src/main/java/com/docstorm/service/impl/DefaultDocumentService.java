package com.docstorm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.docstorm.jpa.repository.BodyRepository;
import com.docstorm.jpa.repository.DocumentRepository;
import com.docstorm.jpa.repository.TagRepository;
import com.docstorm.jpa.repository.TransInfoRepository;
import com.docstorm.repository.entities.Body;
import com.docstorm.repository.entities.Document;
import com.docstorm.repository.entities.Tag;
import com.docstorm.repository.entities.TransInfo;
import com.docstorm.service.DocumentService;
import com.docstorm.service.exception.RuntimeSystemException;
import com.docstorm.service.exception.SystemException;
import com.docstorm.service.exception.SystemException.ErrorCode;

@Service
public class DefaultDocumentService implements DocumentService{

	@Autowired private DocumentRepository documentRepository;
	@Autowired private TagRepository tagRepository;
	@Autowired private BodyRepository bodyRepository;
	@Autowired private TransInfoRepository transInfoRepository; 
	
	@Transactional
	public void add(Document document) {
		if(document.getCreateDate() == null || document.getTags().isEmpty() || document.getTransInfoList().isEmpty()) {
			throw new RuntimeSystemException(new SystemException("All fields are required!", ErrorCode.NULL_PROPERTY));
		}

		document = documentRepository.save(document);
		for (TransInfo transInfo : document.getTransInfoList()) {
			transInfo.setDocument(document);
			transInfo = transInfoRepository.save(transInfo);
			for (Body body : transInfo.getBodies()) {
				body.setId(transInfo.getId());
				bodyRepository.save(body);
			}
		}
	}

	@Override
	public Document findById(int id) {
		return documentRepository.findOne(id);
	}
	
	@Override
	public Page<Document> findAll(String languageCode, Pageable pageRequest) {
		Page<Document> documentPage =  documentRepository.findAll(pageRequest);
		for (Document document : documentPage) {
			TransInfo transInfo = transInfoRepository.findByDocumentAndLanguageCodeStartsWith(document, languageCode);
			if (transInfo != null) {
				List<TransInfo> transInfoList = new ArrayList<>();
				transInfoList.add(transInfo);
				document.setTransInfoList(transInfoList);
			}
		}
		return documentPage;
	}

	@Override
	public Page<Document> findAllByTagId(int id, Pageable pageRequest) {
		Tag tag = tagRepository.findOne(id);
		if (tag == null) {
			throw new RuntimeSystemException(new SystemException("Tag doesn't exist!", ErrorCode.NOT_FOUND));
		}
		Page<Document> documentPage = documentRepository.findAllByTags(tag, pageRequest);
		for (Document document : documentPage) {
			List<TransInfo> transInfoList = transInfoRepository.findAllByDocument(document);
			if (!transInfoList.isEmpty()) {
				document.setTransInfoList(transInfoList);
			}
		}
		return documentPage;
	}

}
