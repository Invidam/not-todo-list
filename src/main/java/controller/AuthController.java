package controller;


import DTO.Response.SuccessInfo;
import DTO.User.LoginUserDTO;
import DTO.Token.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.web.bind.annotation.*;
import service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        return ResponseEntity.ok(authService.login(loginUserDTO));
    }

    @RequestMapping(value="/auth/refresh", method = RequestMethod.POST)
    public ResponseEntity<TokenDTO> refreshToken(@RequestBody TokenDTO tokenDTO) {
        return  ResponseEntity.ok(authService.refreshToken(tokenDTO.getRefreshToken()));
    }
    /*
    * @RequestHeader("Authorization") 으로 받은 Accesstoken 처리하기
    * Bearere ${ACcess token } 꼴임.
    * */

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseEntity<SuccessInfo> logoutUser(@NotEmpty(message = "Access Token is empty.") @RequestHeader(value = "Authorization") String accessToken) {

        return  ResponseEntity.ok(new SuccessInfo("LOGOUT_SUCCESS", "success at logout."));
    }
}
