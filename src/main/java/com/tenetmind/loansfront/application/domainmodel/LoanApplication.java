package com.tenetmind.loansfront.application.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.Currency;
import com.tenetmind.loansfront.customer.domainmodel.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoanApplication {

    private Long id;
    private LocalDateTime date;
    private Customer customer;
    private Currency currency;
    private BigDecimal amount;
    private Integer period;
    private BigDecimal marginRate;
    private String status;

    public LoanApplication(LocalDateTime date, Customer customer, Currency currency,
                           BigDecimal amount, Integer period, BigDecimal marginRate) {
        this.date = date;
        this.customer = customer;
        this.currency = currency;
        this.amount = amount;
        this.period = period;
        this.marginRate = marginRate;
        this.status = "New";
    }

    public void setStatus(String status) {
        this.status = status.substring(0, 1).toUpperCase() + status.substring(1);
    }

}
