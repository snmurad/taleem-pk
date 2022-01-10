package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.router.Route;

@Route(value = "newCourses", layout = StudentSideBar.class)
public class StudentDashbord extends HorizontalLayout {

    String studentID = ComponentUtil.getData(UI.getCurrent(), "studentID").toString();
    String[] courseDet = new String[6];
    int refer = 1;

    Button enroll;
    Button next;

    Controller _c = Controller.getInstance();

    public StudentDashbord() {
        courseDet = _c.getCourses(refer);
        refer++;
        draw();
    }

    public void draw() {
        TextArea coursetitle = new TextArea();
        coursetitle.getStyle().set("5", "var(--lumo-space-l) 0 0 0").set("font-size", "3em").set("font-weight", "bold");
        coursetitle.setHeight("150px");
        coursetitle.setWidth("610px");
        coursetitle.setValue(courseDet[0]);
        coursetitle.setReadOnly(true);
        coursetitle.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);

        TextArea no_of_chapters = new TextArea();
        no_of_chapters.getStyle().set("5", "var(--lumo-space-l) 0 0 0").set("font-size", "1em").set("font-weight", "bold");
        no_of_chapters.setHeight("55px");
        no_of_chapters.setWidth("190px");
        no_of_chapters.setValue("No. of Videos: " + courseDet[5]);
        no_of_chapters.setReadOnly(true);
        no_of_chapters.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);

        TextArea pricefield = new TextArea();
        pricefield.getStyle().set("5", "var(--lumo-space-l) 0 0 0").set("font-size", "1em").set("font-weight", "bold");
        pricefield.setHeight("55px");
        pricefield.setWidth("190px");
        pricefield.setValue("Price: " + courseDet[1]);
        pricefield.setReadOnly(true);
        pricefield.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);

        TextArea teacher_name = new TextArea();
        teacher_name.getStyle().set("5", "var(--lumo-space-l) 0 0 0").set("font-size", "1em").set("font-weight", "bold");
        teacher_name.setHeight("55px");
        teacher_name.setWidth("190px");
        teacher_name.setValue(courseDet[2]);
        teacher_name.setReadOnly(true);
        teacher_name.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);

        TextArea subjectarea = new TextArea();
        subjectarea.setHeight("260px");
        subjectarea.setWidth("310px");
        subjectarea.setValue("\nBy\n" + courseDet[3] + "\n" + "(" + courseDet[4] + ")");
        subjectarea.setReadOnly(true);
        subjectarea.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
        subjectarea.getStyle().set("5", "var(--lumo-space-l) 0 0 0").set("font-size", "2em").set("font-weight", "bold");

        TextArea description_field = new TextArea();
        description_field.setHeight("140px");
        description_field.setWidth("960px");
        description_field.setValue(courseDet[3] + " is a well known tutor for the field of " + courseDet[0] + ". Creates and delivers engaging lessons to diverse groups of students at all levels. Promotes enthusiasm for learning and for subjects. Adheres to national curriculum standards. Collects and reports on correct and detailed records of student performance.");
        description_field.setReadOnly(true);
        description_field.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
        description_field.getStyle().set("5", "var(--lumo-space-xxl) 0 0 0").set("font-size", "1em").set("font-weight", "italic");

        enroll = new Button("Enroll", buttonClickEvent -> {
            if (_c.purchaseCourse(studentID, String.valueOf(--refer))) UI.getCurrent().navigate(PurchasedCourses.class);
            else System.out.println("Error Purchasing Course");
        });

        enroll.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        enroll.setAutofocus(true);
        enroll.setWidth("200px");
        enroll.setHeight("50px");

        next = new Button(">", buttonClickEvent -> {
            courseDet = _c.getCourses(refer);
            refer++;
            if (courseDet[0] != "-1") {
                coursetitle.setValue(courseDet[0]);
                no_of_chapters.setValue("No. of Videos: " + courseDet[5]);
                pricefield.setValue("Price: " + courseDet[1]);
                teacher_name.setValue(courseDet[2]);
                subjectarea.setValue("\nBy\n" + courseDet[3] + "\n" + "(" + courseDet[4] + ")");
                description_field.setValue("desc");
            }
        });
        next.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_SMALL);
        HorizontalLayout layout1 = new HorizontalLayout(no_of_chapters, pricefield, teacher_name);//price and no of chapter fields
        VerticalLayout layout2 = new VerticalLayout(coursetitle, layout1);
        HorizontalLayout layout_subject = new HorizontalLayout(layout2, subjectarea);
        VerticalLayout final_layout = new VerticalLayout(layout_subject, description_field, enroll, next);
        final_layout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(final_layout);
    }
}




