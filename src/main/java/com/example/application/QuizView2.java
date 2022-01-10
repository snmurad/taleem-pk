package com.example.application;

import com.example.application.backend.QuizCreator;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value="quiz2", layout = TeacherDashbord.class)
public class QuizView2 extends VerticalLayout {
    String quizID = ComponentUtil.getData(UI.getCurrent(), "quizID").toString();
    String quizCID = ComponentUtil.getData(UI.getCurrent(), "quizCID").toString();
    String quizChapter = ComponentUtil.getData(UI.getCurrent(), "quizChapter").toString();
    String teacherID = ComponentUtil.getData(UI.getCurrent(), "teacherID").toString(); // Have to be Dynamic using ComponentUtil

    H3 _heading;
    HtmlContainer _HR;
    VerticalLayout LAYOUT = new VerticalLayout();
    int x = 1;
    TextArea question = new TextArea();
    TextField option1 = new TextField();
    TextField option2 = new TextField();
    TextField option3 = new TextField();
    TextField option4 = new TextField();
    ComboBox answer = new ComboBox();
    Button submit;
    Button addAnother;


    public QuizView2()
    {
        question.setHeight("150px");
        question.setWidth("600px");
        question.setPlaceholder("Enter Question");
        option1.setPlaceholder("Option 1");
        option2.setPlaceholder("Option 2");
        option3.setPlaceholder("Option 3");
        option4.setPlaceholder("Option 4");
        answer.setPlaceholder("Right Answer");
        answer.setItems("1", "2", "3", "4");
        option1.setWidth("400px");
        option2.setWidth("400px");
        option3.setWidth("400px");
        option4.setWidth("400px");
        answer.setWidth("400px");
        _heading = new H3("Question " + x);

        Draw();

    }

    public void Draw()
    {
        _HR = new HtmlContainer("hr");
        addAnother = new Button("Add Another", buttonClickEvent -> {
            ++x;
            System.out.println("Inside Button Click event" + x);
            QuizCreator newQuestion = new QuizCreator(quizID, question.getValue(), option1.getValue(), option2.getValue(), option3.getValue(), option4.getValue(), answer.getValue().toString() == "1"? option1.getValue(): answer.getValue().toString() == "2" ? option2.getValue() : answer.getValue().toString() == "3" ? option3.getValue() : option4.getValue());
            newQuestion.registerQuestion();
            question.setValue("");
            option1.setValue("");
            option2.setValue("");
            option3.setValue("");
            option4.setValue("");
            answer.setValue("");
            _heading.setText("Question " + x);
            remove(question);
            remove(option1);
            remove(option2);
            remove(option3);
            remove(option4);
            remove(answer);
            remove(submit);
            remove(addAnother);
            remove(_heading);
            remove(_HR);
            Draw();
        });
        submit = new Button("Submit", buttonClickEvent -> {
            UI.getCurrent().navigate(TeacherDashbord.class);
        }
                );
        submit.setWidth("400px");
        addAnother.setWidth("400px");
        submit.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        LAYOUT.add(
                _heading,
                _HR,
                question,
                option1,
                option2,
                option3,
                option4,
                answer,
                addAnother,
                submit

        );

        LAYOUT.setAlignItems(Alignment.CENTER);
        add(LAYOUT);
    }

}
