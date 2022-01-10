package com.example.application.backend;

import com.example.application.database.Database;

import java.util.ArrayList;
import java.util.Arrays;

public class Course {
    private String courseID;
    private String teacherID;
    private String courseName;
    private String courseSubject;
    private String courseClass;
    private String coursePrice;
    private ArrayList<String> playlistIds = new ArrayList<>();
    private ArrayList<String> playlistNames = new ArrayList<>();
    private ArrayList<String> playlistChapters = new ArrayList<>();

    public ArrayList<String> getPlaylistChapters() {
        return playlistChapters;
    }

    private int noOfVideos;

    public ArrayList<String> getPlaylistNames() {
        return playlistNames;
    }

    Database data = new Database();

    public String getTeacherID() {
        return teacherID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public ArrayList<String> getPlaylistIds() {
        return playlistIds;
    }

    public int getNoOfVideos() {
        return noOfVideos;
    }

    public Course()
    {
        // Default Constructor
    }

    public Course(String _cID)
    {
        courseID = _cID;
        String[] cDet = new String[6];
        cDet = data.getCourseDetails(courseID);
        teacherID = cDet[3];
        courseName = cDet[0];
        courseClass = cDet[2];
        coursePrice = cDet[1];
        noOfVideos = Integer.valueOf(cDet[5]);
        playlistIds = data.getPlaylistIds(_cID);
        playlistNames = data.getPlaylistNames(_cID);
        playlistChapters = data.getPlaylistChapters(_cID);
    }

    public Course(String _teacherID, String _courseName, String _courseSubject, String _courseClass, String _coursePrice)
    {
        teacherID = _teacherID;
        courseName = _courseName;
        courseSubject = _courseSubject;
        courseClass = _courseClass;
        coursePrice = _coursePrice;
    }

    public int registerCourse()
    {
        int returnedID = data.registerCourse(teacherID, courseName, courseSubject, courseClass, coursePrice);
        if (returnedID != -1)
            courseID = String.valueOf(returnedID);

        return returnedID;
    }

    public String[][] getCoursesByTID(String tID)
    {
        return data.returnCourseIDs(tID);
    }

    public String[] getCourses(String cID)
    {
        return data.getCourseDetails(cID);
    }

}
