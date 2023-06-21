package example.service;
import example.model.Comment;
import example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository Repository;

    public Comment getById(long id){
        return Repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Comment> getAll(){
        return Repository.findAll();
    }

    public void saveOrUpdate(Comment temp){
        Repository.save(temp);
    }

    public void deleteById(long id){
        Repository.deleteById(id);
    }

    public List<Comment> findCommentsByStudentId(Long studentId){
            List<Comment> comments = Repository.findCommentsByStudentId(studentId);
            return comments;
    }
}