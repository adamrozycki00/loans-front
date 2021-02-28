package com.tenetmind.loansfront.application.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanApplicationMapper {

    public LoanApplicationDto mapToDto(final LoanApplication application) {
        return new LoanApplicationDto(
                Long.parseLong(application.getId()),
                application.getDate(),
                new CustomerDto(Long.parseLong(application.getCustomerId()),
                        application.getFirstName(),
                        application.getLastName(),
                        application.getPesel()),
                new CurrencyDto(Long.parseLong(application.getCurrencyId()),
                        application.getCurrency()),
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
