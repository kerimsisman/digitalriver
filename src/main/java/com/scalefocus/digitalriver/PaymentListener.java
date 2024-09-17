package com.scalefocus.digitalriver;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentListener {

	@GetMapping
	public String listen(@RequestParam(name = "organizationId") UUID organizationId,
			@RequestParam(name = "subscriptionId") UUID subscriptionId) {
		log.info("PaymentCompleted organizationId:{} subscriptionId:{}", organizationId, subscriptionId);
		return "payment-ok";
	}

}
