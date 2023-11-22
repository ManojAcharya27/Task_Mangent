package TaskManagementSystem.Controller;

import TaskManagementSystem.Dtos.RequestDto.UserRequestDto;
import TaskManagementSystem.Service.ServiceInterface.UserInterface;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserInterface userInterface;


    //Method to create User

    @PostMapping("/user_login")
    public ResponseEntity loginUser(@RequestBody UserRequestDto userRequestDto){
         try {
             String message=userInterface.addUser(userRequestDto);
             return new ResponseEntity(message, HttpStatus.CREATED);
         }catch (Exception e){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
         }

    }
    // Method Update user mobile number based on email

    @PutMapping("/update_userMobileNo")
    public ResponseEntity updateUserMobileNo(@PathParam("mobileNo")String mobileNo,@PathParam("email")String email){
        try {
            String message=userInterface.updateUserMobileNo(mobileNo,email);
            return new ResponseEntity(message,HttpStatus.ACCEPTED);

        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // Method to delete user based email.

    @DeleteMapping("/delete_user/{email}")
    public ResponseEntity deleteUser(@PathVariable("email") String email){
        try {
            String message=userInterface.deleteUser(email);
            return new ResponseEntity(message,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
