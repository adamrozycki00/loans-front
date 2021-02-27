package com.tenetmind.loansfront.application.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class LoanApplicationDto {

    private final Long id;
    private final LocalDateTime date;
    private final CustomerDto customerDto;
    private final CurrencyDto currencyDto;
    private final BigDecimal amount;
    private final Integer period;
    private final BigDecimal marginRate;
    private final String status;

}
