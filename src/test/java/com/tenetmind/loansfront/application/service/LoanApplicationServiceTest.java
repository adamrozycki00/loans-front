package com.tenetmind.loansfront.application.service;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.application.client.config.LoanApplicationConfiguration;
import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationMapper;
import com.tenetmind.loansfront.currency.client.CurrencyClient;
import com.tenetmind.loansfront.currency.client.config.CurrencyConfiguration;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.currency.service.CurrencyService;
import com.tenetmind.loansfront.currencyrate.client.CurrencyRateClient;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class LoanApplicationServiceTest {

    @Autowired
    private LoanApplicationService service;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private LoanApplicationClient client;

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private CurrencyRateClient currencyRateClient;

    @Autowired
    private LoanApplicationConfiguration config;

    @Test
    public void shouldGetApplications() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        List<LoanApplicationDto> loanApplicationDtoList = new ArrayList<>();
        loanApplicationDtoList.add(applicationDto);

        LoanApplication application = new LoanApplication(applicationDto);

        when(restTemplate.getForObject(config.getEndpoint(), LoanApplicationDto[].class))
                .thenReturn(new LoanApplicationDto[]{applicationDto});
        when(client.getApplicationDtos()).thenReturn(loanApplicationDtoList);
        when(currencyService.get(any(String.class))).thenReturn(currencyDto);

        //when
        LoanApplication applicationFromService = service.getAll().get(0);

        //then
        assertEquals(application.getId(), applicationFromService.getId());
    }

    @Test
    public void shouldUpdateApplicationWithStatusNewAndIdNotNull() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        LoanApplication application = new LoanApplication(applicationDto);

        when(client.updateApplication(any())).thenReturn(true);

        //when
        boolean result = service.save(application);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldSaveApplicationWithStatusNewAndIdNull() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(null, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        List<LoanApplicationDto> loanApplicationDtoList = new ArrayList<>();
        loanApplicationDtoList.add(applicationDto);

        LoanApplication application = new LoanApplication(applicationDto);

        when(client.createApplication(any())).thenReturn(true);

        //when
        boolean result = service.save(application);

        //then
        assertTrue(result);
    }

    @Test
    public void shouldAcceptApplicationWithStatusNew() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        List<LoanApplicationDto> loanApplicationDtoList = new ArrayList<>();
        loanApplicationDtoList.add(applicationDto);

        LoanApplication application = new LoanApplication(applicationDto);

        when(client.updateApplication(any())).thenReturn(true);
        when(currencyRateClient.checkForUpToDateRate(any())).thenReturn(true);

        //when
        boolean result = service.accept(application);

        //then
        assertTrue(result);
    }

}