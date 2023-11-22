package TaskManagementSystem.Dtos.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TaskRequestDto {
    String taskName;

    LocalDate dueDate;

    int taskPriority;

    String userEmail;
}
