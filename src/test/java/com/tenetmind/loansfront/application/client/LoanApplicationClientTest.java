package com.tenetmind.loansfront.application.client;

import com.tenetmind.loansfront.application.client.config.LoanApplicationConfiguration;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanApplicationClientTest {

    @Autowired
    private LoanApplicationClient client;

    @Autowired
    private LoanApplicationConfiguration config;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void shouldGetApplicationDtos() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");
        LoanApplicationDto[] loanApplicationDtos = new LoanApplicationDto[]{applicationDto};

        when(restTemplate.getForObject(config.getEndpoint(), LoanApplicationDto[].class))
                .thenReturn(loanApplicationDtos);

        //when
        List<LoanApplicationDto> applicationDtosFromClient = client.getApplicationDtos();

        //then
        assertEquals(Arrays.asList(loanApplicationDtos), applicationDtosFromClient);
    }

    @Test
    public void shouldSendPostRequest() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        ResponseEntity<LoanApplicationDto> loanApplicationDtoResponseEntity =
                new ResponseEntity<>(HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<LoanApplicationDto> entity = new HttpEntity<>(applicationDto, headers);

        when(restTemplate.postForEntity(config.getEndpoint(), entity, LoanApplicationDto.class))
                .thenReturn(loanApplicationDtoResponseEntity);

        //when
        boolean applicationDtosFromClient = client.createApplication(applicationDto);

        //then
        assertTrue(applicationDtosFromClient);
    }

    @Test
    public void shouldSendPutRequest() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        ResponseEntity<LoanApplicationDto> loanApplicationDtoResponseEntity =
                new ResponseEntity<>(HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<LoanApplicationDto> entity = new HttpEntity<>(applicationDto, headers);

        when(restTemplate.exchange(
                config.getEndpoint(),
                PUT,
                entity,
                LoanApplicationDto.class,
                1))
                .thenReturn(loanApplicationDtoResponseEntity);

        //when
        boolean applicationDtosFromClient = client.updateApplication(applicationDto);

        //then
        assertTrue(applicationDtosFromClient);
    }

    @Test
    public void shouldSendDeleteRequest() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        ResponseEntity<LoanApplicationDto> loanApplicationDtoResponseEntity =
                new ResponseEntity<>(HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<LoanApplicationDto> entity = new HttpEntity<>(headers);

        when(restTemplate.exchange(
                config.getEndpoint() + applicationDto.getId(),
                DELETE,
                entity,
                LoanApplicationDto.class,
                1))
                .thenReturn(loanApplicationDtoResponseEntity);

        //when
        boolean applicationDtosFromClient = client.deleteApplication(applicationDto.getId());

        //then
        assertTrue(applicationDtosFromClient);
    }

}