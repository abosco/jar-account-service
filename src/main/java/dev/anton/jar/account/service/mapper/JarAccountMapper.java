package dev.anton.jar.account.service.mapper;

import dev.anton.jar.account.service.entity.JarAccountBalanceEntity;
import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.model.JarAccount;
import dev.anton.model.NewJarAccount;

import java.math.BigDecimal;
import java.util.UUID;

public final class JarAccountMapper {

    private JarAccountMapper() {
        //empty private constructor to avoid instantiation
    }

    public static JarAccount mapJarAccount(JarAccountEntity jarAccountEntity) {
        return new JarAccount()
                .jarAccountId(jarAccountEntity.getJarAccountId())
                .linkedAccount(jarAccountEntity.getLinkedAccountIban())
                .currency(JarAccount.CurrencyEnum.valueOf(jarAccountEntity.getCurrency()))
                .roundUp(JarAccount.RoundUpEnum.valueOf(jarAccountEntity.getRoundUp()))
                .balance(jarAccountEntity.getJarAccountBalance().getBalance())
                .status(JarAccount.StatusEnum.valueOf(jarAccountEntity.getStatus()));
    }

    public static JarAccountEntity mapJarAccountEntity(NewJarAccount newJarAccount) {
        final JarAccountEntity jarAccountEntity = new JarAccountEntity();
        jarAccountEntity.setJarAccountId(String.valueOf(UUID.randomUUID()));
        jarAccountEntity.setLinkedAccountIban(newJarAccount.getLinkedAccount());
        jarAccountEntity.setCurrency(newJarAccount.getCurrency().name());
        jarAccountEntity.setRoundUp(newJarAccount.getRoundUp().name());
        jarAccountEntity.setStatus(JarAccount.StatusEnum.ENABLED.name());
        JarAccountBalanceEntity jarAccountBalanceEntity = new JarAccountBalanceEntity();
        jarAccountBalanceEntity.setId(jarAccountEntity.getJarAccountId());
        jarAccountBalanceEntity.setBalance(BigDecimal.ZERO);
        jarAccountBalanceEntity.setJarAccount(jarAccountEntity);
        jarAccountEntity.addJarAccountBalance(jarAccountBalanceEntity);
        return jarAccountEntity;
    }
}
