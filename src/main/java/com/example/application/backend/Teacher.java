package com.example.application.backend;

import com.example.application.database.Database;

import java.util.ArrayList;

public class Teacher extends User {
    public int isAvailable(String email, String password)
    {
        int _RET = data.teacherAuthentication(email, password);
        return _RET;
    }
    Database data = new Database();
    String name;
    String email;
    String password;
    String contact;
    String DOB;
    String Qualification;
    public Teacher() {
    }
    public Teacher(String Cname, String Cemail, String Cpassword, String Ccontact, String CDOB, String CQualification)
    {
        name = Cname;
        email = Cemail;
        password = Cpassword;
        contact = Ccontact;
        DOB = CDOB;
        Qualification = CQualification;
    }

    public ArrayList<String> getDetails(String tID)
    {
        return data.getTeacherDetails(tID);
    }

    public ArrayList<String[]> getTeacherCourses(String tID)
    {
        return data.getTeacherCourses(tID);
    }

    public boolean Registry()
    {
        System.out.println("inside Techer Registry Func");
//        System.out.println("-->"+ name + "\n");
//        System.out.println("-->"+ contact + "\n");
//        System.out.println("-->"+ email + "\n");
//        System.out.println("-->"+ DOB + "\n");
//        System.out.println("-->"+ uClass + "\n");
//        System.out.println("-->"+ password + "\n");
        data.registerTeacher(name, contact, email, DOB, Qualification);
        data.addTeacherCredentials(email, password);
        return true;
    }


    public String getBalance(String id) {
        return  data.getBalaneTeacher(id);
    }
}
