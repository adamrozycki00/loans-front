package com.tenetmind.loansfront.loan.domainmodel;

import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.currency.domainmodel.Currency;
import com.tenetmind.loansfront.customer.domainmodel.Customer;
import com.tenetmind.loansfront.installment.domainmodel.Installment;
import com.tenetmind.loansfront.operation.domainmodel.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Loan {

    private Long id;
    private LocalDateTime date;
    private LoanApplication application;
    private Customer customer;
    private Currency currency;
    private BigDecimal amount;
    private Integer period;
    private BigDecimal baseRate;
    private BigDecimal marginRate;
    private BigDecimal balance;
    private BigDecimal amountToPay;
    private Integer numberOfInstallmentsPaid;
    private String status;
    private List<Installment> schedule;
    private List<Operation> operations;

    public Loan(LocalDateTime date, LoanApplication application, BigDecimal baseRate) {
        this.date = date;
        this.application = application;
        this.customer = application.getCustomer();
        this.currency = application.getCurrency();
        this.amount = application.getAmount();
        this.period = application.getPeriod();
        this.baseRate = baseRate;
        this.marginRate = application.getMarginRate();
        this.balance = BigDecimal.ZERO;
        this.amountToPay = BigDecimal.ZERO;
        this.numberOfInstallmentsPaid = 0;
        this.status = "New";
        this.schedule = new ArrayList<>();
        this.operations = new ArrayList<>();
    }

    public void setStatus(String status) {
        this.status = status.substring(0, 1).toUpperCase() + status.substring(1);
    }

}
