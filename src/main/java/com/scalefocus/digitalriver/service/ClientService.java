package com.scalefocus.digitalriver.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scalefocus.digitalriver.model.Client;
import com.scalefocus.digitalriver.repository.ClientRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {

	private final ClientRepository repository;

	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}

	public Client load(UUID clientId) throws Exception {
		Optional<Client> optionalClient = repository.findById(clientId);
		if (!optionalClient.isPresent()) {
			log.warn("load clientId:{} not found", clientId);
			throw new Exception(String.format("clientId not found", clientId));
		}
		return optionalClient.get();
	}

	public Optional<Client> loadOptionalClient(UUID clientId) {
		return repository.findById(clientId);

	}

	@Transactional
	public Client save(String name) {
		log.info("save name:{} started", name);
		Client data = new Client();
		data.setName(name);
		repository.save(data);

		log.info("save name:{} id:{} completed", name, data.getId());
		return data;
	}

}
