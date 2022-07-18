package dev.anton.jar.account;

import dev.anton.jar.account.service.dao.BankAccountDao;
import dev.anton.jar.account.service.entity.BankAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class MockAccountLoader implements ApplicationRunner {

    private final BankAccountDao bankAccountDao;

    @Autowired
    public MockAccountLoader(final BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }


    public void run(ApplicationArguments args) {
        bankAccountDao.save(setupBankAccount("DE99123456781234567891", new BigDecimal("100.00")));
        bankAccountDao.save(setupBankAccount("DE99123456781234567892", new BigDecimal("20.00")));
        bankAccountDao.save(setupBankAccount("DE99123456781234567893", new BigDecimal("10.00")));
        bankAccountDao.save(setupBankAccount("DE99123456781234567894", new BigDecimal("0.00")));
        bankAccountDao.save(setupBankAccount("DE99123456781234567895", new BigDecimal("500.00")));
    }

    private BankAccountEntity setupBankAccount(String iban, BigDecimal balance) {
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountId(UUID.randomUUID().toString());
        bankAccountEntity.setBankAccountIban(iban);
        bankAccountEntity.setBalance(balance);
        bankAccountEntity.setCurrency("EUR");
        bankAccountEntity.setCustomerId("6152009107");
        bankAccountEntity.setStatus("ACTIVE");
        return bankAccountEntity;
    }

}