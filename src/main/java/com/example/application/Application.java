package com.example.application;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.router.Route;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

import java.awt.*;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
//@Route("application")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args)
    {
       // LoginView caller=new LoginView();

       // Creat_account caller=new Creat_account();
      //  caller.first_dialogue();
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
//        UI.getCurrent().navigate(LoginView.class);

    }

}
