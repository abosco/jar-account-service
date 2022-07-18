package dev.anton.jar.account.service.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOUNT",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CUSTOMER_ID", "IBAN", "CURRENCY"})})
public class BankAccountEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String accountId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private String customerId;

    @Column(name = "IBAN", nullable = false)
    private String bankAccountIban;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @OneToOne(mappedBy = "bankAccount", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private JarAccountEntity jarAccount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankAccountIban() {
        return bankAccountIban;
    }

    public void setBankAccountIban(String bankAccountIban) {
        this.bankAccountIban = bankAccountIban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public JarAccountEntity getJarAccount() {
        return jarAccount;
    }

    public void setJarAccount(JarAccountEntity jarAccount) {
        this.jarAccount = jarAccount;
    }
}
