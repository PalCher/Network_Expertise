package com.nexp.pavel.networkexpertise.mvp.model.entity;

import java.util.List;

public class DataFrame {


    private List<String>  questions;
    private String text;



    public DataFrame(){

    }

    public DataFrame(List<String> questions, String text) {

        this.questions = questions;
        this.text = text;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }



}
