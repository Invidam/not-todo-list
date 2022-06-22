package controller;


import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AuthController {


    private final UserService userService;


    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
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


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
        System.out.println("login star");
        return "/auth/login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginUser(User user, HttpSession session) {
        String token = userService.login(user);
        if(token != null) {
            session.setAttribute("token",token );
            session.setAttribute("UserAccount",user.getAccount());
        }
        return "index";

    }

    @RequestMapping(value="/info", method = RequestMethod.GET)
    public String userInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = (String)session.getAttribute("token");
        User user = userService.verify(token);
        System.out.println("session UserAccount: " + session.getAttribute("UserAccount"));
        System.out.println("USER ID: "+ user.getId());
        return "index";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {
        session.removeAttribute("token");
        session.removeAttribute("UserAccount");
        return "index";

    }
}
