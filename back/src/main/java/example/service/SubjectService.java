package example.service;
import example.model.Subject;
import example.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository Repository;

    public Subject getById(long id){
        return Repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Subject> getAll(){
        return Repository.findAll();
    }

    public Subject saveOrUpdate(Subject temp){
        Repository.save(temp);
        return temp;
    }

    public void deleteById(long id){
        Repository.deleteById(id);
    }


}