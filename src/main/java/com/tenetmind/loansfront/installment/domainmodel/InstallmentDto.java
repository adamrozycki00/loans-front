package com.tenetmind.loansfront.installment.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class InstallmentDto {

    private final Long id;
    private final LocalDate date;
    private final LoanDto loanDto;
    private final Integer number;
    private final CurrencyDto currencyDto;
    private final BigDecimal principal;
    private final BigDecimal interest;

}
