package com.tenetmind.loansfront.customer.client;

import com.tenetmind.loansfront.customer.client.config.CustomerConfiguration;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class CustomerClient {

    @Autowired
    private CustomerConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<CustomerDto> getCustomerDtos() {
        try {
            return (List<CustomerDto>) restTemplate.getForObject(config.getEndpoint(), List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerDto getCustomerDto(Long id) {
        try {
            return restTemplate.getForObject(config.getEndpoint() + id, CustomerDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createCustomer(CustomerDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<CustomerDto> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<CustomerDto> response =
                restTemplate.postForEntity(config.getEndpoint(), entity, CustomerDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

}
