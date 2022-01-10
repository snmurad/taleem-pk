package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

@Route(value = "cInfo", layout = StudentSideBar.class)
public class CourseInfo extends VerticalLayout {
    String cID = ComponentUtil.getData(UI.getCurrent(), "courseSelected").toString();   // Have to be Dynamic
    Controller getVideos = Controller.getInstance();
    int noOfVideos;
    ArrayList<String> _Ids;
    ArrayList<String> _Names;
    ArrayList<String> _Chapters;
    Button vids;
    VerticalLayout LAYOUT = new VerticalLayout();
    H3 _head;

    public CourseInfo() {
        noOfVideos = getVideos.getVideosNumber(Integer.valueOf(cID));
        _Ids = getVideos.getVideosId(Integer.valueOf(cID));
        _Names = getVideos.getVideosName(Integer.valueOf(cID));
        _Chapters = getVideos.getVideosChapter(Integer.valueOf(cID));
        _head = new H3(getVideos.getCourses(Integer.valueOf(cID))[0]);
        LAYOUT.add(_head);
        LAYOUT.setAlignItems(Alignment.CENTER);
        add(LAYOUT);
        for (int i = 0; i < noOfVideos; i++) {
            int finalI = i;
            vids = new Button(_Names.get(i), buttonClickEvent -> {
                ComponentUtil.setData(UI.getCurrent(), "PATH", _Ids.get(finalI));
                ComponentUtil.setData(UI.getCurrent(), "TITLE", _Names.get(finalI));
                ComponentUtil.setData(UI.getCurrent(), "CHAPTER", _Chapters.get(finalI));
                UI.getCurrent().navigate(Enrolled_Courses.class);
            });

            vids.setWidth("600px");
            vids.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
            LAYOUT.add(vids);
            LAYOUT.setAlignItems(Alignment.CENTER);
            add(LAYOUT);
        }

    }
}
