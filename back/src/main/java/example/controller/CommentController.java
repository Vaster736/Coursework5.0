package example.controller;

import example.model.Comment;
import example.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class CommentController {
    @Autowired
    private CommentService Service;


    @GetMapping("/comment/{id}")
    public Comment getOne(@PathVariable long id){
        return Service.getById(id);
    }

    @PostMapping("/comment")
    public void addOne(@RequestBody @Valid Comment temp){
        Service.saveOrUpdate(temp);
    }

    @PutMapping("/comment")
    public void updateOne(@RequestBody @Valid Comment temp){
        Service.saveOrUpdate(temp);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteOne(@PathVariable long id){
        Service.deleteById(id);
    }

    @GetMapping("/comments")
    public List<Comment> getAllUsers() {
        return Service.getAll();
    }

    @GetMapping("/comments/student/{id}")
    public List<Comment> getStudentComments(@PathVariable("id") Long studentId) {
            List<Comment> comments = Service.findCommentsByStudentId(studentId);
            return comments;

    }
}
