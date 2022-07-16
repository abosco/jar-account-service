package dev.anton.jar.account.service.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "JAR_ACCOUNT_BALANCE")
public class JarAccountBalanceEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JAR_ACCOUNT_ID")
    private JarAccountEntity jarAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
