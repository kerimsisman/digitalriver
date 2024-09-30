package com.scalefocus.digitalriver.service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scalefocus.digitalriver.model.Client;
import com.scalefocus.digitalriver.model.WebhookData;
import com.scalefocus.digitalriver.repository.WebhookDataRepository;

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
	public List<WebhookData> readClientData(UUID clientId, String source, UUID dataId) throws Exception {
		log.info("readClientData started clientId:{} source:{} dataId:{} date:{}", clientId, source, dataId,
				Instant.now());

		if (dataId == null) {
			return readAllUnreaded(clientId, source);
		} else {
			return readByIdList(clientId, Set.of(dataId));
		}
	}

	public List<WebhookData> readByIdList(UUID clientId, Set<UUID> idList) throws Exception {
		log.info("readByIdList  started clientId:{} idList:{}", clientId, idList);
		List<WebhookData> list = repository.findByIdList(idList);

		Client client = clientService.load(clientId);
		if (list != null && !list.isEmpty()) {
			for (WebhookData data : list) {
				clientCursorService.save(client, data);
			}
		}

		log.info("readByIdList completed clientId:{} idList:{} list:{}", clientId, idList,
				list == null ? 0 : list.size(), Instant.now(), list);

		return list;
	}

	public List<WebhookData> readAllUnreaded(UUID clientId, String source) throws Exception {
		Instant date = Instant.now().minusSeconds(30);
		log.info("readAllUnreaded started clientId:{} source:{}  date(30 sec before):{}", clientId, source, date);

		List<WebhookData> list = repository.unreadedData(clientId, source, date);
		Client client = clientService.load(clientId);
		if (list != null && !list.isEmpty()) {
			for (WebhookData data : list) {
				clientCursorService.save(client, data);
			}
		}

		log.info("readAllUnreaded completed clientId:{} source:{}  date(30 sec before):{} dataCount:{} list:{}",
				clientId, source, list == null ? 0 : list.size(), date, list);

		return list;
	}
}
