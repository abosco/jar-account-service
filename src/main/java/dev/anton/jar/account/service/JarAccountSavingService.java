package dev.anton.jar.account.service;

import dev.anton.jar.account.exception.BadRequestException;
import dev.anton.jar.account.service.dao.JarAccountDao;
import dev.anton.jar.account.service.dao.JarAccountTransactionDao;
import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.jar.account.service.entity.JarAccountTransactionEntity;
import dev.anton.jar.account.service.mapper.JarAccountTransactionMapper;
import dev.anton.model.JarAccountSaving;
import dev.anton.model.JarAccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JarAccountSavingService {

    private final JarAccountDao jarAccountDao;

    private final JarAccountTransactionDao jarAccountTransactionDao;

    @Autowired
    public JarAccountSavingService(final JarAccountDao jarAccountDao, final JarAccountTransactionDao jarAccountTransactionDao) {
        this.jarAccountDao = jarAccountDao;
        this.jarAccountTransactionDao = jarAccountTransactionDao;
    }

    public JarAccountTransaction saveIntoJarAccount(final JarAccountSaving jarAccountSaving) {
        JarAccountEntity jarAccountEntity = jarAccountDao.findById(jarAccountSaving.getJarAccountId())
                .orElseThrow(() -> new BadRequestException("JAR_ACCOUNT_NOT_FOUND", "JarAccount not found with id " + jarAccountSaving.getJarAccountId()));
        JarAccountTransactionEntity jarAccountTransactionEntity = JarAccountTransactionMapper.mapJarAccountTransactionEntity(jarAccountSaving, jarAccountEntity);
        jarAccountEntity.getJarAccountBalance().setBalance(jarAccountEntity.getJarAccountBalance().getBalance().add(jarAccountTransactionEntity.getAmount()));
        jarAccountTransactionDao.save(jarAccountTransactionEntity);
        jarAccountDao.save(jarAccountEntity);
        return JarAccountTransactionMapper.mapJarAccountTransaction(jarAccountTransactionEntity);
    }


}
