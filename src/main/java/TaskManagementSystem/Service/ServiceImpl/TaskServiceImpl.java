package TaskManagementSystem.Service.ServiceImpl;

import TaskManagementSystem.Dtos.RequestDto.TaskRequestDto;
import TaskManagementSystem.Entity.Task;
import TaskManagementSystem.Entity.User;
import TaskManagementSystem.Repository.TaskRepository;
import TaskManagementSystem.Repository.UserRepository;
import TaskManagementSystem.Service.ServiceInterface.TaskService;
import TaskManagementSystem.Transformer.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public String createTask(TaskRequestDto taskRequestDto) throws Exception {

        User  user=userRepository.findByEmail(taskRequestDto.getUserEmail());
        if(user==null){
            throw  new Exception("User Does Not Registered");
        }
        Task task= TaskTransformer.taskRequestDtoTOTask(taskRequestDto);

        if(task.getTaskPriority()>3&&task.getTaskPriority()<1){
            throw new Exception("Task Priority should be grater than 0 and less than 3");
        }
        task.setUser(user);
        user.getTaskList().add(task);
        User savedUser=userRepository.save(user);
        return "Task added Successfully";
    }

    @Override
    @Cacheable(cacheNames = "email")
    public List<Task> getAllTaskOfUser(String email) throws Exception {

        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User does not exist or enter proper email");
        }


        return user.getTaskList();
    }

    @Override
    public List<Task> getTasksByDueDateRangeOrderedByDueDate(LocalDate startDate, LocalDate endDate) {
        return taskRepository.findByDueDateBetweenOrderByDueDate(startDate, endDate);

    }
}
