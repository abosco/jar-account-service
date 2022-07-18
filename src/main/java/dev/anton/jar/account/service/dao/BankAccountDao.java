package dev.anton.jar.account.service.dao;

import dev.anton.jar.account.service.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountDao extends CrudRepository<BankAccountEntity, String> {

    List<BankAccountEntity> findByCustomerId(String customerId);

    @Query("select acc from BankAccountEntity acc where acc.customerId = ?1 and acc.bankAccountIban = ?2 and acc.currency = ?3")
    List<BankAccountEntity> findByCustomerIdAndIbanAndCurrency(String customerId, String iban, String currency);
}