package com.tenetmind.loansfront.interestrate.client;

import com.tenetmind.loansfront.interestrate.client.config.InterestRateConfiguration;
import com.tenetmind.loansfront.interestrate.domainmodel.InterestRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class InterestRateClient {

    @Autowired
    private InterestRateConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<InterestRateDto> getInterestRateDtos() {
        try {
            return (List<InterestRateDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InterestRateDto getInterestRateDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, InterestRateDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createInterestRate(InterestRateDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<InterestRateDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<InterestRateDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, InterestRateDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
