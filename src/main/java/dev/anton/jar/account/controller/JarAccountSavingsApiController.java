package dev.anton.jar.account.controller;

import dev.anton.api.JarAccountSavingsApi;
import dev.anton.jar.account.service.JarAccountSavingService;
import dev.anton.model.JarAccountSaving;
import dev.anton.model.JarAccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JarAccountSavingsApiController implements JarAccountSavingsApi {

    @Autowired
    JarAccountSavingService jarAccountSavingService;

    @Override
    public ResponseEntity<JarAccountTransaction> saveIntoJarAccount(JarAccountSaving jarAccountSaving) {
        return ResponseEntity.ok().body(jarAccountSavingService.saveIntoJarAccount(jarAccountSaving));
    }

}
