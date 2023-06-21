package example.repository;
import example.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query("SELECT t FROM Timetable t WHERE t.id_course IN (SELECT u.id_course FROM User u WHERE u.id = :userId)")
    List<Timetable> findByTeacherId(@Param("userId") Long userId);
}
