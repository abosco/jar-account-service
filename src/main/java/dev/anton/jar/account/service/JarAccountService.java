package dev.anton.jar.account.service;

import dev.anton.jar.account.exception.BadRequestException;
import dev.anton.jar.account.service.dao.JarAccountDao;
import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.model.JarAccount;
import dev.anton.model.NewJarAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JarAccountService {
    private final JarAccountDao jarAccountDao;

    @Autowired
    public JarAccountService(final JarAccountDao jarAccountDao) {
        this.jarAccountDao = jarAccountDao;
    }

    public JarAccount createJarAccount(final NewJarAccount newJarAccount) {
        try {
            JarAccountEntity jarAccountEntity = mapJarAccountEntity(newJarAccount);
            jarAccountDao.save(jarAccountEntity);
            return mapJarAccount(jarAccountEntity);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new BadRequestException("JAR_ACCOUNT_ALREADY_EXISTS", "JarAccount already exists for this account");
        }

    }

    private JarAccount mapJarAccount(JarAccountEntity jarAccountEntity) {
        return new JarAccount()
                .jarAccountId(jarAccountEntity.getJarAccountId())
                .linkedAccount(jarAccountEntity.getLinkedAccountIban())
                .currency(JarAccount.CurrencyEnum.valueOf(jarAccountEntity.getCurrency()))
                .customerId(jarAccountEntity.getCustomerId())
                .roundUp(JarAccount.RoundUpEnum.valueOf(jarAccountEntity.getRoundUp()))
                .status(JarAccount.StatusEnum.valueOf(jarAccountEntity.getStatus()));
    }
    private JarAccountEntity mapJarAccountEntity(NewJarAccount newJarAccount) {
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
