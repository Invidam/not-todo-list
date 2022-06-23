package controller;


import DTO.LoginUserDTO;
import DTO.TokenDTO;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }



//    @RequestMapping(value="/login", method = RequestMethod.GET)
//    public String login() {
//        System.out.println("login star");
//        return "/auth/login";
//    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public TokenDTO loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        return authService.login(loginUserDTO);
    }

    @RequestMapping(value="/auth/refresh", method = RequestMethod.GET)
    public TokenDTO refreshToken(@RequestHeader("x-jwt-refresh-token") String refreshToken) {

        return authService.refreshToken(new TokenDTO(null,refreshToken));
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {
        session.removeAttribute("token");
        session.removeAttribute("UserAccount");
        return "index";

    }
}
