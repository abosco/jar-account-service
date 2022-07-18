package dev.anton.jar.account.service.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JAR_ACCOUNT",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"LINKED_ACCOUNT", "CURRENCY"})})
public class JarAccountEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String jarAccountId;

    @Column(name = "LINKED_ACCOUNT", nullable = false)
    private String linkedAccountIban;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "ROUND_UP", nullable = false)
    private String roundUp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private BankAccountEntity bankAccount;

    @OneToOne(mappedBy = "jarAccount", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private JarAccountBalanceEntity jarAccountBalance;

    @OneToMany(mappedBy = "jarAccount", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private final List<JarAccountTransactionEntity> jarAccountTransactions = new ArrayList<>();

    public String getJarAccountId() {
        return jarAccountId;
    }

    public void setJarAccountId(String jarAccountId) {
        this.jarAccountId = jarAccountId;
    }

    public String getLinkedAccountIban() {
        return linkedAccountIban;
    }

    public void setLinkedAccountIban(String linkedAccountIban) {
        this.linkedAccountIban = linkedAccountIban;
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

    public String getRoundUp() {
        return roundUp;
    }

    public void setRoundUp(String roundUp) {
        this.roundUp = roundUp;
    }

    public BankAccountEntity getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountEntity bankAccount) {
        this.bankAccount = bankAccount;
    }

    public JarAccountBalanceEntity getJarAccountBalance() {
        return jarAccountBalance;
    }

    public void addJarAccountBalance(JarAccountBalanceEntity jarAccountBalance) {
        this.jarAccountBalance = jarAccountBalance;
    }

    public List<JarAccountTransactionEntity> getJarAccountTransactions() {
        return jarAccountTransactions;
    }

    public void addJarAccountTransaction(JarAccountTransactionEntity jarAccountTransactionEntity) {
        jarAccountTransactionEntity.setJarAccount(this);
        this.jarAccountTransactions.add(jarAccountTransactionEntity);
    }
}
