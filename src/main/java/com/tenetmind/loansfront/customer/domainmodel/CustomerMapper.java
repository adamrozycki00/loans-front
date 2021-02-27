package com.tenetmind.loansfront.customer.domainmodel;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public Customer mapToNewEntity(final CustomerDto dto) {
        return new Customer(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPesel());
    }

    public Customer mapToExistingEntity(final CustomerDto dto) {
        return new Customer(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPesel());
    }

    public CustomerDto mapToDto(final Customer entity) {
        return new CustomerDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPesel());
    }

    public List<CustomerDto> mapToDtoList(final List<Customer> currencies) {
        return currencies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
