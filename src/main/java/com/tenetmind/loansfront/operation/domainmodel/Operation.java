package com.tenetmind.loansfront.operation.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.Currency;
import com.tenetmind.loansfront.loan.domainmodel.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Operation {

    private Long id;
    private LocalDate date;
    private Loan loan;
    private String type;
    private Currency currency;
    private BigDecimal amount;
    private BigDecimal plnAmount;

    public Operation(LocalDate date, Loan loan, String type, Currency currency,
                     BigDecimal amount, BigDecimal plnAmount) {
        this.date = date;
        this.loan = loan;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.plnAmount = plnAmount;
    }

}
