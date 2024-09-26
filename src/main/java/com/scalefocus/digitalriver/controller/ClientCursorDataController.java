package com.scalefocus.digitalriver.controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.digitalriver.model.WebhookData;
import com.scalefocus.digitalriver.service.ClientCursorDataService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/read")
@Slf4j
public class ClientCursorDataController {
	private static final String KEY_CLIENT_ID = "client-id";

	private final ClientCursorDataService service;

	public ClientCursorDataController(ClientCursorDataService service) {
		this.service = service;
	}

	@GetMapping("/{" + KEY_CLIENT_ID + "}")
	public @ResponseBody List<WebhookData> read(@RequestParam(name = "source", required = false) String source,
			@PathVariable(required = false, name = KEY_CLIENT_ID) UUID clientId) throws Exception {
		log.info("read started {} clientId:{} source:{}", Instant.now(), clientId, source);

		List<WebhookData> result = service.readClientData(clientId, source);

		log.info("read completed {} clientId:{} source:{} size:{}", Instant.now(), clientId, source,
				result == null ? 0 : result.size());
		return result;
	}
}
