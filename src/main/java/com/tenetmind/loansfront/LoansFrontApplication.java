package com.tenetmind.loansfront;

import com.tenetmind.loansfront.currency.client.CurrencyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LoansFrontApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LoansFrontApplication.class, args);
        CurrencyClient currencyClient = (CurrencyClient) context.getBean("currencyClient");
        boolean result = currencyClient.deleteCurrency(5L);
        System.out.println("result: " + result);
    }

}
