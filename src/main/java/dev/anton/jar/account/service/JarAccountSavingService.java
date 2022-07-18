package dev.anton.jar.account.service;

import dev.anton.jar.account.exception.BadRequestException;
import dev.anton.jar.account.service.dao.JarAccountDao;
import dev.anton.jar.account.service.dao.JarAccountTransactionDao;
import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.jar.account.service.entity.JarAccountTransactionEntity;
import dev.anton.jar.account.service.mapper.JarAccountTransactionMapper;
import dev.anton.model.JarAccountSaving;
import dev.anton.model.NewJarAccountSaving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JarAccountSavingService {

    private final JarAccountDao jarAccountDao;

    private final JarAccountTransactionDao jarAccountTransactionDao;

    @Autowired
    public JarAccountSavingService(final JarAccountDao jarAccountDao, final JarAccountTransactionDao jarAccountTransactionDao) {
        this.jarAccountDao = jarAccountDao;
        this.jarAccountTransactionDao = jarAccountTransactionDao;
    }


    public JarAccountSaving saveIntoJarAccount(String jarAccountId, NewJarAccountSaving newJarAccountSaving) {
        JarAccountEntity jarAccountEntity = jarAccountDao.findById(jarAccountId)
                .orElseThrow(() -> new BadRequestException("JAR_ACCOUNT_NOT_FOUND", "JarAccount not found with id " + jarAccountId));
        JarAccountTransactionEntity jarAccountTransactionEntity = JarAccountTransactionMapper.mapJarAccountTransactionEntity(newJarAccountSaving, jarAccountEntity);
        jarAccountEntity.getJarAccountBalance().setBalance(jarAccountEntity.getJarAccountBalance().getBalance().add(jarAccountTransactionEntity.getAmount()));
        jarAccountTransactionDao.save(jarAccountTransactionEntity);
        jarAccountDao.save(jarAccountEntity);
        return JarAccountTransactionMapper.mapJarAccountSaving(jarAccountTransactionEntity);
    }

    public List<JarAccountSaving> getJarAccountSavings(String jarAccountId) {
        return jarAccountTransactionDao.findByJarAccountId(jarAccountId).stream().map(JarAccountTransactionMapper::mapJarAccountSaving).collect(Collectors.toList());
    }
}
