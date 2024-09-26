package com.scalefocus.digitalriver.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "webhook_data")
@Data
public class WebhookData {
	@Id
	private UUID id = UUID.randomUUID();

	@Column(name = "token")
	private String token;
	
	@Column(name = "source")
	private String source;

	@Column(name = "request_parameter",length = 4000)
	private String requestParameter;

	@Column(name = "request_body",columnDefinition="TEXT")
	private String requestBody;

	@Column(name = "created_at")
	private Instant createdAt = Instant.now();
}
