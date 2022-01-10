package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

@Route(value = "tHome", layout = TeacherDashbord.class)
public class TeacherHome extends VerticalLayout {
    ArrayList<String> _tacherDet;
    Controller getTeacher = Controller.getInstance();
    H3 _head;
    Hr _line;
    Hr _contentDivide;
    Label Value1;
    Label Value2;
    Hr _mid;
    String teacherID = ComponentUtil.getData(UI.getCurrent(), "teacherID").toString();
    ArrayList<String[]> teacherCourses = new ArrayList<>();
    VerticalLayout LAYOUT = new VerticalLayout();

    public TeacherHome() {
        _tacherDet = getTeacher.getTeacherDetails(teacherID);
        _head = new H3("Welcome " + _tacherDet.get(0));
        _line = new Hr();
        LAYOUT.add(_head, _line);
        LAYOUT.setAlignItems(Alignment.CENTER);
        add(LAYOUT);
        _line = new Hr();
        _line.setWidth("50%");
        _line.setId("chch");
        LAYOUT.add(new H4("Courses --- Number of Enrolled Students"), _line);
        LAYOUT.setAlignItems(Alignment.CENTER);
        add(LAYOUT);
        teacherCourses = getTeacher.getTeacherCourse(teacherID);
        for (int i = 0; i < teacherCourses.size(); i++) {
            _contentDivide = new Hr();
            _contentDivide.setWidth("50%");
            _contentDivide.setId("chch");
            Value1 = new Label();
            Value2 = new Label();
            String dash = " ";
            for (int j = 0; j < (60 - teacherCourses.get(i)[0].length()); j++)
                dash += "_";
            dash += " ";
            Value1.setText(teacherCourses.get(i)[0] + dash + teacherCourses.get(i)[1]);
            Value2.setText(teacherCourses.get(i)[1]);
            _mid = new Hr();
            _mid.setWidth("150px");
            LAYOUT.add(Value1, _contentDivide);
            LAYOUT.setAlignItems(Alignment.CENTER);
            add(LAYOUT);
        }
    }
}
