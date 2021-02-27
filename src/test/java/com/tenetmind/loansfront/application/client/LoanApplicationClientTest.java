package com.tenetmind.loansfront.application.client;

import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationMapper;
import com.tenetmind.loansfront.currency.domainmodel.Currency;
import com.tenetmind.loansfront.customer.domainmodel.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
public class LoanApplicationClientTest {

    @Autowired
    private LoanApplicationClient client;

    @Autowired
    private LoanApplicationMapper mapper;

    public void test() {
        //given
        Customer customer = new Customer("John", "Smith", "12345");
        Currency pln = new Currency("PLN");
        LoanApplication application = new LoanApplication(LocalDateTime.now(), customer, pln,
                new BigDecimal("1000"), 12, new BigDecimal(".05"));

        //when
        boolean result = client.createApplication(mapper.mapToDto(application));
        System.out.println(result);
    }

    public void testGet(Long id) {
        client.getApplicationDto(id);
    }


}