package com.example.miles.services;

import com.example.miles.entities.Feedback;
import com.example.miles.entities.exception.ObjectNotFoundException;
import com.example.miles.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public List<Feedback> findAll(){
        return  repository.findAll();
    }

    public Feedback findById(int id){
        Optional<Feedback> optionalFeedback = repository.findById(String.valueOf(id));
        return optionalFeedback.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

}
