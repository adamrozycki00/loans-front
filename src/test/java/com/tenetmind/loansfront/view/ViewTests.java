package com.tenetmind.loansfront.view;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.loan.client.LoanClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class ViewTests {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private LoanApplicationClient loanApplicationClient;

    @MockBean
    private LoanClient loanClient;

    @Test
    void shouldHaveCreatedMainView() {
        //given & when
        Object mainView = context.getBean("mainView");
        when(loanApplicationClient.getApplicationDtos()).thenReturn(new ArrayList<>());
        when(loanClient.getLoanDtos()).thenReturn(new ArrayList<>());

        //then
        assertNotNull(mainView);
    }

    @Test
    void shouldHaveCreatedApplicationForm() {
        //given
        MainView mainView = (MainView) context.getBean("mainView");
        when(loanApplicationClient.getApplicationDtos()).thenReturn(new ArrayList<>());
        when(loanClient.getLoanDtos()).thenReturn(new ArrayList<>());

        //when
        ApplicationForm applicationForm = mainView.getApplicationForm();

        //then
        assertNotNull(applicationForm);
    }

    @Test
    void shouldHaveCreatedLoanForm() {
        //given
        MainView mainView = (MainView) context.getBean("mainView");
        when(loanApplicationClient.getApplicationDtos()).thenReturn(new ArrayList<>());
        when(loanClient.getLoanDtos()).thenReturn(new ArrayList<>());

        //when
        LoanForm loanForm = mainView.getLoanForm();

        //then
        assertNotNull(loanForm);
    }

}
