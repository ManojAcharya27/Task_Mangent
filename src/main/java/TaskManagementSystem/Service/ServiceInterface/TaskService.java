package TaskManagementSystem.Service.ServiceInterface;

import TaskManagementSystem.Dtos.RequestDto.TaskRequestDto;
import TaskManagementSystem.Entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    public String createTask(TaskRequestDto taskRequestDto) throws Exception;


    public List<Task> getAllTaskOfUser(String email) throws Exception;

    public List<Task> getTasksByDueDateRangeOrderedByDueDate(LocalDate startDate, LocalDate endDate);
}
