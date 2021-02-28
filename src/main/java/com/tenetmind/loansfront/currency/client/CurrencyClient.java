package com.tenetmind.loansfront.currency.client;

import com.tenetmind.loansfront.currency.client.config.CurrencyConfiguration;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

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

    public boolean deleteCurrency(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(singletonList(APPLICATION_JSON));

        HttpEntity<CurrencyDto> request = new HttpEntity<>(headers);

        ResponseEntity<CurrencyDto> response = restTemplate.exchange(
                config.getEndpoint() + id,
                DELETE,
                request,
                CurrencyDto.class,
                1);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

    public boolean updateCurrency(CurrencyDto currency) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(singletonList(APPLICATION_JSON));

        HttpEntity<CurrencyDto> request = new HttpEntity<>(headers);

        ResponseEntity<CurrencyDto> response = restTemplate.exchange(
                config.getEndpoint(),
                PUT,
                request,
                CurrencyDto.class,
                1);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }
}
