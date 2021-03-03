package com.tenetmind.loansfront.application.domainmodel;

import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.currency.service.CurrencyService;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanApplicationMapperTest {

    @Autowired
    private LoanApplicationMapper mapper;

    @MockBean
    private CurrencyService currencyService;

    @Test
    public void shouldCreateDtoWithSamePropertiesAsThoseInInputApplication() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        LoanApplication application = new LoanApplication(applicationDto);
        when(currencyService.get(application.getCurrencyName().getName())).thenReturn(currencyDto);

        //when
        LoanApplicationDto newApplicationDto = mapper.mapToDto(application);

        //then
        assertEquals(applicationDto.getId(), newApplicationDto.getId());
        assertEquals(applicationDto.getAmount(), newApplicationDto.getAmount());
        assertEquals(applicationDto.getCurrencyDto().getName(), newApplicationDto.getCurrencyDto().getName());
        assertEquals(applicationDto.getCustomerDto().getLastName(), newApplicationDto.getCustomerDto().getLastName());
    }


}