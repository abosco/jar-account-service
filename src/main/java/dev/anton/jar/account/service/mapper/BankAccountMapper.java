package dev.anton.jar.account.service.mapper;

import dev.anton.jar.account.service.entity.BankAccountEntity;
import dev.anton.model.Account;

public final class BankAccountMapper {

    private BankAccountMapper() {
        //empty private constructor to avoid instantiation
    }

    public static Account mapBankAccount(BankAccountEntity bankAccountEntity) {
        return new Account()
                .accountId(bankAccountEntity.getAccountId())
                .iban(bankAccountEntity.getBankAccountIban())
                .currency(Account.CurrencyEnum.valueOf(bankAccountEntity.getCurrency()))
                .customerId(bankAccountEntity.getCustomerId())
                .balance(bankAccountEntity.getBalance())
                .jarAccount(null != bankAccountEntity.getJarAccount() ? JarAccountMapper.mapJarAccount(bankAccountEntity.getJarAccount()) : null);
    }

}
