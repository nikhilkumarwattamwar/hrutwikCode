package com.loanapp.loanManagementSystem.service.homeloan.utilily;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmiCalculator {

    public static BigDecimal calculate(BigDecimal p, BigDecimal annualRate, int tenure) {

        BigDecimal r = annualRate.divide(BigDecimal.valueOf(1200), 10, RoundingMode.HALF_UP);
        BigDecimal onePlusRPowerN = BigDecimal.ONE.add(r).pow(tenure);

        return p.multiply(r).multiply(onePlusRPowerN)
                .divide(onePlusRPowerN.subtract(BigDecimal.ONE),
                        2, RoundingMode.HALF_UP);
    }
}
