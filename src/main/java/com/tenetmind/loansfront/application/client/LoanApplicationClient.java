package com.tenetmind.loansfront.application.client;

import com.tenetmind.loansfront.application.client.config.LoanApplicationConfiguration;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class LoanApplicationClient {

    @Autowired
    private LoanApplicationConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<LoanApplicationDto> getApplicationDtos() {
        try {
            return (List<LoanApplicationDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoanApplicationDto getApplicationDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, LoanApplicationDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createApplication(LoanApplicationDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<LoanApplicationDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<LoanApplicationDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, LoanApplicationDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
