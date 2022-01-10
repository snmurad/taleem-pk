package com.example.application.backend;

import com.example.application.database.Database;

public class QuizCreator {
    String quizID;
    String Question;
    String ans1;
    String ans2;
    String ans3;

    public String getQuestion() {
        return Question;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public String getRightOption() {
        return rightOption;
    }

    String ans4;
    String rightOption;
    Database data = new Database();

    public QuizCreator(){}  // Default Constructor

    public void setQuizID(String givenId)
    {
        quizID = givenId;
    }
    public QuizCreator(String qID, String ques, String a1, String a2, String a3, String a4, String aR)
    {
        quizID = qID;
        Question = ques;
        ans1 = a1;
        ans2 = a2;
        ans3 = a3;
        ans4 = a4;
        rightOption = aR;
    }


    public void registerQuestion()
    {
        data.registerQuestion(quizID, Question, ans1, ans2, ans3, ans4, rightOption);
    }

    public String[][] setByQuizId()
    {
        String[][] _ret = data.getQuizQuestion(quizID, getTotalQuestions());
        return _ret;
    }

    public int getTotalQuestions()
    {
        return data.getTotalQues(quizID);
    }
}
