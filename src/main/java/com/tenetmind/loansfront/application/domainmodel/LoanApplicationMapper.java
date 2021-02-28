package com.tenetmind.loansfront.application.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyMapper;
import com.tenetmind.loansfront.currency.service.CurrencyService;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanApplicationMapper {

    @Autowired
    private CurrencyService currencyService;

    public LoanApplicationDto mapToDto(final LoanApplication application) {
        return new LoanApplicationDto(
                application.getId() == null ? null : Long.parseLong(application.getId()),
                LocalDateTime.now(),
                new CustomerDto(
                        application.getCustomerId() == null ? null : Long.parseLong(application.getCustomerId()),
                        application.getFirstName(),
                        application.getLastName(),
                        application.getPesel()),
                currencyService.get(application.getCurrencyName().getName()),
                new BigDecimal(application.getAmount()),
                Integer.parseInt(application.getPeriod()),
                new BigDecimal(application.getMarginRate()),
                application.getStatus());
    }

    public List<LoanApplicationDto> mapToDtoList(final List<LoanApplication> loanApplications) {
        return loanApplications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
