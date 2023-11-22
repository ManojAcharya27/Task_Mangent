package TaskManagementSystem.Transformer;

import TaskManagementSystem.Dtos.RequestDto.UserRequestDto;
import TaskManagementSystem.Entity.User;

import java.util.ArrayList;

public class UserTransformer {

    public static User userRequestDtoToUser(UserRequestDto userRequestDto){

        return  User.builder().email(userRequestDto.getEmail()).name(userRequestDto.getName())
                .mobileNo(userRequestDto.getMobileNo()).password(userRequestDto.getPassword())
                .taskList(new ArrayList<>()).build();
    }
}
