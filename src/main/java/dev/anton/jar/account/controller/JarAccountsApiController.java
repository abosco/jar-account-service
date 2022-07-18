package dev.anton.jar.account.controller;

import dev.anton.api.JarAccountsApi;
import dev.anton.jar.account.service.JarAccountSavingService;
import dev.anton.jar.account.service.JarAccountService;
import dev.anton.model.JarAccount;
import dev.anton.model.JarAccountSaving;
import dev.anton.model.NewJarAccount;
import dev.anton.model.NewJarAccountSaving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JarAccountsApiController implements JarAccountsApi {

    @Autowired
    JarAccountService jarAccountService;

    @Autowired
    JarAccountSavingService jarAccountSavingService;

    @Override
    public ResponseEntity<JarAccount> createJarAccount(NewJarAccount newJarAccount) {
        return ResponseEntity.ok().body(jarAccountService.createJarAccount(newJarAccount));
    }

    @Override
    public ResponseEntity<List<JarAccount>> getJarAccounts(String customerId, String linkedAccount) {
        return ResponseEntity.ok().body(jarAccountService.getJarAccounts(customerId, linkedAccount));
    }

    @Override
    public ResponseEntity<JarAccountSaving> saveIntoJarAccount(String jarAccountId, NewJarAccountSaving newJarAccountSaving) {
        return ResponseEntity.ok().body(jarAccountSavingService.saveIntoJarAccount(jarAccountId, newJarAccountSaving));
    }

    @Override
    public ResponseEntity<List<JarAccountSaving>> getJarAccountSavings(String jarAccountId) {
        return ResponseEntity.ok().body(jarAccountSavingService.getJarAccountSavings(jarAccountId));
    }
}
