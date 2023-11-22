package TaskManagementSystem.Controller;

import TaskManagementSystem.Dtos.RequestDto.TaskRequestDto;
import TaskManagementSystem.Entity.Task;
import TaskManagementSystem.Service.ServiceInterface.TaskService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    TaskService taskService;

    @PostMapping("/create_task")
    public ResponseEntity createTask(@RequestBody TaskRequestDto taskRequestDto) {

        try {
            String message = taskService.createTask(taskRequestDto);
            return new ResponseEntity(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAll_task")
    public ResponseEntity getAllTaskOfUser(@PathParam("email") String email) {
        try {
            List<Task> taskList = taskService.getAllTaskOfUser(email);
            System.out.println(taskList.size());
            return new ResponseEntity<>("The Name Of the User"+" "+taskList.get(0).getUser().getName()+" "+" The task name is"+
                    " "+taskList.get(0).getTaskName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/dueDateOrdered")
    public ResponseEntity<List> getTasksByDueDateOrdered(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Task> tasks = taskService.getTasksByDueDateRangeOrderedByDueDate(startDate, endDate);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}