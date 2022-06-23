package controller;


import domain.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.portlet.ModelAndView;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/user/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest httpServletRequest) {
//        TokenDTO tokenDTO = new TokenDTO(httpServletRequest.getHeader("x-jwt-access-token"),httpServletRequest.getHeader("x-jwt-refresh-token"));
//
//        User user = userService.verify(tokenDTO);
//        httpServletRequest.setAttribute("user",user);
        return "/user/profile";
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        return user;
//        return "/index.jsp";
    }

    @RequestMapping(value="/user", method = RequestMethod.PUT)
    public String updateUser(User user, HttpServletResponse response) {

        userService.updateUser(user);
        System.out.println("UPDATE USER: "+ user.getAccount());
        return "/user/profile";
    }

    @DeleteMapping("/user/{id}")
    public ModelAndView withdrawUser(@PathVariable long id) {
        userService.withdrawUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.jsp");
        return modelAndView;
    }
    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String signup() {
        System.out.println("signyp star");
        return "/auth/signup";
    }
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String createUser(User user) {
        System.out.println(user.getAccount());
        userService.createUser(user);
        return "index";
    }

}
