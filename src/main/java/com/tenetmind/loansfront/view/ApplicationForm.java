package com.tenetmind.loansfront.view;

import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ApplicationForm extends FormLayout {

    private final MainView mainView;
    private final TextField date = new TextField("Date");
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField pesel = new TextField("PESEL");
    private final TextField currency = new TextField("Currency");
    private final TextField amount = new TextField("Amount");
    private final TextField period = new TextField("Period");
    private final TextField marginRate = new TextField("Margin rate");
    private final TextField status = new TextField("Status");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");

    private final Binder<LoanApplicationDto> binder = new Binder<>(LoanApplicationDto.class);

    public ApplicationForm(MainView mainView) {

        this.mainView = mainView;
        add(date, firstName, lastName, pesel, currency, amount, period, marginRate, status);
//        binder.bindInstanceFields(this);

    }
}
