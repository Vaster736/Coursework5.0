package example.controller;

import example.model.Course;
import example.model.Subject;
import example.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class CourseController {
    @Autowired
    private CourseService Service;


    @GetMapping("/course/{id}")
    public Course getOne(@PathVariable long id){
        return Service.getById(id);
    }

    @PostMapping("/course")
    public ResponseEntity<Course> addOne(@RequestBody @Valid Course temp){
        Service.saveOrUpdate(temp);
        return ResponseEntity.ok(temp);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updateOne(@RequestBody @Valid Course temp){
        Service.saveOrUpdate(temp);
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping("/course/{id}")
    public void deleteOne(@PathVariable long id){
        Service.deleteById(id);
    }

    @GetMapping("/courses")
    public List<Course> getAllUsers() {
        return Service.getAll();
    }


}
