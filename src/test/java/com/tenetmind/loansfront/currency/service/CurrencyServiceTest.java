package com.tenetmind.loansfront.currency.service;

import com.tenetmind.loansfront.currency.client.CurrencyClient;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class CurrencyServiceTest {

    @Autowired
    private CurrencyService service;

    @MockBean
    private CurrencyClient client;

    @Test
    public void shouldGetCurrencyDto() {
        //given
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        when(client.getCurrencyDtos()).thenReturn(Arrays.asList(currencyDto));

        //when
        List<CurrencyDto> resultList = service.getAll();

        //then
        assertFalse(resultList.isEmpty());
    }

}