package com.docstorm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.docstorm.repository.entities.Document;

/**
 * @author mohamed.refaat
 *
 */
public interface DocumentService {

	/** add new document
	 * @param document
	 */
	void add(Document document);
	
	/** find document by id
	 * @param id
	 * @return {@link Document}
	 */
	Document findById(int id);
	
	/** find all documents By Language Code 
	 * @return {@link Package}
	 */
	Page<Document> findAll(String languageCode, Pageable pageRequest);
	
	/** find All document By given Tag-Id
	 * @param id
	 * @return {@link Page}
	 */
	Page<Document> findAllByTagId(int id, Pageable pageRequest);

}
