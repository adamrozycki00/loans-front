package com.tenetmind.loansfront.currencyrate.client;

import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.currencyrate.client.config.CurrencyRateConfiguration;
import com.tenetmind.loansfront.currencyrate.domainmodel.CurrencyRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyRateClient {

    @Autowired
    private CurrencyRateConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public CurrencyRateDto getCurrencyRateDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, CurrencyRateDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkForUpToDateRate(String currencyName) {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<LoanApplicationDto> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response =
                restTemplate.postForEntity(config.getEndpoint() + "uptodate/" + currencyName,
                        entity, Boolean.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
