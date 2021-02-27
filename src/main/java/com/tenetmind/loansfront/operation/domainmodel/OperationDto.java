package com.tenetmind.loansfront.operation.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class OperationDto {

    private final Long id;
    private final LocalDate date;
    private final LoanDto loanDto;
    private final String type;
    private final CurrencyDto currencyDto;
    private final BigDecimal amount;
    private final BigDecimal plnAmount;

}
