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
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String profile(HttpServletRequest httpServletRequest, HttpSession session) {
        User user = userService.verify((String)session.getAttribute("token"));
        httpServletRequest.setAttribute("user",user);
        return "/user/profile";
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        return user;
//        return "/index.jsp";
    }

    @RequestMapping(value="", method = RequestMethod.PUT)
    public String updateUser(@RequestBody User user, HttpServletResponse response) {

        userService.updateUser(user);
        System.out.println("UPDATE USER: "+ user.getAccount());
        return "/user/profile";
    }

    @DeleteMapping("/{id}")
    public ModelAndView withdrawUser(@PathVariable long id) {
        userService.withdrawUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.jsp");
        return modelAndView;
    }
}
