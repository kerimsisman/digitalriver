package com.scalefocus.digitalriver.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scalefocus.digitalriver.model.WebhookData;
import com.scalefocus.digitalriver.repository.WebhookDataRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WebhookDataService {

	private final WebhookDataRepository repository;

	public WebhookDataService(WebhookDataRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public UUID insert(String token,String source, String queryParameter, String requestBody) {
		log.info("insert started->queryParameter:{} requestBody:{}", queryParameter, requestBody);
		WebhookData data = new WebhookData();

		data.setToken(token);
		data.setSource(source);
		data.setRequestBody(requestBody);
		data.setRequestParameter(queryParameter);
		repository.save(data);
		log.info("insert completed->queryParameter:{} requestBody:{} id:{}", queryParameter, requestBody, data.getId());
		return data.getId();
	}

}
