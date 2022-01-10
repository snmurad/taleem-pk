package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.Route;

@Route(value = "pCourses", layout = StudentSideBar.class)
public class PurchasedCourses extends VerticalLayout {
    H3 _head;
    Button course;
    String sID = ComponentUtil.getData(UI.getCurrent(), "studentID").toString(); // Have to be Dynamic
    Controller _c = Controller.getInstance();
    String[] cIDS;
    String[] cNames;
    VerticalLayout LAYOUT = new VerticalLayout();

    public PurchasedCourses() {
        // Having all the Purchased Courses
        cIDS = new String[20];
        cNames = new String[20];
        cIDS = _c.getPurchasedCourseIds(sID);
        cNames = _c.getPurchasedCourseNames();

        _head = new H3("My Courses");
        LAYOUT.add(_head);
        LAYOUT.setAlignItems(Alignment.CENTER);
        add(LAYOUT);
        for (int i = 0; cIDS[i] != null; i++) {
            int finalI = i;
            course = new Button(cNames[i], buttonClickEvent -> {
                // get the course selected from cNames[finalI]
                ComponentUtil.setData(UI.getCurrent(), "courseSelected", cIDS[finalI]);
                UI.getCurrent().navigate(CourseInfo.class);
            });

            course.setWidth("600px");
            course.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
            LAYOUT.add(course);
            LAYOUT.setAlignItems(Alignment.CENTER);
            add(LAYOUT);
        }

    }
}
