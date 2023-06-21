package example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name = "timetable")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String weekday_id;


    private String start_time;


    private Long id_course;


}
