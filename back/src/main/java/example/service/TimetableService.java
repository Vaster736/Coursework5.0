package example.service;
import example.model.Timetable;
import example.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TimetableService {
    private final TimetableRepository Repository;

    public Timetable getById(long id){
        return Repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Timetable> getAll(){
        return Repository.findAll();
    }

    public void saveOrUpdate(Timetable temp){
        Repository.save(temp);
    }

    public void deleteById(long id){
        Repository.deleteById(id);
    }

    public List<Timetable> findByTeacherId(Long userId) {
        return Repository.findByTeacherId(userId);
    }
}