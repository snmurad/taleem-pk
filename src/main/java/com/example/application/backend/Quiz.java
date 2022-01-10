package com.example.application.backend;

import com.example.application.backend.Controller;
import com.example.application.backend.Course;
import com.example.application.database.Database;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Quiz {
    private String quizID;
    private String teacherID;
    private String courseID;
    private String chapter;
    Database data = new Database();
    public Quiz()
    {
        // Default Constructor
    }
    public Quiz(String _TID)
    {
        teacherID = _TID;
    }

    public String[][] getCourseIDs()
    {
        Course gettingIDs = new Course();
        return gettingIDs.getCoursesByTID(teacherID);
    }

    public void setCourseID(String _CID)
    {
        courseID = _CID;
    }

    public String[] getChapters()
    {
        return data.getChaptersByCID(courseID);
    }

    public void setChapter(String a)
    {
        chapter = a;
    }

    public String registerQuiz()
    {
        int _ret = data.registerQuiz(teacherID, courseID, chapter);
        return String.valueOf(_ret);
    }

    public String getQuizId(String id, String chap)
    {
        return data.getQuizId(id, chap);
    }

//    public void Display()
//    {
//        int[] courses = getCourseIDs();
//        setCourseID("1");
//        String[] chaps = getChapters();
//        for(int i = 0; courses[i] != -1 ;i++)
//        {
//            System.out.println("Course: " + courses[i] + "\n");
//        }
//        for(int i = 0; chaps[i] != "Unknown" ;i++)
//        {
//            System.out.println("Chapters: " + chaps[i] + "\n");
//        }
//    }
}
