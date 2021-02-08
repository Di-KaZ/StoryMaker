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
    public List<User> showUser(){

        List<User> listUser = userRepository.findAll();

        return listUser;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(
            @RequestBody User user
    ){

        return userRepository.save(user);
    }

    @GetMapping("/update")
    public User modifyUser(){

        User changeUser = userRepository.findById(1).get();
        changeUser.setName("Rodrig");
        changeUser.setEmail("Rodrig@gmail.com");

        return userRepository.save(changeUser);

    }

    @GetMapping("/delete")
    public void  deleteUser(){
        userRepository.deleteById(1);

    }
}
