package dev.anton.jar.account.controller;

import dev.anton.api.JarAccountsApi;
import dev.anton.jar.account.service.JarAccountService;
import dev.anton.model.JarAccount;
import dev.anton.model.NewJarAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JarAccountsApiController implements JarAccountsApi {

    @Autowired
    JarAccountService jarAccountService;

    @Override
    public ResponseEntity<JarAccount> createJarAccount(NewJarAccount newJarAccount) {
        return ResponseEntity.ok().body(jarAccountService.createJarAccount(newJarAccount));
    }

}
