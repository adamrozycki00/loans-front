package com.tenetmind.loansfront.installment.domainmodel;

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
public class Installment {

    private Long id;
    private LocalDate date;
    private Loan loan;
    private Integer number;
    private Currency currency;
    private BigDecimal principal;
    private BigDecimal interest;

    @Override
    public String toString() {
        return " {" +
                "id=" + id +
                ", date=" + date +
                ", loan=" + loan +
                ", number=" + number +
                ", currency=" + currency +
                ", principal=" + principal +
                ", interest=" + interest +
                '}';
    }

}
