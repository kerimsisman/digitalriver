package com.scalefocus.digitalriver.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scalefocus.digitalriver.service.WebhookDataService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fire")
@Slf4j
public class WebhookDataController {
	private static final String KEY_TOKEN = "token";
	private static final String KEY_SOURCE = "source";
	private final WebhookDataService service;

	public WebhookDataController(WebhookDataService service) {
		this.service = service;
	}

	@PostMapping
	public String hook(@RequestParam Map<String, String> params, @RequestBody String requestBody) {
		log.info("started. {}", Instant.now());
		log.info("params:{} requestBody:{}", params, requestBody);

		// Save data
		mapFieldsAndSave(params, requestBody);

		log.info("completed. {}", Instant.now());
		return "ok";
	}

	/**
	 * Save data in a thread
	 * 
	 * @param params
	 * @param requestBody
	 */
	private void mapFieldsAndSave(Map<String, String> params, String requestBody) {
		new Thread(() -> {
			String token = null;
			String source = null;
			String queryParam = null;
			if (params != null) {
				token = params.get(KEY_TOKEN);
				log.info("token:{}", token);

				source = params.get(KEY_SOURCE);
				log.info("source:{}", source);

				List<String> paramConcatList = params.entrySet().stream()
						.filter(m -> (!KEY_TOKEN.equals(m.getKey()) && !KEY_SOURCE.equals(m.getKey())))
						.map(m -> String.format("%s=%s", m.getKey(), m.getValue())).toList();
				if (paramConcatList != null && !paramConcatList.isEmpty()) {
					queryParam = String.join("&", paramConcatList);
				}
			}
			log.info("mapFielsAndSave-thread started. token:{} params:{} requestBody:{}", token, params, requestBody);
			UUID id = service.insert(token, source, queryParam, requestBody);
			log.info("mapFielsAndSave-thread completed. id:{} token:{} params:{} requestBody:{}", id, token, params,
					requestBody);

		}).start();
	}

}
