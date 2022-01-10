package com.example.application.database;

import elemental.json.Json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Database_Creation_Mysql.sql file is provided
// import it using XAMPP or MySQL Workbench
public class Database {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Database()
    {
        try {
            // user = root
            // password = 5544
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "5544");
            statement = connection.createStatement();
//            statement.executeUpdate("INSERT INTO `lms`.`teachercred`(`email`,`password`,`tID`)VALUES('xyzaa','xyzaa',2);");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int studentAuthentication(String userID, String password)
    {
        try {
            resultSet = statement.executeQuery("SELECT * FROM `lms`.`studentcred` WHERE `email` = '"+userID+"' AND `password` = '"+password+"';");
            if (resultSet.next()) {
                return resultSet.getInt(3);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public int teacherAuthentication(String userID, String password)
    {
        try {
            resultSet = statement.executeQuery("SELECT * FROM `lms`.`teachercred` WHERE `email` = '"+userID+"' AND `password` = '"+password+"';");
            if (resultSet.next()) {
                return resultSet.getInt(3);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public void registerTeacher(String name, String contactNumber, String email, String DOB, String Qualification)
    {
        try {
            System.out.println("inside database regi teach");
            statement.executeUpdate("INSERT INTO `lms`.`teacher` (`Name`, `email`, `contactNumber`, `DOB`, `Qualification`, `accountBalance`) VALUES ('"+name+"','"+email+"','"+contactNumber+"','"+DOB+"','"+Qualification+"','10000');");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void registerStudent(String name, String contactNumber, String email, String DOB, String prefClass)
    {
        try {
            statement.executeUpdate("INSERT INTO `lms`.`student` (`Name`, `email`, `contactNumber`, `DOB`, `prefClass`, `accountBalance`) VALUES ('"+name+"','"+email+"','"+contactNumber+"','"+DOB+"','"+prefClass+"','10000');");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int registerCourse(String tID, String cName, String cSubject, String cClass, String cPrice)
    {
        try {
            statement.executeUpdate("INSERT INTO `lms`.`course` (`tID`, `cName`, `cSubject`, `cClass`, `price`) VALUES ('"+tID+"','"+cName+"','"+cSubject+"','"+cClass+"','"+cPrice+"');", Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            int key = -1;
            if (resultSet != null && resultSet.next()) {
                key = resultSet.getInt(1);
            }
            return key;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public void linkPurchasedCourse(String sID, String cID)
    {
        try {
            statement.executeUpdate("INSERT INTO `lms`.`purchasedcourse` (`sID`, `cID`) VALUES ('"+sID+"','"+cID+"');");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void registerPlaylist(String cID, String Chapter, String videTitle, String videoLoc)
    {
        try {
            statement.executeUpdate("INSERT INTO `lms`.`playlist` (`cID`, `Chapter`, `videoTitle`, `videoLoc`) VALUES ('"+cID+"','"+Chapter+"','"+videTitle+"','"+videoLoc+"');");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addStudentCredentials(String user, String password)
    {
        try{
            System.out.println("==> " + user);
            System.out.println("==> " + password);
            resultSet = statement.executeQuery("SELECT * FROM `lms`.`student` WHERE `email` = '"+user+"';");
            String sID = "";
            if(resultSet.next())
                sID = resultSet.getString(1);
            System.out.println("-->" + sID +"\n");
            statement.executeUpdate("INSERT INTO `lms`.`studentcred` (`email`, `password`, `sID`) VALUES ('"+user+"','"+password+"','"+sID+"');");
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    public void addTeacherCredentials(String user, String password)
    {
        try{
            System.out.println("==> " + user);
            System.out.println("==> " + password);
            resultSet = statement.executeQuery("SELECT * FROM `lms`.`teacher` WHERE `email` = '"+user+"';");
            String tID = "";
            if(resultSet.next())
                tID = resultSet.getString(1);
            System.out.println("-->" + tID +"\n");
            statement.executeUpdate("INSERT INTO `lms`.`teachercred` (`email`, `password`, `tID`) VALUES ('"+user+"','"+password+"','"+tID+"');");
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    public String[][] returnCourseIDs(String tID)
    {
        String[][] ids = new String[10][2];
        for (int i = 0; i < 10; i++)
            ids[i][0] = "-1";
        try{
            resultSet = statement.executeQuery("SELECT * FROM `lms`.`course` WHERE `tID` = '"+tID+"';");
            int i = 0;
            while(resultSet.next()) {
                ids[i][0] = resultSet.getString(1);
                ids[i][1] = resultSet.getString(3);
                i++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ids;
    }

    public String[] getChaptersByCID(String cID)
    {
        String[] _chapters = new String[20];
        for(int i = 0; i < 20; i++)
            _chapters[i] = "Unknown";
        try {
            resultSet = statement.executeQuery("SELECT * FROM `lms`.`playlist` WHERE `cID` = '" + cID + "';");
            int i = 0;
            while (resultSet.next())
            {
                _chapters[i] = resultSet.getString(3);
//                System.out.println(_chapters[i]);
                i++;
            }
//            System.out.println("-->" + tID +"\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
        return _chapters;
    }

    public int registerQuiz(String tID, String cID, String chapter)
    {
        try {
            statement.executeUpdate("INSERT INTO `lms`.`quiz` (`tID`, `cID`, `Chapter`) VALUES ('"+tID+"','"+cID+"','"+chapter+"');", Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            int key = -1;
            if (resultSet != null && resultSet.next()) {
                key = resultSet.getInt(1);
            }
            return key;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public void registerQuestion(String q, String ques, String a1, String a2, String a3, String a4, String aR)
    {
        try {
            statement.executeUpdate("INSERT INTO `lms`.`quizforma` (`quizID`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `rightAnswer`) VALUES ('"+q+"','"+ques+"','"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+aR+"');");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String[][] getQuizQuestion(String QID, int total)
    {
        String[][] Question = new String[total][6];
        try {
            resultSet = statement.executeQuery("SELECT * FROM `lms`.`quizforma` WHERE `quizID` = '" + QID + "';");
            int i = 0;
            while (resultSet.next())
            {
                Question[i][0] = resultSet.getString(2);
                Question[i][1] = resultSet.getString(3);
                Question[i][2] = resultSet.getString(4);
                Question[i][3] = resultSet.getString(5);
                Question[i][4] = resultSet.getString(6);
                Question[i][5] = resultSet.getString(7);
                i++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }

        return Question;
    }

    public int getTotalQues(String qid)
    {
        int _val = -1;
        try {
            resultSet = statement.executeQuery("SELECT COUNT(quizID) FROM `lms`.`quizforma` WHERE `quizID` = '" + qid + "';");
            if (resultSet.next())
            {
                _val = resultSet.getInt(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
        return _val;
    }

    public String[] getCourseDetails(String cID)
    {
        String [] _ret = new String[6];
        try{
            resultSet = statement.executeQuery("SELECT * FROM course WHERE `cID` = '" + cID + "';");
            if (resultSet.next())
            {
                _ret[0] = resultSet.getString(3);   // Course Name
                _ret[1] = resultSet.getString(6);   // Course Price
                _ret[2] = resultSet.getString(5);   // Course Class
            }
            else
            {
                _ret[0]= "-1";
                return _ret;
            }

            resultSet = statement.executeQuery("SELECT * FROM teacher WHERE `tid` = '" + resultSet.getString(2) + "';");
            if (resultSet.next())
            {
                _ret[3] = resultSet.getString(2);   // Teacher Name
                _ret[4] = resultSet.getString(6);   // Teacher Qualification
            }

            resultSet = statement.executeQuery("SELECT COUNT(*) FROM playlist WHERE `cID` = '" + cID + "';");
            if (resultSet.next())
            {
                _ret[5] = resultSet.getString(1);   // No of Videos
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _ret;
    }

    public String getQuizId(String cID, String chapter)
    {
        try {
            resultSet = statement.executeQuery("SELECT * FROM quiz WHERE `cID` = '"+cID+"' AND `Chapter` = '"+chapter+"';");
            if (resultSet.next())
            {
                return resultSet.getString(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "-1";
    }

    public ArrayList<String> getPlaylistIds(String cID)
    {
        ArrayList<String> _ids = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM playlist WHERE `cID` = '" + cID + "';");
            while (resultSet.next())
            {
                _ids.add(resultSet.getString(5));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _ids;
    }

    public ArrayList<String> getPlaylistNames(String cID)
    {
        ArrayList<String> _names = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM playlist WHERE `cID` = '" + cID + "';");
            while (resultSet.next())
            {
                _names.add(resultSet.getString(4));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _names;
    }

    public ArrayList<String> getPlaylistChapters(String cID)
    {
        ArrayList<String> _names = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM playlist WHERE `cID` = '" + cID + "';");
            while (resultSet.next())
            {
                _names.add(resultSet.getString(3));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _names;
    }

    public String[] getPurchasedCourseIds(String sID)
    {
        String[] ids = new String[20];
        try{
            resultSet = statement.executeQuery("SELECT * FROM purchasedcourse WHERE `sID` = '" + sID + "';");
            int i = 0;
            while (resultSet.next())
            {
                ids[i] = resultSet.getString(2);
                i++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ids;
    }

    public boolean purchaseCourse(String sid, String cid)
    {
        boolean _ret = false;
        String price = getBalaneStudent(sid);
        String[] course = getCourseDetails(cid);
        boolean isAvailable = false;
        try {
            resultSet = statement.executeQuery("SELECT * FROM purchasedcourse WHERE `sID` = '" + sid + "' AND `cID` = '"+cid+"';");
            if(resultSet.next())
                isAvailable = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (Integer.valueOf(course[1]) < Integer.valueOf(price) && !isAvailable) {
            _ret = true;
            price = String.valueOf(Integer.valueOf(price) - Integer.valueOf(course[1]));
        }

        if (_ret == true) {
            try {
                statement.executeUpdate("INSERT INTO `lms`.`purchasedcourse`(`sID`,`cID`) VALUES ('" + sid + "', '" + cid + "');");
                statement.executeUpdate("UPDATE `lms`.`student` SET `accountBalance` = '" + price + "';");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return _ret;
    }

    public ArrayList<String> getTeacherDetails(String tID)
    {
        ArrayList<String> _ret = new ArrayList<>();
        try
        {
            resultSet = statement.executeQuery("SELECT * FROM teacher WHERE `tid` = '" + tID + "';");
            if (resultSet.next())
            {
                _ret.add(resultSet.getString(2));   // Teacher Name
                _ret.add(resultSet.getString(3));   // Teacher Mail
                _ret.add(resultSet.getString(4));   // Teacher Contact
                _ret.add(resultSet.getString(5));   // Teacher DOB
                _ret.add(resultSet.getString(6));   // Teacher Qualification
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _ret;
    }

    public ArrayList<String[]> getTeacherCourses(String tID)
    {
        ArrayList<String[]> _ret = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM course WHERE `tID` = '"+tID+"';");
            while (resultSet.next())
            {
                ResultSet rs;
                Statement s;
                s = connection.createStatement();
                rs = s.executeQuery("SELECT COUNT(*) FROM purchasedcourse WHERE `cID` = '"+resultSet.getString(1)+"';");
                String[] toAdd = {"0", "0"};
                toAdd[0] = resultSet.getString(3);
                if (rs.next())
                {
                    toAdd[1] = rs.getString(1);
                }

                _ret.add(toAdd);
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _ret;
    }

    public String getBalaneTeacher(String id) {
        String _ret = "0";
        try {
            resultSet = statement.executeQuery("SELECT * FROM teacher WHERE `tID` = '"+id+"';");
            if(resultSet.next())
                _ret = resultSet.getString(7);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _ret;
    }

    public String getBalaneStudent(String id) {
        String _ret = "0";
        try {
            resultSet = statement.executeQuery("SELECT * FROM student WHERE `sID` = '"+id+"';");
            if(resultSet.next())
                _ret = resultSet.getString(7);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return _ret;
    }
}
