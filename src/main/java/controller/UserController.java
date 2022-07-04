package controller;


import DTO.Item.UserRepresentInfoDTO;
import DTO.User.RankDTO;
import DTO.User.SignupUserDTO;
import DTO.User.UpdateUserDTO;
import auth.JwtToken;
import domain.User;
import enums.SuccessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import utility.DTOEntityMapper;

@RestController
@RequestMapping("")
public class UserController {

    private final JwtToken jwtToken;
    private final UserService userService;
    private final DTOEntityMapper dtoEntityMapper;

    @Autowired
    public UserController(JwtToken jwtToken, UserService userService, DTOEntityMapper dtoEntityMapper) {
        this.jwtToken = jwtToken;
        this.userService = userService;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public  ResponseEntity<SuccessStatus> createUser(@RequestBody SignupUserDTO signupUserDTO) {
        User user = dtoEntityMapper.toEntity(signupUserDTO);
        userService.checkExistInfo(user);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatus.SIGNUP);
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public UserRepresentInfoDTO getUserById(@PathVariable long id) {
        return dtoEntityMapper.toDTO(userService.getUserRepresentInfoById(id));
    }

    @RequestMapping(value="/user", method = RequestMethod.PUT)
    public ResponseEntity<SuccessStatus> updateUser(@RequestBody UpdateUserDTO updateUserDTO, @RequestHeader(value = "Authorization") String authHeader) {
        userService.checkUserById(updateUserDTO.getId(),jwtToken.getAccessTokenInHeader(authHeader));
        userService.updateUser(dtoEntityMapper.toEntity(updateUserDTO),updateUserDTO.getUpdatedPassword());
        return ResponseEntity.ok(SuccessStatus.UPDATE_USER);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<SuccessStatus> withdrawUser(@PathVariable long id, @RequestHeader(value = "Authorization") String authHeader) {
        userService.checkUserById(id,jwtToken.getAccessTokenInHeader(authHeader));
        userService.withdrawUser(id);
        return ResponseEntity.ok(SuccessStatus.WITHDRAW_USER);
    }

    @RequestMapping(value="/rank", method = RequestMethod.GET)
    public ResponseEntity<RankDTO> getRank() {
        return ResponseEntity.ok(dtoEntityMapper.toDTO(userService.getRank()));
    }
}