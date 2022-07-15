package dev.anton.jar.account.service.dao;

import dev.anton.jar.account.service.entity.JarAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JarAccountDao extends CrudRepository<JarAccountEntity, String> {

}
