package com.tenetmind.loansfront.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ApplicationForm extends FormLayout {

//    private final TextField title = new TextField("Title");
//    private final TextField author = new TextField("Author");
//    private final TextField publicationYear = new TextField("Publication year");
//    private final ComboBox<BookType> type = new ComboBox<>("Book type");
//
//    private final Button save = new Button("Save");
//    private final Button delete = new Button("Delete");
//
//    private final Binder<Book> binder = new Binder<>(Book.class);
//
//    private final BookService service = BookService.getInstance();
//
//    private final CustomerPanel customerPanel;
//
//    public ApplicationForm(CustomerPanel customerPanel) {
//        this.customerPanel = customerPanel;
//        type.setItems(BookType.values());
//        HorizontalLayout buttons = new HorizontalLayout(save, delete);
//        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        add(title, author, publicationYear, type, buttons);
//        binder.bindInstanceFields(this);
//        save.addClickListener(event -> save());
//        delete.addClickListener(event -> delete());
//    }
//
//    public void setBook(Book book) {
//        binder.setBean(book);
//
//        if (book == null) {
//            setVisible(false);
//        } else {
//            setVisible(true);
//            title.focus();
//        }
//    }
//
//    private void save() {
//        Book book = binder.getBean();
//        service.save(book);
//        customerPanel.refresh();
//        setBook(null);
//    }
//
//    private void delete() {
//        Book book = binder.getBean();
//        service.delete(book);
//        customerPanel.refresh();
//        setBook(null);
//    }

}
