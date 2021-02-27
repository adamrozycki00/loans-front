package com.tenetmind.loansfront.operation.client;

import com.tenetmind.loansfront.operation.client.config.OperationConfiguration;
import com.tenetmind.loansfront.operation.domainmodel.OperationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class OperationClient {

    @Autowired
    private OperationConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<OperationDto> getOperationDtos() {
        try {
            return (List<OperationDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OperationDto getOperationDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, OperationDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createOperation(OperationDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<OperationDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<OperationDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, OperationDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
