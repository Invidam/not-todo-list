package controller;


import DTO.Response.SuccessInfo;
import DTO.Token.TokenDTO;
import DTO.User.LoginUserDTO;
import DTO.User.UpdateUserDTO;
import DTO.User.UserRepresentInfoDTO;
import domain.User;
import org.springframework.http.ResponseEntity;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.net.URI;

@RestController
@RequestMapping("")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public  ResponseEntity<SuccessInfo> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(new SuccessInfo("SIGNUP_SUCCESS", "success at sign up."));
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public UserRepresentInfoDTO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
    @RequestMapping(value="/user", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDTO updateUserDTO, @RequestHeader(value = "Authorization") String authHeader) {

        return ResponseEntity.ok(userService.updateUser(updateUserDTO,authHeader));
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<SuccessInfo> withdrawUser(@PathVariable long id, @RequestHeader(value = "Authorization") String authHeader) {
        userService.withdrawUser(id,authHeader);
        return ResponseEntity.ok(new SuccessInfo("WITHDRAW_SUCCESS", "success at withdraw user."));
    }
}