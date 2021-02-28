package com.tenetmind.loansfront;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.service.LoanApplicationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class LoansFrontApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LoansFrontApplication.class, args);
//        LoanApplicationService service = (LoanApplicationService) context.getBean("loanApplicationService");
    }

}
