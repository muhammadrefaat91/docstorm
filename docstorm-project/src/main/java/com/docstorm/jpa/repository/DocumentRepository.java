package com.docstorm.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docstorm.repository.entities.Document;
import com.docstorm.repository.entities.Tag;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	
	/**  find all document having tag-id
	 * @param tag
	 * @return List<Document>
	 */
	Page<Document> findAllByTags(Tag tag, Pageable pageRequest);
	
	/** find document by id
	 * @param id
	 * @return  Document
	 */
	Document findById(int id);
	
}
