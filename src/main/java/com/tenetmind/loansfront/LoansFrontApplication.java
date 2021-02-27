package com.tenetmind.loansfront;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LoansFrontApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LoansFrontApplication.class, args);
        LoanApplicationClient applicationClient = (LoanApplicationClient) context.getBean("loanApplicationClient");
        LoanApplicationDto dto = applicationClient.getApplicationDto(4L);
        dto.setStatus("Accepted");
        System.out.println(dto);
        boolean result = applicationClient.updateApplication(dto);
        System.out.println("result: " + result);
    }

}
