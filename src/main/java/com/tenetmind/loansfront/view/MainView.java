package com.tenetmind.loansfront.view;

import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.service.LoanApplicationService;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import com.tenetmind.loansfront.loan.service.LoanService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Route
@Component
@Scope("prototype")
public class MainView extends VerticalLayout {

    private final LoanApplicationService applicationService;
    private final LoanService loanService;

    private final Grid<LoanApplicationDto> applicationGrid = new Grid<>();
    private final Grid<LoanDto> loanGrid = new Grid<>();

    @Autowired
    public MainView(LoanApplicationService applicationService, LoanService loanService) {

        this.applicationService = applicationService;
        this.loanService = loanService;

        applicationGrid.addColumn(appl -> appl.getDate().toLocalDate()).setHeader("Date");
        applicationGrid.addColumn(appl -> appl.getCustomerDto().getFirstName()).setHeader("First name");
        applicationGrid.addColumn(appl -> appl.getCustomerDto().getLastName()).setHeader("Last name");
        applicationGrid.addColumn(appl -> appl.getCustomerDto().getPesel()).setHeader("PESEL");
        applicationGrid.addColumn(appl -> appl.getCurrencyDto().getName()).setHeader("Currency");
        applicationGrid.addColumn(LoanApplicationDto::getAmount).setHeader("Amount");
        applicationGrid.addColumn(LoanApplicationDto::getPeriod).setHeader("Period");
        applicationGrid.addColumn(LoanApplicationDto::getMarginRate).setHeader("Margin rate");
        applicationGrid.addColumn(LoanApplicationDto::getStatus).setHeader("Status");

        loanGrid.addColumn(loan -> loan.getDate().toLocalDate()).setHeader("Date");
        loanGrid.addColumn(loan -> loan.getCustomerDto().getFirstName()).setHeader("First name");
        loanGrid.addColumn(loan -> loan.getCustomerDto().getLastName()).setHeader("Last name");
        loanGrid.addColumn(loan -> loan.getCurrencyDto().getName()).setHeader("Currency");
        loanGrid.addColumn(LoanDto::getAmount).setHeader("Amount");
        loanGrid.addColumn(LoanDto::getBalance).setHeader("Balance");
        loanGrid.addColumn(loanService::getAmountOfNextInstallment).setHeader("Next installment");
        loanGrid.addColumn(LoanDto::getStatus).setHeader("Status");

        VerticalLayout mainContent = new VerticalLayout(applicationGrid, loanGrid);
        mainContent.setSizeFull();
        applicationGrid.setSizeFull();
        loanGrid.setSizeFull();

        add(mainContent);
        setSizeFull();

        refresh();
    }

    public void refresh() {
        applicationGrid.setItems(applicationService.getAll());
        loanGrid.setItems(loanService.getAll());
    }
}
