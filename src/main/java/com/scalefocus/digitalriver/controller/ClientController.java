package com.scalefocus.digitalriver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.digitalriver.dto.ClientRequestDto;
import com.scalefocus.digitalriver.model.Client;
import com.scalefocus.digitalriver.service.ClientService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

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
}
