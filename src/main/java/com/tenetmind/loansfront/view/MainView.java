package com.tenetmind.loansfront.view;

import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.service.LoanApplicationService;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import com.tenetmind.loansfront.loan.service.LoanService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

    private final Text applicationText = new Text("LOAN APPLICATIONS");
    private final Text loanText = new Text("LOANS");

    private final Button newApplication = new Button("New application");

    @Autowired
    public MainView(LoanApplicationService applicationService, LoanService loanService) {

        this.applicationService = applicationService;
        this.loanService = loanService;

        prepareApplicationGrid();
        prepareLoanGrid(loanService);

        newApplication.addClickListener(e -> {
            applicationGrid.asSingleSelect().clear();
            applicationForm.setApplication(new LoanApplication());
        });

        VerticalLayout applicationToolBar = new VerticalLayout(applicationText, newApplication);
        VerticalLayout loanToolBar = new VerticalLayout(loanText);

        HorizontalLayout applicationContent = new HorizontalLayout(applicationGrid, applicationForm);
        HorizontalLayout loanContent = new HorizontalLayout(loanGrid);

        applicationContent.setSizeFull();
        loanContent.setSizeFull();
        applicationGrid.setSizeFull();
        loanGrid.setSizeFull();

        add(applicationToolBar, applicationContent, loanToolBar, loanContent);
        applicationForm.setApplication(null);

        setSizeFull();

        refresh();

        applicationGrid.asSingleSelect().addValueChangeListener(e -> applicationForm.setApplication(
                applicationGrid.asSingleSelect().getValue()
        ));

    }

    public LoanApplicationService getApplicationService() {
        return applicationService;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    private void prepareApplicationGrid() {
        applicationGrid.addColumn(LoanApplication::getId).setHeader("No.");
        applicationGrid.addColumn(LoanApplication::getDateString).setHeader("Date");
        applicationGrid.addColumn(LoanApplication::getFirstName).setHeader("First name");
        applicationGrid.addColumn(LoanApplication::getLastName).setHeader("Last name");
        applicationGrid.addColumn(LoanApplication::getPesel).setHeader("PESEL");
        applicationGrid.addColumn(LoanApplication::getCurrencyName).setHeader("Currency");
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
