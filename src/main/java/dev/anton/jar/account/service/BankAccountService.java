package dev.anton.jar.account.service;

import dev.anton.jar.account.service.dao.BankAccountDao;
import dev.anton.jar.account.service.mapper.BankAccountMapper;
import dev.anton.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final BankAccountDao bankAccountDao;

    @Autowired
    public BankAccountService(final BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    public Accounts getBankAccounts(String customerId) {
        return new Accounts().accounts(bankAccountDao.findByCustomerId(customerId).stream().map(BankAccountMapper::mapBankAccount).collect(Collectors.toList()));
    }


}
