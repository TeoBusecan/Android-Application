package app.controller;

import app.dto.ChangePasswordDTO;
import app.dto.UserDTO;
import app.dto.UserLogin;
import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/login")
    public String login(@RequestBody UserLogin logInUser) throws IOException, TimeoutException {
        System.out.println(logInUser.getEmail());
        System.out.println(logInUser.getPassword());
        return userService.loginUser(logInUser);
    }

    @GetMapping(value = "/get/{username}")
    public UserDTO getuser(@PathVariable("username") String username){
        return userService.getUser(username);
    }

    @PutMapping("/updateUser/{id}")
    public UserDTO updateuser( @PathVariable int id,@RequestBody UserDTO userDTO) {

        System.out.println("HELLO GORGEOUS");
        return userService.update(id,userDTO);
    }

    @PutMapping(value = "/changePassword/{id}")
    public boolean changePassword(@PathVariable int id, @RequestBody ChangePasswordDTO changePassword) {
        return userService.changePass(id,changePassword);
    }
}
