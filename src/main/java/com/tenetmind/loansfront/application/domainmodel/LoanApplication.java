package com.tenetmind.loansfront.application.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanApplication {

    private String id;
    private LocalDateTime date;
    private String dateString;
    private String customerId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String currencyId;
    private String currency;
    private String amount;
    private String period;
    private String marginRate;
    private String status;

    public LoanApplication(LoanApplicationDto dto) {
        this.id = dto.getId().toString();
        this.date = dto.getDate();
        this.dateString = dto.getDate().toLocalDate().toString();
        this.customerId = dto.getCustomerDto().getId().toString();
        this.firstName = dto.getCustomerDto().getFirstName();
        this.lastName = dto.getCustomerDto().getLastName();
        this.pesel = dto.getCustomerDto().getPesel();
        this.currencyId = dto.getCurrencyDto().getId().toString();
        this.currency = dto.getCurrencyDto().getName();
        this.amount = dto.getAmount().toString();
        this.period = dto.getPeriod().toString();
        this.marginRate = dto.getMarginRate().toString();
        this.status = dto.getStatus();
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
                ", customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", currencyId=" + currencyId +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", period=" + period +
                ", marginRate=" + marginRate +
                ", status='" + status + '\'' +
                '}';
    }

}
