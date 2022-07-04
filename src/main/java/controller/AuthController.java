package controller;

import DTO.User.LoginUserDTO;
import DTO.Token.TokenDTO;
import DTO.User.UserAndTokenDTO;
import DTO.User.UserIdAndTokenDTO;
import enums.SuccessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AuthService;
import utility.DTOEntityMapper;

@RestController
@RequestMapping("")
public class AuthController {

    private final AuthService authService;
    private final DTOEntityMapper dtoEntityMapper;

    @Autowired
    public AuthController(AuthService authService, DTOEntityMapper dtoEntityMapper) {
        this.authService = authService;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        UserAndTokenDTO userAndTokenDTO = authService.getLoggedInUserAndToken(dtoEntityMapper.toEntity(loginUserDTO));
        authService.updateUserRefreshToken(new UserIdAndTokenDTO(userAndTokenDTO.getId(),userAndTokenDTO.getToken().getRefreshToken()));
        return ResponseEntity.ok(userAndTokenDTO.getToken());
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseEntity<SuccessStatus> logoutUser(@RequestHeader(value = "Authorization") String accessToken) {
        return  ResponseEntity.ok(SuccessStatus.LOGOUT);
    }

    @RequestMapping(value="/auth/refresh", method = RequestMethod.POST)
    public ResponseEntity<TokenDTO> refreshToken(@RequestBody TokenDTO tokenDTO) {
        long id = authService.verifyRefreshToken(tokenDTO.getRefreshToken());
        authService.checkRefreshToken(new UserIdAndTokenDTO(id,tokenDTO.getRefreshToken()));

        return ResponseEntity.ok(authService.refreshToken(id));
    }
}
