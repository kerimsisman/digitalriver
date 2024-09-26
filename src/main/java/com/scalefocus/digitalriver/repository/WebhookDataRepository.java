package com.scalefocus.digitalriver.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.digitalriver.model.WebhookData;

@Repository
public interface WebhookDataRepository extends CrudRepository<WebhookData, UUID> {

	@Query("select w from WebhookData w left outer join ClientCursor c on w.id=c.webhookData.id and c.client.id=?1 where c.id is null and (w.source=?2 or ?2 is null) and w.createdAt > ?3 ")
	List<WebhookData> unreadedData(UUID clientId,String source, Instant date);
}
