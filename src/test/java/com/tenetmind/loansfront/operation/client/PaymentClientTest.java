package com.tenetmind.loansfront.operation.client;

import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.operation.client.config.PaymentConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentClientTest {

    @Autowired
    private PaymentClient client;

    @Autowired
    private PaymentConfiguration config;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void shouldCallMethodPostForEntityWhenMakingLoan() {
        //given
        PaymentDto paymentDto = new PaymentDto(LocalDate.now(), 1L);

        ResponseEntity<PaymentDto> paymentDtoResponseEntity =
                new ResponseEntity<>(HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<PaymentDto> entity = new HttpEntity<>(paymentDto, headers);

        when(restTemplate.postForEntity(config.getEndpoint() + "loan/", entity, PaymentDto.class))
                .thenReturn(paymentDtoResponseEntity);

        //when
        client.makeLoan(paymentDto);

        //then
        verify(restTemplate, times(1))
                .postForEntity(config.getEndpoint() + "loan/", entity, PaymentDto.class);
    }

    @Test
    public void shouldCallMethodPostForEntityWhenPayingInstallment() {
        //given
        PaymentDto paymentDto = new PaymentDto(LocalDate.now(), 1L, "PLN", new BigDecimal("100"));

        ResponseEntity<PaymentDto> paymentDtoResponseEntity =
                new ResponseEntity<>(HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<PaymentDto> entity = new HttpEntity<>(paymentDto, headers);

        when(restTemplate.postForEntity(config.getEndpoint() + "installment/", entity, PaymentDto.class))
                .thenReturn(paymentDtoResponseEntity);

        //when
        client.payInstallment(paymentDto);

        //then
        verify(restTemplate, times(1))
                .postForEntity(config.getEndpoint() + "installment/", entity, PaymentDto.class);
    }

}