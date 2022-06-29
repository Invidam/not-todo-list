package controller;

import exception.token.AccessTokenExpiredException;
import exception.token.InCorrectAccessTokenException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class InterceptorController {
//    @GetMapping("/interceptor-error/auth")
//    public void errorAuth(HttpServletRequest httpServletRequest) {
//        throw new AuthorizationServiceException("Header Authorization Is Empty");
//    }
//    @GetMapping("/interceptor-error/token-expired")
//    public void errorTokenExpired(HttpServletRequest httpServletRequest) {
//        throw new AccessTokenExpiredException("Access Token is expired.");
//    }
//    @GetMapping("/interceptor-error/incorrect-token")
//    public void errorIncorrectToken(HttpServletRequest httpServletRequest) {
//        throw new InCorrectAccessTokenException("Header's Access Token is not equal to DB's Refresh Token.");
//    }
    @RequestMapping(value = "/interceptor-error")
    public void error(HttpServletRequest httpServletRequest) throws Exception {
        String name = (String) httpServletRequest.getAttribute("name");
        String message = (String) httpServletRequest.getAttribute("message");
        switch (name) {
            case "AuthorizationServiceException":
                throw new AuthorizationServiceException(message);
            case "AccessTokenExpiredException":
                throw new AccessTokenExpiredException(message);
            case "InCorrectAccessTokenException":
                throw new InCorrectAccessTokenException(message);
            default:
                throw new Exception("Interceptor error.");
        }
    }
}
