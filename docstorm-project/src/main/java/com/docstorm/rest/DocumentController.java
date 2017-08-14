package com.docstorm.rest;

import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docstorm.common.CommonUtils;
import com.docstorm.repository.entities.Document;
import com.docstorm.service.DocumentService;

@RequestMapping("/documents")
@RestController
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@GetMapping
	public ResponseEntity<List<Document>> getAllDocments(
			@RequestHeader(value = "accept-language", defaultValue = "en") String acceptLanguage,
			@PageableDefault(size = 10) Pageable pageRequest) {
		List<LanguageRange> ranges = Locale.LanguageRange.parse(acceptLanguage);

		Page<Document> documentList = documentService.findAll(ranges.get(0).getRange(), pageRequest);
		return new ResponseEntity<>(documentList.getContent(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> addNewDocument(@RequestBody() Document document) {
		try {
			documentService.add(document);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception ex) {
			throw CommonUtils.handleException(ex);
		}
	}

}
