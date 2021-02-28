package com.tenetmind.loansfront.application.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyMapper;
import com.tenetmind.loansfront.currency.service.CurrencyService;
import com.tenetmind.loansfront.customer.domainmodel.Customer;
import com.tenetmind.loansfront.customer.domainmodel.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanApplicationMapper {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CurrencyService currencyService;

    public LoanApplication mapFromDto(final LoanApplicationDto dto) {
        return new LoanApplication(
                dto.getId(),
                dto.getDate(),
                dto.getCustomerDto().getFirstName(),
                dto.getCustomerDto().getLastName(),
                dto.getCustomerDto().getPesel(),
                dto.getCurrencyDto().getName(),
                dto.getAmount(),
                dto.getPeriod(),
                dto.getMarginRate(),
                dto.getStatus());
    }

    public LoanApplicationDto mapToDto(final LoanApplication entity) {
        return new LoanApplicationDto(
                entity.getId(),
                entity.getDate(),
                customerMapper.mapToDto(new Customer(entity.getFirstName(), entity.getLastName(), entity.getPesel())),
                currencyService.get(entity.getCurrency()),
                entity.getAmount(),
                entity.getPeriod(),
                entity.getMarginRate(),
                entity.getStatus());
    }

    public List<LoanApplicationDto> mapToDtoList(final List<LoanApplication> loanApplications) {
        return loanApplications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
