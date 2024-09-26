package com.scalefocus.digitalriver.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class Client {
	@Id
	private UUID id = UUID.randomUUID();

	private String name;

	@Column(name = "created_at")
	private Instant createdAt = Instant.now();

}
