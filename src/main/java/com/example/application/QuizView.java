package com.example.application;

import ch.qos.logback.core.Layout;
import com.example.application.backend.Quiz;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route(value="quiz")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)

public class QuizView extends VerticalLayout {
    ComboBox courses = new ComboBox();
    ComboBox chapters = new ComboBox();
    Button submit;
    Quiz quizOBJ = new Quiz(ComponentUtil.getData(UI.getCurrent(), "teacherID").toString());   // Have to be Dynamic accordance to teacher id
    VerticalLayout LAYOUT = new VerticalLayout();


    public QuizView()
    {
        courses.setAllowCustomValue(false);
        chapters.setAllowCustomValue(false);
        courses.setPlaceholder("Select Course");
        chapters.setPlaceholder("Select Chapter");
        courses.setWidth("500px");
        chapters.setWidth("500px");
        String[][] _courses = quizOBJ.getCourseIDs();
        List<String> courseCollection = new ArrayList<>();

        for (int i = 0; _courses[i][0] != "-1"; i++)
            courseCollection.add(String.valueOf(_courses[i][1]));
        courses.setItems(courseCollection);
        courses.addValueChangeListener(e-> {
            quizOBJ.setCourseID(getIDbyCName(courses.getValue().toString(), _courses));
            String[] _chapter = quizOBJ.getChapters();
            List<String> chapterCollection = new ArrayList<>();
            for(int i = 0; _chapter[i] != "Unknown"; i++)
                chapterCollection.add(_chapter[i]);
            chapters.setItems(chapterCollection);
        });

        submit = new Button("Create Quiz", buttonClickEvent -> {
            String id = getIDbyCName(courses.getValue().toString(), _courses);
            String chap = chapters.getValue().toString();
            ComponentUtil.setData(UI.getCurrent(), "quizCID", id);
            ComponentUtil.setData(UI.getCurrent(), "quizChapter", chap);
            quizOBJ.setChapter(chap);
            String quizID = quizOBJ.registerQuiz();
            ComponentUtil.setData(UI.getCurrent(), "quizID", quizID);
            UI.getCurrent().navigate(QuizView2.class);
        });
        submit.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        LAYOUT.add(new HtmlContainer("br"),new H3("New Quiz"), new HtmlContainer("hr"), courses, chapters, submit);
        LAYOUT.setAlignItems(Alignment.CENTER);
        add(
                LAYOUT
        );

    }

    public String getIDbyCName(String cNAme, String[][] arr)
    {
        int i = 0;
        while(arr[i][1] != cNAme)
        {
            i++;
        }

        return arr[i][0];
    }
}
