package TaskManagementSystem.Service.ServiceInterface;

import TaskManagementSystem.Dtos.RequestDto.UserRequestDto;

public interface UserInterface {

    public String addUser(UserRequestDto userRequestDto) throws Exception;


    public String updateUserMobileNo(String mobileNo,String email) throws Exception;


    public String deleteUser(String email) throws Exception;
}
