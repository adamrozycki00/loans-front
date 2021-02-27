package com.tenetmind.loansfront.currency.client;

import com.tenetmind.loansfront.currency.client.config.CurrencyConfiguration;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class CurrencyClient {

    @Autowired
    private CurrencyConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<CurrencyDto> getCurrencyDtos() {
        try {
            return (List<CurrencyDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CurrencyDto getCurrencyDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, CurrencyDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createCurrency(CurrencyDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<CurrencyDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<CurrencyDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, CurrencyDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
