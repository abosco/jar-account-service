package dev.anton.jar.account.service.dao;

import dev.anton.jar.account.service.entity.JarAccountTransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JarAccountTransactionDao extends CrudRepository<JarAccountTransactionEntity, String> {


}