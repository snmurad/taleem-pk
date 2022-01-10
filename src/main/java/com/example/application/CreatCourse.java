package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

import java.io.*;
import java.nio.file.Paths;

import static java.lang.System.out;

@Route(value = "Creat Course", layout = TeacherDashbord.class)
public class CreatCourse extends VerticalLayout {

    int counter = 1;
    Button next;
    Video video;
    H2 head;
    H1 head0;
    TextField tf;
    TextArea ta;
    Upload upload;
    FileBuffer fileBuffer;
    Controller regPlaylist = Controller.getInstance();
    String cID = ComponentUtil.getData(UI.getCurrent(), "courseID").toString(); // Have to be dynamic
    String Path;

    public CreatCourse() {
        Path = System.getProperty("user.dir");
        draw();
    }

    public void draw() {
        head0 = new H1("TEACHER DASHBOARD");
        head0.getStyle().set("margin", "var(--lumo-space-l) 0 0 0").set("font-size", "4em").set("font-weight", "bold");
        head = new H2("CHAPTER " + counter);

        ta = new TextArea("Description: ");
        ta.setWidth("450px");
        ta.setHeight("150px");
        tf = new TextField("Video Title:");
        tf.getStyle().set("margin", "var(--lumo-space-l) 0 0 0").set("font-size", "2.5em").set("font-weight", "bold");
        tf.setWidth("450px");


        fileBuffer = new FileBuffer();
        upload = new Upload(fileBuffer);
        upload.addSucceededListener(event -> {

            System.out.println("File uploaded: " + fileBuffer.getFileName());
            InputStream a = fileBuffer.getInputStream();
            OutputStream b = null;


            try {
                Path += "\\data\\" + fileBuffer.getFileName();
                b = new FileOutputStream(new File(Path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                b.write(fileBuffer.getInputStream().readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            videostarter(fileBuffer.getFileName());
        });

        Button submit = new Button("Submit", buttonClickEvent -> {
            for (int i = 0; i < Path.length(); i++) {
                if (Path.charAt(i) == '\\') {
                    Path = Path.substring(0, i) + '/' + Path.substring(i + 1);
                }
            }
            regPlaylist.addPlaylist(cID, "Chapter " + counter, tf.getValue(), Path);
            out.println(cID);
            out.println("Chapter " + Integer.toString(counter));
            out.println(tf.getValue());
            out.println(Path);
            UI.getCurrent().navigate(TeacherDashbord.class);

        });

        next = new Button("Next", buttonClickEvent -> {

            for (int i = 0; i < Path.length(); i++) {
                if (Path.charAt(i) == '\\') {
                    Path = Path.substring(0, i) + '/' + Path.substring(i + 1);
                }
            }
            regPlaylist.addPlaylist(cID, "Chapter " + counter, tf.getValue(), Path);
            out.println(cID);
            out.println("Chapter " + counter);
            out.println(tf.getValue());
            out.println(Path);
            Path = System.getProperty("user.dir");

//                    remove(head0);
//                    remove(head);
//                    remove(ta);
//                    remove(upload);
//                    remove(submit);
//                    remove(tf);
//                    remove(video);
//                    remove(next);
            counter++;
            draw();
        });

        HorizontalLayout buttonlayout = new HorizontalLayout(next, submit);
        buttonlayout.setAlignItems(Alignment.STRETCH);

        VerticalLayout layout2 = new VerticalLayout(head0, head, tf, ta, upload, buttonlayout);
        layout2.setAlignItems(FlexComponent.Alignment.CENTER);
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        next.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        submit.setWidth("200px");
        next.setWidth("200px");
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        next.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(layout2);
    }


    public void videostarter(String fileName) {
        video = new Video();
        video.setWidth("500px");
        video.setHeight("370px");
        video.setControls(true);
        VerticalLayout vlayout = new VerticalLayout();
        video.setSource(Paths.get(Path));

        vlayout.add(video);
        vlayout.setAlignItems(Alignment.CENTER);
        add(vlayout);
    }

}









