package com.docstorm.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.docstorm.jpa.repository.BodyRepository;
import com.docstorm.jpa.repository.DocumentRepository;
import com.docstorm.jpa.repository.TagRepository;
import com.docstorm.jpa.repository.TransInfoRepository;
import com.docstorm.repository.entities.Body;
import com.docstorm.repository.entities.Document;
import com.docstorm.repository.entities.Tag;
import com.docstorm.repository.entities.TransInfo;
import com.docstorm.service.DocumentService;
import com.docstorm.service.impl.DefaultDocumentService;



@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {
	@Mock private DocumentRepository documentRepository;
	@Mock private TagRepository tagRepository;
	@Mock private TransInfoRepository transInfoRepository;
	@Mock private BodyRepository bodyRepository ;
	@InjectMocks private DocumentService documentService = new DefaultDocumentService();
	
	
	private List<TransInfo>  transInfoList;
	private List<Tag> tagList;
	private List<Body> bodyList;
	private List<Document> documentList;
	private Document document;
	
	@Before
	public void init() {
		Mockito.reset(documentRepository);
		Mockito.reset(tagRepository);
		Mockito.reset(transInfoRepository);
		Mockito.reset(bodyRepository);
		
		Body body = createBody();
		bodyList = new ArrayList<>();
		bodyList.add(body);
		
		Tag tag = createTag();
		tagList = new ArrayList<>();
		tagList.add(tag);

		TransInfo transInfo = createTransInfo(bodyList);
		transInfoList = new ArrayList<>();
		transInfoList.add(transInfo);
		
		
		document = createDocument(transInfoList, tagList);
		document.setTransInfoList(transInfoList);
		document.setTags(tagList);
		documentList = new ArrayList<>();
		documentList.add(document);
	}

	@Test
	 public void findByTagId() {
		when(tagRepository.findOne(1)).thenReturn(tagList.get(0));
		when(documentRepository.findAllByTags(any(Tag.class), any(PageRequest.class))).thenReturn(new PageImpl<>(documentList));
		when(transInfoRepository.findAllByDocument(document)).thenReturn(transInfoList);
		List<Document> documents = documentService.findAllByTagId(1, any(PageRequest.class)).getContent();

		assertThat(documents).isNotEmpty();
		verify(documentRepository).findAllByTags(any(Tag.class), any(PageRequest.class));
		verify(transInfoRepository).findAllByDocument(document);
		verify(tagRepository).findOne(1);
	 }
	
	@Test
	 public void findAllByGivenLanguageCode() {
		when(documentRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(documentList));
		when(transInfoRepository.findByDocumentAndLanguageCodeStartsWith(any(Document.class), any(String.class))).thenReturn(transInfoList.get(0));
		List<Document> documents = documentService.findAll("en", any(PageRequest.class)).getContent();

		assertThat(documents).isNotEmpty();
		verify(documentRepository).findAll(any(PageRequest.class));
		verify(transInfoRepository).findByDocumentAndLanguageCodeStartsWith(any(Document.class), any(String.class));
	 }
	
	@Test
	 public void saveDocument() {

		when(documentRepository.save(any(Document.class))).thenReturn(document);
		when(transInfoRepository.save(any(TransInfo.class))).thenReturn(transInfoList.get(0));
		documentService.add(document);
		
		verify(documentRepository).save(any(Document.class));
		verify(transInfoRepository).save(any(TransInfo.class));
		verify(bodyRepository).save(any(Body.class));
		
	 }

	private Document createDocument(List<TransInfo> transInfoList, List<Tag> tagList) {
		Document document = new Document();
		document.setCreateDate(LocalDate.now());
		document.setTags(tagList);
		document.setTransInfoList(transInfoList);
		return document;
	}
	
	private TransInfo createTransInfo(List<Body> bodies) {
		TransInfo transInfo = new TransInfo();
		transInfo.setLanguageCode("EG");
		transInfo.setName("How to program Book");
		transInfo.setBodies(bodies);
		return transInfo;
	}
	
	private Body createBody() {
		return new Body("This book is really good.");
	}
	
	private Tag createTag() {
		return new Tag("java");
	}
	
	
}
