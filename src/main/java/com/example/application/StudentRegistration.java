package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.Composite;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;

import com.vaadin.flow.component.html.H1;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.validation.constraints.NotEmpty;
import java.time.format.DateTimeFormatter;

@Route("sRegistration")
public class StudentRegistration extends Composite<VerticalLayout> {

    VerticalLayout layout = new VerticalLayout();

    @NotEmpty TextField Name = new TextField();
    @NotEmpty EmailField Email = new EmailField();
    @NotEmpty TextField contact = new TextField();
    @NotEmpty PasswordField Password = new PasswordField();
    @NotEmpty PasswordField confirm_Password = new PasswordField();

    DatePicker dob = new DatePicker();
    ComboBox<String> comboBox = new ComboBox<>("Enroll in Class");
    Controller toRegStudent = Controller.getInstance();

    VerticalLayout cb = new VerticalLayout();

    @Override
    public VerticalLayout initContent() {

        Name = new TextField("Name");
        Name.setPlaceholder("Your Name");
        Name.setRequired(true);
        Name.setAutofocus(true);
        Name.setWidth("250px");

        Email = new EmailField("Email");
        Email.setPlaceholder("xyz@mail.com");
        Email.setRequiredIndicatorVisible(true);
        Email.setWidth("250px");

        Password = new PasswordField("Password");
        Password.setPlaceholder("********");
        Password.setWidth("250px");
        Password.setRequired(true);

        confirm_Password = new PasswordField("Confirm Password");
        confirm_Password.setPlaceholder("********");
        confirm_Password.setWidth("250px");
        confirm_Password.setRequired(true);

        contact = new TextField("Contact");
        contact.setPlaceholder("+923123456789");
        contact.setRequiredIndicatorVisible(true);
        contact.setWidth("250px");

        Button register = new Button("Continue", buttonClickEvent -> {
            System.out.println("Contact Value" + contact.getValue() + "\n");
            System.out.println("DOB Value" + dob.getValue());
            toRegStudent.registerStudent(Name.getValue(), Email.getValue(), Password.getValue(), contact.getValue(), dob.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yy")), comboBox.getValue());
            UI.getCurrent().navigate(LoginView.class);
        });

        register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        register.setWidth("300px");

        dob = new DatePicker("Date of Birth");
        dob.setRequired(true);
        dob.setWidth("250px");

        TextField specialization = new TextField("Subject");
        specialization.setWidth("250px");
        specialization.setPlaceholder("e.g. Computer Sciences ");

        comboBox.setAllowCustomValue(true);
        comboBox.setItems("9th", "10th", "11th", "12th", "Bachelors");
        comboBox.setRequired(true);
        comboBox.setWidth("250px");

        VerticalLayout layout = new VerticalLayout(new H1("Student Registration Form"), new HorizontalLayout(Name, Email), new HorizontalLayout(Password, confirm_Password), new HorizontalLayout(contact, dob), new HorizontalLayout(comboBox, specialization), new HorizontalLayout(register));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        return layout;
    }
}

