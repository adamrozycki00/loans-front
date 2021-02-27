package com.tenetmind.loansfront.operation.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

@SpringBootTest
public class PaymentClientTest {

    @Autowired
    private PaymentClient client;

    public boolean testMakeLoan() {
        PaymentDto paymentDto = new PaymentDto(LocalDate.now().minusDays(1), 5L, null, null);
        return client.makeLoan(paymentDto);
    }

    public boolean testPayInstallment() {
        PaymentDto paymentDto = new PaymentDto(LocalDate.now().minusDays(1), 4L, "GBP",
                new BigDecimal("100.00"));
        return client.payInstallment(paymentDto);
    }

}