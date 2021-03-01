package com.tenetmind.loansfront.loan.client;

import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.installment.domainmodel.InstallmentDto;
import com.tenetmind.loansfront.loan.client.config.LoanConfiguration;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class LoanClient {

    @Autowired
    private LoanConfiguration config;

    @Autowired
    private RestTemplate restTemplate;

    public List<LoanDto> getLoanDtos() {
        try {
            LoanDto[] response = restTemplate.getForObject(config.getEndpoint(), LoanDto[].class);
            assert response != null;
            return Arrays.asList(response.clone());

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

    public boolean updateLoan(LoanDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(singletonList(APPLICATION_JSON));

        HttpEntity<LoanDto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<LoanDto> response = restTemplate.exchange(
                config.getEndpoint(),
                PUT,
                request,
                LoanDto.class,
                1);

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            System.out.println(response.getStatusCode());
            return false;
        }
    }

    public BigDecimal getAmountOfNextInstallment(LoanDto loanDto) {
        final int numberOfNextInstallment = loanDto.getNumberOfInstallmentsPaid() + 1;
        if (numberOfNextInstallment > loanDto.getPeriod()) {
            return BigDecimal.ZERO;
        }
        InstallmentDto nextInstallment = loanDto.getScheduleDto().stream()
                .filter(installment -> installment.getNumber() == numberOfNextInstallment)
                .collect(Collectors.toList()).get(0);
        return nextInstallment.getPrincipal().add(nextInstallment.getInterest());
    }

}
