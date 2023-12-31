package com.example.miles.entities;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name="feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String image;
    private String feedback;
    private String name;

    public Feedback(){}

    public Feedback(String id, String image, String feedback, String name) {
        this.id = id;
        this.image = image;
        this.feedback = feedback;
        this.name = name;
    }

    public Feedback(String feedback, String name){
        this.feedback = feedback;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
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
