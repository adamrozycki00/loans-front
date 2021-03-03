package com.tenetmind.loansfront;

import com.tenetmind.loansfront.view.ApplicationForm;
import com.tenetmind.loansfront.view.LoanForm;
import com.tenetmind.loansfront.view.MainView;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class LoansFrontApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldHaveCreatedMainView() {
        //given & when
        Object mainView = context.getBean("mainView");

        //then
        assertNotNull(mainView);
    }

    @Test
    void shouldHaveCreatedApplicationForm() {
        //given
        MainView mainView = (MainView) context.getBean("mainView");

        //when
        ApplicationForm applicationForm = mainView.getApplicationForm();

        //then
        assertNotNull(applicationForm);
    }

    @Test
    void shouldHaveCreatedLoanForm() {
        //given
        MainView mainView = (MainView) context.getBean("mainView");

        //when
        LoanForm loanForm = mainView.getLoanForm();

        //then
        assertNotNull(loanForm);
    }

}
