package dev.anton.jar.account.service.dao;

import dev.anton.jar.account.service.entity.JarAccountTransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JarAccountTransactionDao extends CrudRepository<JarAccountTransactionEntity, String> {

    @Query("select tx from JarAccountTransactionEntity tx where tx.jarAccount.id = ?1")
    List<JarAccountTransactionEntity> findByJarAccountId(String jarAccountId);

}