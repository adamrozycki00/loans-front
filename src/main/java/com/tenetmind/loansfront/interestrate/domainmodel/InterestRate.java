package com.tenetmind.loansfront.interestrate.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.Currency;
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
public class InterestRate {

    private Long id;
    private String name;
    private LocalDate date;
    private Currency currency;
    private BigDecimal rate;

    public InterestRate(String name, LocalDate date, Currency currency, BigDecimal rate) {
        this.name = name;
        this.date = date;
        this.currency = currency;
        this.rate = rate;
    }

}
