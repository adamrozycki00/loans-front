package com.tenetmind.loansfront.currencyrate.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CurrencyRateDto {

    private final Long id;
    private final String name;
    private final LocalDate date;
    private final CurrencyDto currencyDto;
    private final BigDecimal rate;

}
