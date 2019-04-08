package com.nexp.pavel.networkexpertise.mvp.model.entity;

public class Questions {

    private int id;
    private String  question;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Questions(int id, String question) {
        this.id = id;
        this.question = question;
    }
}
