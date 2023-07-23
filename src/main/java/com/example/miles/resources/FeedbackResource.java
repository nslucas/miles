package com.example.miles.resources;

import com.example.miles.entities.Feedback;
import com.example.miles.repository.FeedbackRepository;
import com.example.miles.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackResource {

    @Autowired
    private FeedbackService service;

    @GetMapping
    public ResponseEntity<List<Feedback>> findAll() {
        List<Feedback> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Feedback> findById(@PathVariable String id) {
        Feedback obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestParam("image") MultipartFile image,
                                                   @RequestParam("feedback") String feedbackText,
                                                   @RequestParam("name") String name) {
        try {
            Feedback savedFeedback = service.createFeedback(image, feedbackText, name);
            return ResponseEntity.ok(savedFeedback);
        } catch (IOException e) {
            // Tratar possíveis erros de upload de imagem
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@RequestBody Feedback obj, @PathVariable String id){
        Feedback newObj = service.findById(id);
        if (newObj == null) {
            return ResponseEntity.notFound().build();
        }
        service.update(newObj, obj);
        return ResponseEntity.noContent().build();
    }
}



