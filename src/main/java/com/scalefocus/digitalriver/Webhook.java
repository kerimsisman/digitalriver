package com.scalefocus.digitalriver;

import java.time.Instant;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fire")
@Slf4j
public class Webhook {

	@PostMapping
	public String hook(@RequestBody String requestBody) {
		log.info("started. {}", Instant.now());
		log.info("requestBody:[{}]", requestBody);
		log.info("completed. {}", Instant.now());
		return "ok";
	}

	@PostMapping(path = "/sleekplan")
	public String sleekplan(@QueryParam("key") String key, @RequestBody String requestBody) {
		log.info("sleekplan-started. {}", Instant.now());

		log.info("sleekplan-key:[{}]", key);

		log.info("sleekplan-requestBody:[{}]", requestBody);

		log.info("sleekplan-completed. {}", Instant.now());
		return "sleekplan-ok";
	}

}
