package com.scalefocus.digitalriver.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.digitalriver.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {

}
