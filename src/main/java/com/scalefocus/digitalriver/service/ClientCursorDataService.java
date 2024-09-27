package com.scalefocus.digitalriver.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scalefocus.digitalriver.model.Client;
import com.scalefocus.digitalriver.model.WebhookData;
import com.scalefocus.digitalriver.repository.WebhookDataRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientCursorDataService {

	private final WebhookDataRepository repository;
	private final ClientCursorService clientCursorService;
	private final ClientService clientService;

	public ClientCursorDataService(WebhookDataRepository repository, ClientCursorService clientCursorService,
			ClientService clientService) {
		this.repository = repository;
		this.clientCursorService = clientCursorService;
		this.clientService = clientService;
	}
	

	@Transactional
	public List<WebhookData> readClientData(UUID clientId, String source) throws Exception {
		log.info("readClientData started clientId:{} source:{} date:{}", clientId, source, Instant.now());
		Instant date = Instant.now().minusSeconds(10);
		List<WebhookData> list = repository.unreadedData(clientId,source, date);
		Client client = clientService.load(clientId);
		if (list != null && !list.isEmpty()) {
			for (WebhookData data : list) {
				clientCursorService.save(client, data);
			}
		}

		log.info("readClientData completed clientId:{} source:{}  date:{} dataCount:{} list:{}", clientId, source,
				list == null ? 0 : list.size(), Instant.now(), list);

		return list;
	}
}
