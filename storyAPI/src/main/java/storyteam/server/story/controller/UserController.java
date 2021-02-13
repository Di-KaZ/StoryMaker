package storyteam.server.story.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storyteam.server.story.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import storyteam.server.story.repository.UserRepository;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(
            @RequestBody User userLog
    ){

        //Get a user and transfer his datas to the front view
        Optional<User> user = userRepository.findByName(userLog.getName());

        if(user.isEmpty()){
            //Throw some exception
            LoggerFactory.getLogger(UserController.class).info("Pas d'utilisateurs inscrit avec ce nom");
        }

        System.out.println(userLog.getPassword());
        System.out.println(user.get().getPassword());

        if (!(user.get().getPassword().equals(userLog.getPassword()))){
            //Return a customised error code that tell the user that his password
            //does not match with his userName
            LoggerFactory.getLogger(UserController.class).info("mot de passe incorrect");
        }

        return user.get();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(
            @RequestBody User user
    ){
        LoggerFactory.getLogger(UserController.class).info("logger");
        //Gathering the datas from the view and then
        //Saving it to the db => SECURITY TODO

        return userRepository.save(user);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User modifyUser(
            @RequestBody User user
    ){
        //Modifying the datas

        return userRepository.save(user);

    }


    //When a user wants to delete his account, only him can do this
    @DeleteMapping(value = "/delete/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("userId") Integer userId
    ){
        userRepository.deleteById(userId);

    }
}
