package com.tenetmind.loansfront.installment.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstallmentDto {

    private Long id;
    private LocalDate date;
    private Long loanId;
    private Integer number;
    private CurrencyDto currencyDto;
    private BigDecimal principal;
    private BigDecimal interest;

    @Override
    public String toString() {
        return "InstallmentDto{" +
                "id=" + id +
                ", date=" + date +
                ", loanId=" + loanId +
                ", number=" + number +
                ", currencyDto=" + currencyDto +
                ", principal=" + principal +
                ", interest=" + interest +
                '}';
    }

}
