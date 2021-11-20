package ru.jds.reboot.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jds.reboot.server.entity.CardEntity;

@Repository
public interface CardCrudRepository extends CrudRepository<CardEntity, String> {}
