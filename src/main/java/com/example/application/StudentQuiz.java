package com.example.application;

import com.example.application.backend.QuizCreator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.Route;

@Route("squiz")
public class StudentQuiz extends VerticalLayout {
    H5 _question;
    RadioButtonGroup<String> options;
    Button next;
    Button submit;
    String[][] associatedQuiz;

    int x;
    int score;
    int totalQuestions;
    String quizID;
    QuizCreator _quiz = new QuizCreator();

    StudentQuiz() {
        quizID = "9";  // This should be set dynamically
        x = 1;
        _quiz.setQuizID(quizID);
        totalQuestions = _quiz.getTotalQuestions();
        associatedQuiz = new String[totalQuestions][6];
        associatedQuiz = _quiz.setByQuizId();
        draw();
    }

    void draw() {
        _question = new H5("Question " + x);
        options = new RadioButtonGroup<String>();
        options.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        options.setLabel(associatedQuiz[x - 1][0]);
        options.setItems(associatedQuiz[x - 1][1], associatedQuiz[x - 1][2], associatedQuiz[x - 1][3], associatedQuiz[x - 1][4]);
        options.setValue(associatedQuiz[x - 1][5]);

        next = new Button("Next", buttonClickEvent -> {
            if (associatedQuiz[x - 1][5].equals(options.getValue())) {
                score++;
            }
//            remove(_heading);
            remove(options);
            remove(next);
            options.setValue("");
            x++;
            draw();
        });

        submit = new Button("Submit", buttonClickEvent -> {

        });
        if (x == totalQuestions) {
            add(_question, options, submit);
        } else {
            System.out.println(score);
            add(_question, options, next);
        }

    }
}
