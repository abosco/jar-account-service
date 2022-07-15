package dev.anton.jar.account.service.dao;

import dev.anton.jar.account.service.entity.JarAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JarAccountDao extends CrudRepository<JarAccountEntity, String> {

    List<JarAccountEntity> findByCustomerId(String customerId);

    List<JarAccountEntity> findByLinkedAccountIban(String linkedAccountIban);

    List<JarAccountEntity> findByCustomerIdAndLinkedAccountIban(String customerId, String linkedAccount);

}