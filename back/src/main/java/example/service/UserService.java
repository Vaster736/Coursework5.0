package example.service;
import example.model.User;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOrUpdate(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public void addListOfUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    public void deleteListOfUsers(List<User> users) {
        userRepository.deleteAll(users);
    }

    public List<User> getUsersByCourseId(Long userId) {
        User teacher = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        List<User> users = userRepository.findByIdCourse(teacher.getId_course());
        return users;
    }
}