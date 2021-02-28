package com.tenetmind.loansfront.installment.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyMapper;
import com.tenetmind.loansfront.loan.client.LoanClient;
import com.tenetmind.loansfront.loan.domainmodel.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstallmentMapper {

    @Autowired
    private LoanClient loanClient;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private CurrencyMapper currencyMapper;

//    public Installment mapToNewEntity(final InstallmentDto dto) {
//        return new Installment(
//                dto.getDate(),
//                loanMapper.mapToExistingEntity(loanClient.getLoanDto(dto.getLoanId())),
//                dto.getNumber(),
//                dto.getPrincipal(),
//                dto.getInterest());
//    }

//    public Installment mapToExistingEntity(final InstallmentDto dto) {
//        return new Installment(
//                dto.getId(),
//                dto.getDate(),
//                loanMapper.mapToExistingEntity(loanClient.getLoanDto(dto.getLoanId())),
//                dto.getNumber(),
//                currencyMapper.mapToExistingEntity(dto.getCurrencyDto()),
//                dto.getPrincipal(),
//                dto.getInterest());
//    }

    public InstallmentDto mapToDto(final Installment entity) {
        return new InstallmentDto(
                entity.getId(),
                entity.getDate(),
                entity.getLoan().getId(),
                entity.getNumber(),
                currencyMapper.mapToDto(entity.getCurrency()),
                entity.getPrincipal(),
                entity.getInterest());
    }

    public List<InstallmentDto> mapToDtoList(final List<Installment> installments) {
        return installments.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

//    public List<Installment> mapToEntityList(final List<InstallmentDto> installmentDtos) {
//        return installmentDtos.stream()
//                .map(this::mapToExistingEntity)
//                .collect(Collectors.toList());
//    }

}
