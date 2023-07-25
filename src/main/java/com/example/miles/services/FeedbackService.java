package com.example.miles.services;

import com.example.miles.entities.Feedback;
import com.example.miles.entities.exception.ObjectNotFoundException;
import com.example.miles.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private final FeedbackRepository repository;

    @Autowired
    private final ImageUploadService imageUploadService;

    public FeedbackService(FeedbackRepository repository, ImageUploadService imageUploadService) {
        this.repository = repository;
        this.imageUploadService = imageUploadService;
    }

    public List<Feedback> findAll(){
        return  repository.findAll();
    }

    public Feedback findById(String id){
        Optional<Feedback> optionalFeedback = repository.findById(id);
        return optionalFeedback.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public Feedback createFeedback(MultipartFile image, String feedbackText, String name) throws IOException {
        String imageName = imageUploadService.uploadImage(image);

        Feedback feedback = new Feedback();
        feedback.setImage(imageName);
        feedback.setFeedback(feedbackText);
        feedback.setName(name);

        return repository.save(feedback);
    }

    public Feedback update(Feedback obj){
        Feedback newObj = repository.getReferenceById(obj.getId());
        newObj.setFeedback(obj.getFeedback());
        newObj.setName(obj.getName());
        return repository.save(newObj);
    }

    public String saveImage(String directory, MultipartFile file) {
        if (file.isEmpty()) {
            return "A imagem não pode estar vazia.";
        }
        try {
            // Defina o diretório onde a imagem será salva
            String uploadDir = "com/example/miles/resources/images";
            // Cria o path caso ele não exista
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String filePath = uploadDir + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            return "Imagem recebida e salva com sucesso: " + filePath;
        } catch (IOException e) {
            return "Erro ao processar a imagem: " + e.getMessage();
        }
    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }
}
