package TaskManagementSystem.Transformer;

import TaskManagementSystem.Dtos.RequestDto.TaskRequestDto;
import TaskManagementSystem.Entity.Task;

public class TaskTransformer {

    public static Task taskRequestDtoTOTask(TaskRequestDto taskRequestDto){
        return Task.builder().taskName(taskRequestDto.getTaskName()).taskPriority(taskRequestDto.getTaskPriority())
                .dueDate(taskRequestDto.getDueDate()).build();
    }
}
