package com.example.application.backend;

import java.util.ArrayList;

// Making Controller Class 'Singleton'
public class Controller {
    private static Controller instance = null;
    User user;
    PurchasedCourses pc;
    Playlist pl;
    private Controller() {
    }

    public static synchronized Controller getInstance()
    {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public int isStudentAvailable(String email, String password)
    {
        user = new Student();
        int _GET = user.isAvailable(email, password);
        return  _GET;
    }

    public int isTeacherAvailable(String email, String password)
    {
        user = new Teacher();
        int _GET = user.isAvailable(email, password);
        return _GET;
    }

    public boolean registerStudent(String name, String email, String password, String contact, String DOB, String uClass)
    {
        Student stud = new Student(name, email, password, contact, DOB, uClass);
        stud.Registry();
        return true;
    }

    public boolean registerTeacher(String name, String email, String password, String contact, String DOB, String Qualifiaction)
    {
        System.out.println("Inside Controller Techer function");
        Teacher _Teacher = new Teacher(name, email, password, contact, DOB, Qualifiaction);
        _Teacher.Registry();
        return true;
    }

    public int registerCourse(String _teacherID, String _courseName, String _courseSubject, String _courseClass, String _coursePrice)
    {
        Course toRegister = new Course(_teacherID, _courseName, _courseSubject, _courseClass, _coursePrice);
        int _val = toRegister.registerCourse();
        return _val;
    }

    public void addPlaylist(String cID, String Chapter, String videoTitle, String videoLoc)
    {
        Playlist p =  new Playlist(cID, Chapter, videoTitle, videoLoc);
        p.registerPlaylist();
    }

    public String[] getCourses(int cID)
    {
        Course c = new Course();
        return c.getCourses(String.valueOf(cID));
    }

    public String[] getPurchasedCourseIds(String sID)
    {
        pc = new PurchasedCourses(sID);
        return pc.getcID();
    }

    public String[] getPurchasedCourseNames()
    {
        return pc.getcNames();
    }

    public ArrayList<String> getVideosId(int cID)
    {
        Course _instance = new Course(String.valueOf(cID));
        return _instance.getPlaylistIds();
    }

    public ArrayList<String> getVideosName(int cID)
    {
        Course _instance = new Course(String.valueOf(cID));
        return _instance.getPlaylistNames();
    }

    public ArrayList<String> getVideosChapter(int cID)
    {
        Course _instance = new Course(String.valueOf(cID));
        return _instance.getPlaylistChapters();
    }

    public int getVideosNumber(int cID)
    {
        Course _instance = new Course(String.valueOf(cID));
        return _instance.getNoOfVideos();
    }

    public String getQuizId(String cID, String chapter)
    {
        Quiz _instance = new Quiz();
        return _instance.getQuizId(cID, chapter);
    }

    public boolean purchaseCourse(String sID, String cID)
    {
        PurchasedCourses _instance = new PurchasedCourses(sID);
        _instance.purchaseCourse(cID);

        return true;
    }

    public ArrayList<String> getTeacherDetails(String tID)
    {
        Teacher _instance = new Teacher();
        return _instance.getDetails(tID);
    }

    public ArrayList<String[]> getTeacherCourse(String tID)
    {
        Teacher _instance = new Teacher();
        return _instance.getTeacherCourses(tID);
    }

    public String getBalanceTeacher(String id)
    {
        Teacher _instance = new Teacher();
        return _instance.getBalance(id);
    }

    public String getBalanceStudent(String id)
    {
        Student _instance = new Student();
        return _instance.getBalance(id);
    }

}
