package example.controller;

import example.model.Subject;
import example.model.User;
import example.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class SubjectController {
    @Autowired
    private SubjectService Service;


    @GetMapping("/subject/{id}")
    public Subject getOne(@PathVariable long id){
        return Service.getById(id);
    }

    @PostMapping("/subject")
    public ResponseEntity<Subject> addOne(@RequestBody @Valid Subject temp){
        Service.saveOrUpdate(temp);
        Subject saved = Service.saveOrUpdate(temp);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/subject/{id}")
    public ResponseEntity<Subject> updateOne(@RequestBody @Valid Subject temp){
        Service.saveOrUpdate(temp);
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping("/subject/{id}")
    public void deleteOne(@PathVariable long id){
        Service.deleteById(id);
    }

    @GetMapping("/subjects")
    public List<Subject> getAllUsers() {
        return Service.getAll();
    }


}
