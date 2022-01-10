package com.example.application.backend;

import com.example.application.database.Database;
import org.apache.commons.lang3.ObjectUtils;

public class PurchasedCourses {


    String sID;
    String[] cID;
    String[] cNames;
    Database data = new Database();


    public PurchasedCourses(String s)
    {
        sID = s;
        cID = data.getPurchasedCourseIds(sID);
        cNames = new String[20];
        for (int i = 0; cID[i] != null; i++)
        {
            System.out.println(cID[i]);
            String[] tem = data.getCourseDetails(cID[i]);
            cNames[i] = tem[0];
        }
    }

    public boolean purchaseCourse(String _c)
    {
        return data.purchaseCourse(sID, _c);
    }

    public String[] getcID() {
        return cID;
    }

    public String[] getcNames() {
        return cNames;
    }


}
