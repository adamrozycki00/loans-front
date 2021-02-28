package com.tenetmind.loansfront.view;

import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyName;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ApplicationForm extends FormLayout {

    private final MainView mainView;
    private final Text id = new Text("No.");
    private final TextField dateString = new TextField("Date");
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField pesel = new TextField("PESEL");
    private final ComboBox<CurrencyName> currencyName = new ComboBox<>("Currency");
    private final TextField amount = new TextField("Amount");
    private final TextField period = new TextField("Period");
    private final TextField marginRate = new TextField("Margin rate");
    private final TextField status = new TextField("Status");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");

    private final Binder<LoanApplication> binder = new Binder<>(LoanApplication.class);

    public ApplicationForm(MainView mainView) {
        this.mainView = mainView;
        currencyName.setItems(CurrencyName.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id, firstName, lastName, pesel, currencyName, amount, period, marginRate, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
    }

    public void setApplication(LoanApplication application) {
        binder.setBean(application);

        if (application == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstName.focus();
        }
    }

    private void save() {
        LoanApplication application = binder.getBean();
        mainView.getApplicationService().save(application);
        mainView.refresh();
        setApplication(null);
    }

    private void delete() {
        LoanApplication application = binder.getBean();
        mainView.getApplicationService().delete(application);
        mainView.refresh();
        setApplication(null);
    }

}
