package com.example.application;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.management.Notification;
import java.awt.*;


@Route("Registration")

public class Registeration extends Composite<VerticalLayout>{

VerticalLayout layout=new VerticalLayout();

TextField Name=new TextField();
   // TextField alignCenterTextField = new TextField();

EmailField Email=new EmailField();
NumberField contact=new NumberField();
PasswordField Password=new PasswordField();
PasswordField confirm_Password=new PasswordField();
DatePicker dob=new DatePicker();


//VerticalLayout layout=new VerticalLayout();

   // ComboBox cb;
    VerticalLayout cb=new VerticalLayout();

@Override
    public VerticalLayout initContent() {



                 Name = new TextField("Name:");

       // Name.setValue("Align center");
       // Name.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

                 Name.setPlaceholder("First Name");
                 Name.setRequired(true);
                 Name.setAutofocus(true);
                 Name.setWidth("250px");

                Email = new EmailField("Email");
                Email.setPlaceholder("enter Mail Id");
               // Email.setRequired(true);
              //  Email.setAutofocus(true);
                Email.setWidth("250px");

        Password = new PasswordField("Password:");
        Password.setPlaceholder("Password");
        Password.setWidth("250px");
        Password.setRequired(true);

        confirm_Password = new PasswordField("Confirm_Password");
        confirm_Password.setPlaceholder("Re_EnterPassword");
        confirm_Password.setWidth("250px");
        confirm_Password.setRequired(true);

                contact = new NumberField("Contact");
                contact.setPlaceholder("03123456789");
                contact.setWidth("250px");
                contact.setPattern("0-9");
                contact.setErrorMessage("Only Numbers are allowed");

             //   Password.getValue();




                dob = new DatePicker("Date of Birth");
                dob.setRequired(true);
                dob.setWidth("250px");




    ComboBox<String> comboBox = new ComboBox<>("Browser");
    comboBox.setAllowCustomValue(true);
    //add(comboBox);

    comboBox.setItems("Chrome", "Edge", "Firefox", "Safari");
    comboBox.setHelperText("Select or type a browser");

    cb.add(comboBox);

                VerticalLayout layout = new VerticalLayout(

                             new H1("Welcome To Teacher Registration Form"),

                          new HorizontalLayout(Name,Email),
                            new HorizontalLayout(Password,confirm_Password),
                            new HorizontalLayout(contact,dob)


        );
                layout.setAlignItems(FlexComponent.Alignment.CENTER);
                return layout;


    }
//

//private static HorizontalLayout testfunction(TextField Name, TextField Email)
//{
////    Email.setPlaceholder("Someone@gmail.com");
////    Email.setWidth();
//    Email.setRequired(true);
//
//    HorizontalLayout h_layout1= new HorizontalLayout(Name,Email);
//    return h_layout1;
//}

}

