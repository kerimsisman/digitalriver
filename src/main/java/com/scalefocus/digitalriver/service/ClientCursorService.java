package com.scalefocus.digitalriver.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scalefocus.digitalriver.model.Client;
import com.scalefocus.digitalriver.model.ClientCursor;
import com.scalefocus.digitalriver.model.WebhookData;
import com.scalefocus.digitalriver.repository.ClientCursorRespository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientCursorService {

	private final ClientCursorRespository repository;

	public ClientCursorService(ClientCursorRespository repository) {
		this.repository = repository;
	}

	@Transactional
	public UUID save(Client client, WebhookData webhookData) {
		log.info("save client:{} webhookDataId:{}", client, webhookData.getId());
		ClientCursor data = new ClientCursor();
		data.setClient(client);
		data.setWebhookData(webhookData);
		repository.save(data);
		return data.getId();
	}

}
