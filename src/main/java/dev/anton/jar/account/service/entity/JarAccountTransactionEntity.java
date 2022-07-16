package dev.anton.jar.account.service.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "JAR_ACCOUNT_TRANSACTION")
public class JarAccountTransactionEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "BOOKING_DATE", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "VALUE_DATE", nullable = false)
    private LocalDate valueDate;

    @Column(name = "ORIG_TX_AMOUNT", nullable = false)
    private BigDecimal originalTransactionAmount;

    @Column(name = "ROUND_OFF_RULE", nullable = false)
    private String roundOffRule;

    @Column(name = "NARRATION", nullable = false)
    private String narration;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JAR_ACCOUNT_ID")
    private JarAccountEntity jarAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public BigDecimal getOriginalTransactionAmount() {
        return originalTransactionAmount;
    }

    public void setOriginalTransactionAmount(BigDecimal originalTransactionAmount) {
        this.originalTransactionAmount = originalTransactionAmount;
    }

    public String getRoundOffRule() {
        return roundOffRule;
    }

    public void setRoundOffRule(String roundOffRule) {
        this.roundOffRule = roundOffRule;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public JarAccountEntity getJarAccount() {
        return jarAccount;
    }

    public void setJarAccount(JarAccountEntity jarAccount) {
        this.jarAccount = jarAccount;
    }
}
