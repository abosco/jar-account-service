package dev.anton.jar.account.service.dao;

import dev.anton.jar.account.service.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountDao extends CrudRepository<BankAccountEntity, String> {

    List<BankAccountEntity> findByCustomerId(String customerId);

    @Query("select acc from BankAccountEntity acc where acc.bankAccountIban = ?1 and acc.currency = ?2")
    List<BankAccountEntity> findByIbanAndCurrency(String iban, String currency);
}