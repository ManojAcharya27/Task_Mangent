package TaskManagementSystem.Service.ServiceImpl;

import TaskManagementSystem.Dtos.RequestDto.UserRequestDto;
import TaskManagementSystem.Entity.User;
import TaskManagementSystem.Repository.UserRepository;
import TaskManagementSystem.Service.ServiceInterface.UserInterface;
import TaskManagementSystem.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserInterface {

    @Autowired
    UserRepository userRepository;
    @Override
    public String addUser(UserRequestDto userRequestDto) throws Exception {

        User user=userRepository.findByEmail(userRequestDto.getEmail());
        if(user!=null){
            throw  new Exception("This Email Already Present !");
        }

        User user1= UserTransformer.userRequestDtoToUser(userRequestDto);
        User savedUser=userRepository.save(user1);
        return "Login of user is Successful";
    }

    @Override
    @CachePut(cacheNames = "mobileNo")
    public String updateUserMobileNo(String mobileNo, String email) throws Exception {

        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User does not exist . Please sign in");
        }
        user.setMobileNo(mobileNo);
        User savedUser=userRepository.save(user);
        return "Mobile no. Updated Successfully";
    }


    @Override
    @CacheEvict(cacheNames = "User")
    public String deleteUser(String email) throws Exception {

        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User does not found");
        }

        userRepository.delete(user);
        return "User Deletion Successful";
    }
}
