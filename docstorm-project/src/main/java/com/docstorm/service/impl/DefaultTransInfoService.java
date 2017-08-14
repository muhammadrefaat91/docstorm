package com.docstorm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docstorm.jpa.repository.TransInfoRepository;
import com.docstorm.repository.entities.TransInfo;
import com.docstorm.service.TransInfoService;

@Service
public class DefaultTransInfoService implements TransInfoService{

	@Autowired private TransInfoRepository transInfoRepository;
	
	@Override
	public void add(TransInfo transInfo) {
		transInfoRepository.save(transInfo);
	}

}
