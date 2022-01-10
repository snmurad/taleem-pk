package com.example.application;

import com.example.application.backend.Controller;
import com.example.application.backend.QuizCreator;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import java.nio.file.Paths;


@Route(value = "Lecture")
public class Enrolled_Courses extends VerticalLayout {
    Video video;
    Button Chat;
    Button Quiz;
    Button Back;
    Button Next;
    String t = ComponentUtil.getData(UI.getCurrent(), "TITLE").toString();
    String PATH = ComponentUtil.getData(UI.getCurrent(), "PATH").toString();
    Controller Course = Controller.getInstance();

    String courseId = ComponentUtil.getData(UI.getCurrent(), "courseSelected").toString();          // Have to be Dynamic
    String chapter = ComponentUtil.getData(UI.getCurrent(), "CHAPTER").toString();                 // Have to be Dynamic
    int singleClick = 0;

    // For Quiz //
    H5 _question;
    RadioButtonGroup<String> options;
    Button next;
    Button submit;
    String[][] associatedQuiz;
    int x = 1;
    int score = 0;
    int totalQuestions;
    String quizID;
    QuizCreator _quiz = new QuizCreator();
    //////////////

    public Enrolled_Courses() {
        draw();
    }

    public void draw() {
        TextArea title = new TextArea();
        title.setHeight("53px");
        title.setWidth("1200px");
        title.setValue(t);
        title.setReadOnly(true);
        title.getStyle().set("5", "var(--lumo-space-l) 0 0 0").set("font-size", "1em").set("font-weight", "bold");

        video = new Video();
        video.setWidth("1200px");
        video.setHeight("500px");
        video.setControls(true);
        video.setSource(Paths.get(PATH));
        VerticalLayout vlayout = new VerticalLayout();
        vlayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Chat = new Button("Ask Question", buttonClickEvent -> {
        });

        Back = new Button("Go Back", buttonClickEvent -> {
            UI.getCurrent().navigate(CourseInfo.class);
        });

        Quiz = new Button("Quiz", buttonClickEvent -> {
            if (singleClick == 0) {
                singleClick++;
                quizID = Course.getQuizId(courseId, chapter);  // This should be set dynamically
                if (!(quizID.equals("-1"))) {
                    System.out.println(quizID);
                    x = 1;
                    _quiz.setQuizID(quizID);
                    totalQuestions = _quiz.getTotalQuestions();
                    associatedQuiz = new String[totalQuestions][6];
                    associatedQuiz = _quiz.setByQuizId();

                    drawQuiz();
                } else {
                    singleClick++;
                    add(new H3("No Quiz Found!"));
                }
            }

        });

        Next = new Button("Next Lecture", buttonClickEvent -> {
        });


        Chat.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Back.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Quiz.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Next.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout layout = new HorizontalLayout(Back, Quiz);
        VerticalLayout layout1 = new VerticalLayout(video, title);

        vlayout.add(layout1, layout);

        add(vlayout);
    }


    public void drawQuiz() {
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
            remove(_question);
            remove(options);
            remove(next);
            options.setValue("");
            x++;
            drawQuiz();
        });

        submit = new Button("Submit", buttonClickEvent -> {
            remove(_question);
            remove(options);
            remove(next);
            remove(submit);
            add(new H2("Your Score: " + score + " / " + totalQuestions));
        });
        if (x == totalQuestions) {
            add(_question, options, submit);
        } else {
            System.out.println(score);
            add(_question, options, next);
        }
    }
}
