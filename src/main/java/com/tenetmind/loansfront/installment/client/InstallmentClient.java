package com.tenetmind.loansfront.installment.client;

import com.tenetmind.loansfront.installment.client.config.InstallmentConfiguration;
import com.tenetmind.loansfront.installment.domainmodel.InstallmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class InstallmentClient {

    @Autowired
    private InstallmentConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<InstallmentDto> getInstallmentDtos() {
        try {
            return (List<InstallmentDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InstallmentDto getInstallmentDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, InstallmentDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createInstallment(InstallmentDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<InstallmentDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<InstallmentDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, InstallmentDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
