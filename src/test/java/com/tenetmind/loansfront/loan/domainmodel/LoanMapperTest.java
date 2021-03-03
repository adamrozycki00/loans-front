package com.tenetmind.loansfront.loan.domainmodel;

import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationMapper;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.tenetmind.loansfront.currency.service.CurrencyService;
import com.tenetmind.loansfront.customer.domainmodel.CustomerDto;
import com.tenetmind.loansfront.installment.domainmodel.InstallmentDto;
import com.tenetmind.loansfront.operation.domainmodel.OperationDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanMapperTest {

    @Autowired
    private LoanMapper mapper;

    @MockBean
    private CurrencyService currencyService;

    @Test
    public void shouldCreateDtoWithSamePropertiesAsThoseInInputLoan() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        LoanApplication application = new LoanApplication(applicationDto);

        Loan loan = new Loan(
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().toString(),
                applicationDto,
                customerDto,
                application.getFirstName(),
                application.getLastName(),
                currencyDto,
                currencyDto.getName(),
                applicationDto.getAmount(),
                applicationDto.getAmount().toString(),
                applicationDto.getPeriod(),
                new BigDecimal("0.05"),
                applicationDto.getMarginRate(),
                BigDecimal.ZERO,
                BigDecimal.ZERO.toString(),
                new BigDecimal("100").toString(),
                new BigDecimal("1100"),
                0,
                "0",
                "New",
                new ArrayList<>(),
                new ArrayList<>());

        //when
        LoanDto loanDto = mapper.mapToDto(loan);

        //then
        assertEquals(loan.getId(), loanDto.getId());
        assertEquals(loan.getAmount(), loanDto.getAmount());
        assertEquals(loan.getCurrencyDto().getName(), loanDto.getCurrencyDto().getName());
        assertEquals(loan.getCustomerDto().getLastName(), loanDto.getCustomerDto().getLastName());
    }

    @Test
    public void shouldCreateLoanWithSamePropertiesAsThoseInInputDto() {
        //given
        CustomerDto customerDto = new CustomerDto(1L, "John", "Smith", "12345");
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR");

        LoanApplicationDto applicationDto = new LoanApplicationDto(1L, LocalDateTime.now(), customerDto,
                currencyDto, new BigDecimal("1000"), 12, new BigDecimal(".05"), "New");

        LoanApplication application = new LoanApplication(applicationDto);

        LoanDto loanDto = new LoanDto(
                1L,
                LocalDateTime.now(),
                applicationDto,
                customerDto,
                currencyDto,
                applicationDto.getAmount(),
                applicationDto.getPeriod(),
                new BigDecimal("0.05"),
                applicationDto.getMarginRate(),
                BigDecimal.ZERO,
                new BigDecimal("1100"),
                0,
                "New",
                new ArrayList<>(),
                new ArrayList<>());

        //when
        Loan loan = mapper.mapFromDto(loanDto);

        //then
        assertEquals(loanDto.getId(), loan.getId());
        assertEquals(loanDto.getAmount(), loan.getAmount());
        assertEquals(loanDto.getCurrencyDto().getName(), loan.getCurrencyDto().getName());
        assertEquals(loanDto.getCustomerDto().getLastName(), loan.getCustomerDto().getLastName());
    }

}