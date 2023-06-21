package example.service;
import example.model.Course;
import example.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository Repository;

    public Course getById(long id){
        return Repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Course> getAll(){
        return Repository.findAll();
    }

    public void saveOrUpdate(Course temp){
        Repository.save(temp);
    }

    public void deleteById(long id){
        Repository.deleteById(id);
    }

}