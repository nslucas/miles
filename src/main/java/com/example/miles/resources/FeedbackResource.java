package com.example.miles.resources;

import com.example.miles.dto.FeedbackRecord;
import com.example.miles.dto.FeedbackRecordRequest;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackResource {

    @Autowired
    private FeedbackService service;

    @GetMapping
    public ResponseEntity<List<FeedbackRecord>> findAll() {
        List<FeedbackRecord> list = service.findAll().stream().map(feedback -> new FeedbackRecord(
                feedback.getId(),
                feedback.getFeedback(),
                feedback.getName())).toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<FeedbackRecord> findById(@PathVariable String id) {
        Feedback obj = service.findById(id);
        return ResponseEntity.ok().body(new FeedbackRecord(obj));
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
        service.update(newObj);
        return ResponseEntity.noContent().build();
    }
}



