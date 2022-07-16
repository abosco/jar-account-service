package dev.anton.jar.account.service;

import dev.anton.jar.account.exception.BadRequestException;
import dev.anton.jar.account.service.dao.JarAccountDao;
import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.jar.account.service.mapper.JarAccountMapper;
import dev.anton.model.JarAccount;
import dev.anton.model.NewJarAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JarAccountService {

    private final JarAccountDao jarAccountDao;

    @Autowired
    public JarAccountService(final JarAccountDao jarAccountDao) {
        this.jarAccountDao = jarAccountDao;
    }

    public JarAccount createJarAccount(final NewJarAccount newJarAccount) {
        try {
            JarAccountEntity jarAccountEntity = JarAccountMapper.mapJarAccountEntity(newJarAccount);
            jarAccountDao.save(jarAccountEntity);
            return JarAccountMapper.mapJarAccount(jarAccountEntity);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new BadRequestException("JAR_ACCOUNT_ALREADY_EXISTS", "JarAccount already exists for this account");
        }
    }

    public List<JarAccount> getJarAccounts(String customerId, String linkedAccount) {
        if (customerId == null && linkedAccount == null) {
            throw new BadRequestException("MISSING_QUERY_PARAMETERS", "provide either customerId or linkedAccount");
        }
        return getJarAccountEntities(customerId, linkedAccount).stream().map(JarAccountMapper::mapJarAccount).collect(Collectors.toList());
    }

    private List<JarAccountEntity> getJarAccountEntities(String customerId, String linkedAccount) {
        if (null != customerId && null != linkedAccount) {
            return jarAccountDao.findByCustomerIdAndLinkedAccountIban(customerId, linkedAccount);
        }
        if (null != customerId) {
            return jarAccountDao.findByCustomerId(customerId);
        }
        return jarAccountDao.findByLinkedAccountIban(linkedAccount);
    }


}
