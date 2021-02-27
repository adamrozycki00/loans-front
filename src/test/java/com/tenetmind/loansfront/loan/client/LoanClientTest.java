package com.tenetmind.loansfront.loan.client;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationMapper;
import com.tenetmind.loansfront.loan.domainmodel.Loan;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import com.tenetmind.loansfront.loan.domainmodel.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class LoanClientTest {

    @Autowired
    private LoanClient client;

    @Autowired
    private LoanMapper mapper;

    @Autowired
    private LoanApplicationClient applicationClient;

    @Autowired
    LoanApplicationMapper applicationMapper;

    public void test() {
        //given
        LoanApplicationDto applicationDto = applicationClient.getApplicationDto(3L);
        Loan loan = new Loan(LocalDateTime.now(), applicationMapper.mapToExistingEntity(applicationDto),
                new BigDecimal(".05"));

        //when
        LoanDto loanDto = mapper.mapToDto(loan);
        System.out.println(loanDto);
        boolean result = client.createLoan(loanDto);

        //then
        System.out.println(result);
    }

    public void testGetLoanDtos() {
        List<LoanDto> loanDtos = client.getLoanDtos();
        System.out.println(loanDtos);
    }

}