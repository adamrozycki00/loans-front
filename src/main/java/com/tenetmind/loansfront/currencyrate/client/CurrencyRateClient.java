package com.tenetmind.loansfront.currencyrate.client;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.currencyrate.client.config.CurrencyRateConfiguration;
import com.tenetmind.loansfront.currencyrate.domainmodel.CurrencyRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class CurrencyRateClient {

    @Autowired
    private CurrencyRateConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<CurrencyRateDto> getCurrencyRateDtos() {
        try {
            return (List<CurrencyRateDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CurrencyRateDto getCurrencyRateDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, CurrencyRateDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createCurrencyRate(CurrencyRateDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<CurrencyRateDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<CurrencyRateDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, CurrencyRateDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
