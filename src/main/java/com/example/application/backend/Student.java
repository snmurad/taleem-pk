package com.example.application.backend;

import com.example.application.database.Database;

public class Student extends User{
//    @Override
    public int isAvailable(String email, String password)
    {
        int _RET = data.studentAuthentication(email, password);
        return _RET;
    }
    Database data = new Database();
    String name;
    String email;
    String password;
    String contact;
    String DOB;
    String uClass;
    public  Student()
    {
        // Default Constructor
    }
    public Student(String Cname, String Cemail, String Cpassword, String Ccontact, String CDOB, String CuClass) {
        name = Cname;
        email = Cemail;
        password = Cpassword;
        contact = Ccontact;
        DOB = CDOB;
        uClass = CuClass;
    }

    public boolean Registry()
    {
//        System.out.println("-->"+ name + "\n");
//        System.out.println("-->"+ contact + "\n");
//        System.out.println("-->"+ email + "\n");
//        System.out.println("-->"+ DOB + "\n");
//        System.out.println("-->"+ uClass + "\n");
//        System.out.println("-->"+ password + "\n");
        data.registerStudent(name, contact, email, DOB, uClass);
        data.addStudentCredentials(email, password);
        return true;
    }


    public String getBalance(String id) {
        return data.getBalaneStudent(id);
    }
}
