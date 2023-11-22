package TaskManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String taskName;


    LocalDate dueDate;

    int taskPriority;

    @ManyToOne
    @JoinColumn
    User user;


}
