package example.repository;
import example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByLogin(String username);

    boolean existsByLogin(String login);
    @Query("SELECT u FROM User u WHERE u.id_course = :idCourse AND u.role = 'student'")
    List<User> findByIdCourse(Long idCourse);
}
