package storyteam.server.story.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storyteam.server.story.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import storyteam.server.story.repository.UserRepository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/show")
    public User getUser(){

        //Will be used for user creation and update

        //Get a user and transfer his datas to the front view
        Optional<User> user = userRepository.findById(1);

        if(user.isEmpty()){
            //Throw some exception
        }

        return user.get();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(
            @RequestBody User user
    ){

        //Gathering the datas from the view and then
        //Saving it to the db => SECURITY TODO

        return userRepository.save(user);
    }

    @GetMapping("/update")
    public User modifyUser(){

        //Modifying the datas

        User changeUser = userRepository.findById(1).get();
        changeUser.setName("Rodrig");
        changeUser.setEmail("Rodrig@gmail.com");

        return userRepository.save(changeUser);

    }


    //When a user wants to delete his account, only him can do this
    @GetMapping("/delete")
    public void  deleteUser(){
        userRepository.deleteById(1);

    }
}
