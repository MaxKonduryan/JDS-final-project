package ru.jds.reboot.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jds.reboot.server.entity.AtmEntity;

@Repository
public interface AtmCrudRepository extends CrudRepository<AtmEntity, String> {}
