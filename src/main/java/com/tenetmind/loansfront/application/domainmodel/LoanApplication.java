package com.tenetmind.loansfront.application.domainmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class LoanApplication {

    private Long id;
    private LocalDateTime date;
    private String firstName;
    private String lastName;
    private String pesel;
    private String currency;
    private BigDecimal amount;
    private Integer period;
    private BigDecimal marginRate;
    private String status;

    public LoanApplication(Long id, LocalDateTime date, String firstName, String lastName, String pesel,
                           String currency, BigDecimal amount, Integer period, BigDecimal marginRate, String status) {
        this.id = id;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.currency = currency;
        this.amount = amount;
        this.period = period;
        this.marginRate = marginRate;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanApplication that = (LoanApplication) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", date=" + date +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", period=" + period +
                ", marginRate=" + marginRate +
                ", status='" + status + '\'' +
                '}';
    }

}
