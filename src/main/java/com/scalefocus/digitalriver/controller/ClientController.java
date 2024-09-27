package com.scalefocus.digitalriver.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.digitalriver.dto.ClientRequestDto;
import com.scalefocus.digitalriver.model.Client;
import com.scalefocus.digitalriver.service.ClientService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {
	private static final String KEY_CLIENT_ID = "client-id";
	private final ClientService service;

	public ClientController(ClientService service) {
		this.service = service;
	}

	@PostMapping
	public @ResponseBody Client save(@Valid @RequestBody ClientRequestDto requestBody) {
		log.info("save started {} name:{}", requestBody.getName());

		Client result = service.save(requestBody.getName());

		return result;
	}

	@GetMapping("/{" + KEY_CLIENT_ID + "}")
	public ResponseEntity<?> find(@PathVariable(required = false, name = KEY_CLIENT_ID) UUID clientId,
			HttpServletResponse response) {
		log.info("find started clientId:{}", clientId);

		Optional<Client> client = service.loadOptionalClient(clientId);

		if (client.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
