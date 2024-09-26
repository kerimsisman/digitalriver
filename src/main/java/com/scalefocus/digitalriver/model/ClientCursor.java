package com.scalefocus.digitalriver.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "client_cursor", indexes = {
		@Index(columnList = "client_id,webhook_data_id", name = "idx_client_cursor_clnt_id_whd_id"),
		@Index(columnList = "client_id", name = "idx_client_cursor_client_id") })
@Data
public class ClientCursor {

	@Id
	private UUID id = UUID.randomUUID();

	@Column(name = "created_at")
	private Instant createdAt = Instant.now();

	@ManyToOne
	@JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_client_cursor_to_client"))
	private Client client;


	@ManyToOne
	@JoinColumn(name = "webhook_data_id", foreignKey = @ForeignKey(name = "fk_client_cursor_to_webhook_data"))
	private WebhookData webhookData;

}
