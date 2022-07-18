package dev.anton.jar.account.service.mapper;

import dev.anton.jar.account.service.entity.JarAccountEntity;
import dev.anton.jar.account.service.entity.JarAccountTransactionEntity;
import dev.anton.model.JarAccount;
import dev.anton.model.JarAccountSaving;
import dev.anton.model.NewJarAccountSaving;

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

    public static JarAccountTransactionEntity mapJarAccountTransactionEntity(NewJarAccountSaving newJarAccountSaving, JarAccountEntity jarAccountEntity) {
        BigDecimal savingAmount = determineRoundUpAmount(newJarAccountSaving.getOriginalTransactionAmount(), jarAccountEntity.getRoundUp());
        JarAccountTransactionEntity jarAccountTransactionEntity = new JarAccountTransactionEntity();
        jarAccountTransactionEntity.setJarAccount(jarAccountEntity);
        jarAccountTransactionEntity.setOriginalTransactionAmount(newJarAccountSaving.getOriginalTransactionAmount());
        jarAccountTransactionEntity.setId(UUID.randomUUID().toString());
        jarAccountTransactionEntity.setAmount(savingAmount);
        jarAccountTransactionEntity.setBookingDate(newJarAccountSaving.getBookingDate());
        jarAccountTransactionEntity.setValueDate(newJarAccountSaving.getValueDate());
        jarAccountTransactionEntity.setRoundOffRule(jarAccountEntity.getRoundUp());
        jarAccountTransactionEntity.setNarration("RoundUp Saving for Transaction Amount " + newJarAccountSaving.getOriginalTransactionAmount() + " on " + newJarAccountSaving.getValueDate());
        return jarAccountTransactionEntity;
    }

    public static JarAccountSaving mapJarAccountSaving(JarAccountTransactionEntity jarAccountTransactionEntity) {
        JarAccountSaving jarAccountSaving = new JarAccountSaving();
        jarAccountSaving.setJarAccountTransactionId(jarAccountTransactionEntity.getId());
        jarAccountSaving.setSavingAmount(jarAccountTransactionEntity.getAmount());
        jarAccountSaving.setBookingDate(jarAccountTransactionEntity.getBookingDate());
        jarAccountSaving.setValueDate(jarAccountTransactionEntity.getValueDate());
        jarAccountSaving.setOriginalTransactionAmount(jarAccountTransactionEntity.getOriginalTransactionAmount());
        jarAccountSaving.setNarration(jarAccountTransactionEntity.getNarration());
        return jarAccountSaving;
    }

    private static BigDecimal determineRoundUpAmount(BigDecimal originalTransactionAmount, String roundUpRule) {
        BigDecimal roundUpDivisor = ROUND_UP_DIVISOR_MAP.get(roundUpRule);
        return originalTransactionAmount.divide(ROUND_UP_DIVISOR_MAP.get(roundUpRule), 0, RoundingMode.UP)
                .multiply(roundUpDivisor).setScale(2, RoundingMode.UP)
                .subtract(originalTransactionAmount);
    }
}
