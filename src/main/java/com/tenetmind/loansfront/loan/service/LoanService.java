package com.tenetmind.loansfront.loan.service;

import com.tenetmind.loansfront.loan.client.LoanClient;
import com.tenetmind.loansfront.loan.domainmodel.Loan;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import com.tenetmind.loansfront.loan.domainmodel.LoanMapper;
import com.tenetmind.loansfront.operation.client.PaymentClient;
import com.tenetmind.loansfront.operation.client.PaymentDto;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final String NEW = "New";
    private final String ACTIVE = "Active";

    @Autowired
    private LoanClient loanClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private LoanMapper mapper;

    public LoanDto get(Long id) {
        return loanClient.getLoanDto(id);
    }

    public List<LoanDto> getDtos() {
        return loanClient.getLoanDtos();
    }

    public List<Loan> getAll() {
        return loanClient.getLoanDtos().stream()
                .map(mapper::mapFromDto)
                .collect(Collectors.toList());
    }

    public boolean save(LoanDto application) {
        return loanClient.createLoan(application);
    }

    public boolean update(LoanDto application) {
        return loanClient.updateLoan(application);
    }

    public BigDecimal getAmountOfNextInstallment(LoanDto loanDto) {
        return loanClient.getAmountOfNextInstallment(loanDto);
    }

    public void makeLoan(Loan loan) {
        if (NEW.equals(loan.getStatus())) {
            PaymentDto paymentDto = new PaymentDto(LocalDate.now(), loan.getId());
            paymentClient.makeLoan(paymentDto);
            loan.setNextInstallmentString(getAmountOfNextInstallment(mapper.mapToDto(loan)).toString());
        } else {
            Notification.show("The loan has already been made");
        }
    }

    public void payInstallment(Loan loan) {
        if (ACTIVE.equals(loan.getStatus()) && loan.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            PaymentDto paymentDto = new PaymentDto(LocalDate.now(), loan.getId(),
                    loan.getCurrencyString(), new BigDecimal(loan.getNextInstallmentString()));
            paymentClient.payInstallment(paymentDto);
        } else {
            Notification.show("You can only pay installment for active loans");
        }
    }

}
