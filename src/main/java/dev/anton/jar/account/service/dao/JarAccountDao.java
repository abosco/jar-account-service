package dev.anton.jar.account.service.dao;

import dev.anton.jar.account.service.entity.JarAccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JarAccountDao extends CrudRepository<JarAccountEntity, String> {

    @Query("select jar from JarAccountEntity jar, BankAccountEntity acc " +
            "where acc.customerId = ?1 and jar.linkedAccountIban = acc.bankAccountIban and jar.currency = acc.currency")
    List<JarAccountEntity> findByCustomerId(String customerId);

    List<JarAccountEntity> findByLinkedAccountIban(String linkedAccountIban);

    @Query("select jar from JarAccountEntity jar, BankAccountEntity acc " +
            "where acc.customerId = ?1 and acc.bankAccountIban = ?2 and jar.linkedAccountIban = acc.bankAccountIban and jar.currency = acc.currency")
    List<JarAccountEntity> findByCustomerIdAndLinkedAccountIban(String customerId, String linkedAccount);

}