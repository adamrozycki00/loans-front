package com.tenetmind.loansfront.currency.domainmodel;

import com.tenetmind.loansfront.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyMapper {

    @Autowired
    private CurrencyService service;

    public CurrencyDto mapToDto(final Currency entity) {
        return new CurrencyDto(
                service.get(entity.getName().getName()).getId(),
                entity.getName().getName());
    }

    public CurrencyDto mapToDto(final String name) {
        return service.get(name);
    }

    public List<CurrencyDto> mapToDtoList(final List<Currency> currencies) {
        return currencies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
