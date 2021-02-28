package com.tenetmind.loansfront.view;

import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.service.LoanApplicationService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Route
@Component
public class MainView extends VerticalLayout {

    private final LoanApplicationService applicationService;

    private final Grid<LoanApplication> grid = new Grid<>();

    @Autowired
    public MainView(LoanApplicationService applicationService) {

        this.applicationService = applicationService;

        grid.addColumn(LoanApplication::getId).setHeader("No.");
        grid.addColumn(appl -> appl.getDate().toLocalDate()).setHeader("Date");
        grid.addColumn(LoanApplication::getFirstName).setHeader("First name");
        grid.addColumn(LoanApplication::getLastName).setHeader("Last name");
        grid.addColumn(LoanApplication::getPesel).setHeader("PESEL");
        grid.addColumn(LoanApplication::getCurrency).setHeader("Currency");
        grid.addColumn(LoanApplication::getAmount).setHeader("Amount");
        grid.addColumn(LoanApplication::getPeriod).setHeader("Period");
        grid.addColumn(LoanApplication::getMarginRate).setHeader("Margin rate");
        grid.addColumn(LoanApplication::getStatus).setHeader("Status");

        add(grid);
        setSizeFull();

        refresh();

    }

    public void refresh() {
        grid.setItems(applicationService.getAll());
    }
}
