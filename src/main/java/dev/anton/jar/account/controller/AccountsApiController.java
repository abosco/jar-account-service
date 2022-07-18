package dev.anton.jar.account.controller;

import dev.anton.api.AccountsApi;
import dev.anton.jar.account.service.BankAccountService;
import dev.anton.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsApiController implements AccountsApi {

    @Autowired
    BankAccountService bankAccountService;

    @Override
    public ResponseEntity<List<Account>> getAccounts(String customerId) {
        return ResponseEntity.ok().body(bankAccountService.getBankAccounts(customerId));
    }

}
