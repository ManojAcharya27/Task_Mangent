package TaskManagementSystem.Controller;

import TaskManagementSystem.Dtos.RequestDto.UserRequestDto;
import TaskManagementSystem.Service.ServiceImpl.UserServiceImpl;
import TaskManagementSystem.Service.ServiceInterface.UserInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class UserControllerTest {
    @Autowired
    UserServiceImpl userService;

    // please change email everytime for the test loginUser method otherwise it throws excepition;
    @Test
    void loginUser()  {
        UserRequestDto userRequestDto=new UserRequestDto("Manoj","hmanojacharya5@gmail.com","8105824966","8105824966Aa@");
       String ans=null;
        try {
             ans=userService.addUser(userRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        String expected="Login of user is Successful";
        String expectedAns2="This Email Already Present !";
        assertThat(ans).isEqualTo(expected);

    }

    // for this method use registred email and new mobile Number otherwise it throws exception.
    @Test
    void updateUserMobileNo() throws Exception {
        String ans= userService.updateUserMobileNo("7975628730","hmanojacharya5@gmail.com");
        assertThat(ans).isEqualTo("Mobile no. Updated Successfully");
    }

    // for this test method use registred email otherwise it throws exception.
    @Test
    void deleteUser() throws Exception {
        String ans= userService.deleteUser("hmanojacharya5@gmail.com");
        assertThat(ans).isEqualTo("User Deletion Successful");
    }
}