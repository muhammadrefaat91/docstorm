package com.docstorm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docstorm.common.CommonUtils;
import com.docstorm.repository.entities.Document;
import com.docstorm.service.DocumentService;

@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private DocumentService documentService;

	@GetMapping(path = "/{tag-id}")
	public ResponseEntity<List<Document>> getAllDocmentsByTag(@PathVariable(required = true, name = "tag-id") int tagId,
			@PageableDefault(size = 10) Pageable pageRequest) {
		try {
			Page<Document> documentList = documentService.findAllByTagId(tagId, pageRequest);
			return new ResponseEntity<>(documentList.getContent(), HttpStatus.OK);
		} catch (Exception ex) {
			throw CommonUtils.handleException(ex);
		}
	}
}
