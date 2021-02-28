package com.tenetmind.loansfront.view;

import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.service.LoanApplicationService;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import com.tenetmind.loansfront.loan.service.LoanService;
import com.vaadin.flow.component.Text;
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
    private final ApplicationForm applicationForm = new ApplicationForm(this);

    private final Grid<LoanApplication> applicationGrid = new Grid<>();
    private final Grid<LoanDto> loanGrid = new Grid<>();

    @Autowired
    public MainView(LoanApplicationService applicationService, LoanService loanService) {

        this.applicationService = applicationService;
        this.loanService = loanService;

        Text applicationText = new Text("LOAN APPLICATIONS");
        Text loanText = new Text("LOANS");

        prepareApplicationGrid();
        prepareLoanGrid(loanService);

        VerticalLayout applicationLayout = new VerticalLayout(applicationText, applicationGrid);
        VerticalLayout loanLayout = new VerticalLayout(loanText, loanGrid);

        VerticalLayout mainContent = new VerticalLayout(applicationLayout, loanLayout);
        mainContent.setSizeFull();
        applicationLayout.setSizeFull();
        loanLayout.setSizeFull();
        applicationGrid.setSizeFull();
        loanGrid.setSizeFull();

        add(mainContent);
        setSizeFull();

        refresh();
    }

    private void prepareApplicationGrid() {
        applicationGrid.addColumn(LoanApplication::getDateString).setHeader("Date");
        applicationGrid.addColumn(LoanApplication::getFirstName).setHeader("First name");
        applicationGrid.addColumn(LoanApplication::getLastName).setHeader("Last name");
        applicationGrid.addColumn(LoanApplication::getPesel).setHeader("PESEL");
        applicationGrid.addColumn(LoanApplication::getCurrency).setHeader("Currency");
        applicationGrid.addColumn(LoanApplication::getAmount).setHeader("Amount");
        applicationGrid.addColumn(LoanApplication::getPeriod).setHeader("Period");
        applicationGrid.addColumn(LoanApplication::getMarginRate).setHeader("Margin rate");
        applicationGrid.addColumn(LoanApplication::getStatus).setHeader("Status");
    }

    private void prepareLoanGrid(LoanService loanService) {
        loanGrid.addColumn(loan -> loan.getDate().toLocalDate()).setHeader("Date");
        loanGrid.addColumn(loan -> loan.getCustomerDto().getFirstName()).setHeader("First name");
        loanGrid.addColumn(loan -> loan.getCustomerDto().getLastName()).setHeader("Last name");
        loanGrid.addColumn(loan -> loan.getCurrencyDto().getName()).setHeader("Currency");
        loanGrid.addColumn(LoanDto::getAmount).setHeader("Amount");
        loanGrid.addColumn(LoanDto::getBalance).setHeader("Balance");
        loanGrid.addColumn(loanService::getAmountOfNextInstallment).setHeader("Next installment");
        loanGrid.addColumn(LoanDto::getStatus).setHeader("Status");
    }

    public void refresh() {
        applicationGrid.setItems(applicationService.getAll());
        loanGrid.setItems(loanService.getAll());
    }

}
