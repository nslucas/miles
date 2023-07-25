package com.example.miles.dto;

import com.example.miles.entities.Feedback;

public record FeedbackRecord(String id, String feedback, String name) {

    public FeedbackRecord(Feedback obj){
        this(obj.getId(), obj.getFeedback(), obj.getName());
    }

}
