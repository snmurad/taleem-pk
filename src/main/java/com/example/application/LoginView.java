package com.example.application;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.PasswordField;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import javax.swing.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.awt.*;


@Route("login")
@PageTitle("Taleem.Pk")

@Theme(value = Lumo.class, variant = Lumo.DARK)

public class LoginView extends Composite<VerticalLayout>
{

    Button teacher_Button;
    Button student_Button;

TextField Email=new TextField();
PasswordField Password;

    LoginOverlay loginOverlay = new LoginOverlay();
    @Email
    @NotEmpty

 public LoginView()
 {


     getContent().add(



             new H1(" Log In "),
             Email=new TextField("Email"),

             //t = new EmailField("Email:"),
            Password=new PasswordField("Password:"),
              teacher_Button=  new Button("Sign in As Teacher",buttonClickEvent -> {

                  UI.getCurrent().navigate(DialogBasic.class);
              }),
             new H5("OR"),
             student_Button =  new Button("Sign in As Student"),
             new H5("Don't have an account, "),
             new RouterLink("Create One",Registeration.class)

     );

     Email.setPlaceholder("Someone@gmail.com");
     Email.setRequired(true);
     Email.setHelperText("*Valid email");
     Email.setAutofocus(true);
     Email.setWidth("300px");
     Password.setRequired(true);
     Password.setWidth("300px");

    // UI.getCurrent().navigate(Registeration.class);
    getContent().setMargin(true);
    getContent().setAlignItems(FlexComponent.Alignment.CENTER);

 }
}
