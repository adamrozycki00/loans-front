package com.tenetmind.loansfront.loan.client;

import com.tenetmind.loansfront.loan.client.config.LoanConfiguration;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class LoanClient {

    @Autowired
    private LoanConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<LoanDto> getLoanDtos() {
        try {
            return (List<LoanDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoanDto getLoanDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, LoanDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createLoan(LoanDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<LoanDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<LoanDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, LoanDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
