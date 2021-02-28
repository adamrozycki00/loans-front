package com.tenetmind.loansfront.operation.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyMapper;
import com.tenetmind.loansfront.loan.client.LoanClient;
import com.tenetmind.loansfront.loan.domainmodel.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationMapper {

    @Autowired
    private LoanClient loanClient;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private CurrencyMapper currencyMapper;

//    public Operation mapToNewEntity(final OperationDto dto) {
//        return new Operation(
//                dto.getDate(),
//                loanMapper.mapToExistingEntity(loanClient.getLoanDto(dto.getLoanId())),
//                dto.getType(),
//                currencyMapper.mapToExistingEntity(dto.getCurrencyDto()),
//                dto.getAmount(),
//                dto.getPlnAmount());
//    }

//    public Operation mapToExistingEntity(final OperationDto dto) {
//        return new Operation(
//                dto.getId(),
//                dto.getDate(),
//                loanMapper.mapToExistingEntity(loanClient.getLoanDto(dto.getLoanId())),
//                dto.getType(),
//                currencyMapper.mapToExistingEntity(dto.getCurrencyDto()),
//                dto.getAmount(),
//                dto.getPlnAmount());
//    }

    public OperationDto mapToDto(final Operation entity) {
        return new OperationDto(
                entity.getId(),
                entity.getDate(),
                entity.getLoan().getId(),
                entity.getType(),
                currencyMapper.mapToDto(entity.getCurrency()),
                entity.getAmount(),
                entity.getPlnAmount());
    }

    public List<OperationDto> mapToDtoList(final List<Operation> operations) {
        return operations.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

//    public List<Operation> mapToEntityList(final List<OperationDto> operationDtos) {
//        return operationDtos.stream()
//                .map(this::mapToExistingEntity)
//                .collect(Collectors.toList());
//    }

}
