package com.example.miles.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="feedback")
public class Feedback {

    @Id
    private int id;
    private String image;
    private String feedback;
    private String name;

    public Feedback(){}

    public Feedback(int id, String image, String feedback, String name) {
        this.id = id;
        this.image = image;
        this.feedback = feedback;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    public String getImage() {
        return this.image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
