package dev.anton.jar.account.service.mapper;

import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.jar.account.service.entity.JarAccountTransactionEntity;
import dev.anton.model.JarAccount;
import dev.anton.model.JarAccountSaving;
import dev.anton.model.JarAccountTransaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.UUID;

public final class JarAccountTransactionMapper {

    private  JarAccountTransactionMapper() {
        throw new IllegalStateException("Utility class");
    }

    private static final Map<String, BigDecimal> ROUND_UP_DIVISOR_MAP = Map.of(JarAccount.RoundUpEnum.EUR.name(), BigDecimal.ONE,
            JarAccount.RoundUpEnum.FIVE_EUR.name(), new BigDecimal("5"),
            JarAccount.RoundUpEnum.TEN_EUR.name(), BigDecimal.TEN
    );

    public static JarAccountTransactionEntity mapJarAccountTransactionEntity(JarAccountSaving jarAccountSaving, JarAccountEntity jarAccountEntity) {
        BigDecimal savingAmount = determineRoundUpAmount(jarAccountSaving.getOriginalTransactionAmount(), jarAccountEntity.getRoundUp());
        JarAccountTransactionEntity jarAccountTransactionEntity = new JarAccountTransactionEntity();
        jarAccountTransactionEntity.setJarAccount(jarAccountEntity);
        jarAccountTransactionEntity.setOriginalTransactionAmount(jarAccountSaving.getOriginalTransactionAmount());
        jarAccountTransactionEntity.setId(UUID.randomUUID().toString());
        jarAccountTransactionEntity.setAmount(savingAmount);
        jarAccountTransactionEntity.setBookingDate(jarAccountSaving.getBookingDate());
        jarAccountTransactionEntity.setValueDate(jarAccountSaving.getValueDate());
        jarAccountTransactionEntity.setRoundOffRule(jarAccountEntity.getRoundUp());
        jarAccountTransactionEntity.setNarration("RoundUp Saving for Transaction Amount " + jarAccountSaving.getOriginalTransactionAmount() + " on " + jarAccountSaving.getValueDate());
        return jarAccountTransactionEntity;
    }

    public static JarAccountTransaction mapJarAccountTransaction(JarAccountTransactionEntity jarAccountTransactionEntity) {
        JarAccountTransaction jarAccountTransaction = new JarAccountTransaction();
        jarAccountTransaction.setJarAccountTransactionId(jarAccountTransactionEntity.getId());
        jarAccountTransaction.setSavingAmount(jarAccountTransactionEntity.getAmount());
        jarAccountTransaction.setBookingDate(jarAccountTransactionEntity.getBookingDate());
        jarAccountTransaction.setValueDate(jarAccountTransactionEntity.getValueDate());
        jarAccountTransaction.setOriginalTransactionAmount(jarAccountTransactionEntity.getOriginalTransactionAmount());
        jarAccountTransaction.setNarration(jarAccountTransactionEntity.getNarration());
        return jarAccountTransaction;
    }

    private static BigDecimal determineRoundUpAmount(BigDecimal originalTransactionAmount, String roundUpRule) {
        BigDecimal roundUpDivisor = ROUND_UP_DIVISOR_MAP.get(roundUpRule);
        return originalTransactionAmount.divide(ROUND_UP_DIVISOR_MAP.get(roundUpRule), 0, RoundingMode.UP)
                .multiply(roundUpDivisor).setScale(2, RoundingMode.UP)
                .subtract(originalTransactionAmount);
    }
}
