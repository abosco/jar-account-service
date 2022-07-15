package dev.anton.jar.account.service.mapper;

import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.model.JarAccount;
import dev.anton.model.NewJarAccount;

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
                .customerId(jarAccountEntity.getCustomerId())
                .roundUp(JarAccount.RoundUpEnum.valueOf(jarAccountEntity.getRoundUp()))
                .status(JarAccount.StatusEnum.valueOf(jarAccountEntity.getStatus()));
    }

    public static JarAccountEntity mapJarAccountEntity(NewJarAccount newJarAccount) {
        final JarAccountEntity jarAccountEntity = new JarAccountEntity();
        jarAccountEntity.setJarAccountId(String.valueOf(UUID.randomUUID()));
        jarAccountEntity.setLinkedAccountIban(newJarAccount.getLinkedAccount());
        jarAccountEntity.setCurrency(newJarAccount.getCurrency().name());
        jarAccountEntity.setCustomerId(newJarAccount.getCustomerId());
        jarAccountEntity.setRoundUp(newJarAccount.getRoundUp().name());
        jarAccountEntity.setStatus(JarAccount.StatusEnum.ENABLED.name());
        return jarAccountEntity;
    }

}
