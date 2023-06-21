package example.controller;

import example.model.Course;
import example.model.Timetable;
import example.service.TimetableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class TimetableController {
    @Autowired
    private TimetableService Service;


    @GetMapping("/timetable/{id}")
    public Timetable getOne(@PathVariable long id){
        return Service.getById(id);
    }

    @PostMapping("/timetable")
    public ResponseEntity<Timetable> addOne(@RequestBody @Valid Timetable temp){
        Service.saveOrUpdate(temp);
        return ResponseEntity.ok(temp);
    }

    @PutMapping("/timetable/{id}")
    public ResponseEntity<Timetable> updateOne(@RequestBody @Valid Timetable temp){
        Service.saveOrUpdate(temp);
        return ResponseEntity.ok(temp);
    }

    @DeleteMapping("/timetable/{id}")
    public void deleteOne(@PathVariable long id){
        Service.deleteById(id);
    }

    @GetMapping("/timetables")
    public List<Timetable> getAllUsers() {
        return Service.getAll();
    }
    @GetMapping("/timetables/teacher/{userId}")
    public List<Timetable> getTeacherTimetable(@PathVariable("userId") Long userId) {
        return Service.findByTeacherId(userId);
    }

}
