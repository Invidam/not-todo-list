package controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import exception.token.InCorreftRefreshTokenException;
import exception.token.RefreshTokenExpiredException;
import exception.user.UserAlreadyExistException;
import exception.user.UserMismatchException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import DTO.Response.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final String INVALID_TOKEN = "Bearer error=invalid_token";

    @RequestMapping(value="/interceptor-error", method = RequestMethod.GET)
    public void interceptorError(HttpServletRequest httpServletRequest) throws Exception {
        Exception exception = (Exception) httpServletRequest.getAttribute("exception");
        String name = (String) httpServletRequest.getAttribute("name");
        String message = (String) httpServletRequest.getAttribute("message");
        System.out.println("CASE: " + name);
        switch (name) {
            case "AuthorizationServiceException": throw new AuthorizationServiceException(message);
            case "RefreshTokenExpiredException": throw new RefreshTokenExpiredException(message);
            case "InCorreftRefreshTokenException": throw new InCorreftRefreshTokenException(message);
            default: throw new Exception("Interceptor error.");
        }

    }


    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleUserNotFoundException(UsernameNotFoundException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("USER_NOT_FOUND",e.getMessage(),404,-100);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);

    }
    @ExceptionHandler(value = AccountExpiredException.class)
    public ResponseEntity<ErrorInfo> handleAccountExpiredException(AccountExpiredException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("ACCOUNT_EXPIRED",e.getMessage(),401,-101);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorInfo);

    }
    @ExceptionHandler(value = BadCredentialsException.class) //WrongPasswordException
    public ResponseEntity<ErrorInfo> handleBadCredentialsException(BadCredentialsException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("WRONG_PASSWORD",e.getMessage(),401,-102);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorInfo);

    }
//UserMismatchException
    @ExceptionHandler(UserMismatchException.class)
    public ResponseEntity<ErrorInfo> handleF(UserMismatchException e) {
//        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("USER_MISMATCH",e.getMessage(),403,-103);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorInfo);

    }

    @ExceptionHandler(value = RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorInfo> handleRefreshTokenExpiredException(RefreshTokenExpiredException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("REFRESH_TOKEN_IS_EXPIRED",e.getMessage(),401,-110);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("WWW-Authenticate", INVALID_TOKEN).body(errorInfo);

    }
    @ExceptionHandler(value = InCorreftRefreshTokenException.class)
    public ResponseEntity<ErrorInfo> handleInCorrectRefreshTokenException(InCorreftRefreshTokenException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("INCORRECT_REFRESH_TOKEN",e.getMessage(),403,-111);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).header("WWW-Authenticate", INVALID_TOKEN).body(errorInfo);

    }
    @ExceptionHandler(value = JWTCreationException.class)
    public ResponseEntity<ErrorInfo> handleJWTCreationException(JWTCreationException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("TOKEN_NOT_CREATED",e.getMessage(),401,-112);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("WWW-Authenticate", INVALID_TOKEN).body(errorInfo);

    }
    @ExceptionHandler(value = JWTVerificationException.class)
    public ResponseEntity<ErrorInfo> handleJWTVerificationException(JWTVerificationException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("TOKEN_NOT_VERIFIED",e.getMessage(),401,-113);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("WWW-Authenticate", INVALID_TOKEN).body(errorInfo);

    }
    @ExceptionHandler(value = JWTDecodeException.class)
    public ResponseEntity<ErrorInfo> handleJWTDecodeException(JWTDecodeException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("TOKEN_NOT_DECODED",e.getMessage(),401,-114);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("WWW-Authenticate", INVALID_TOKEN).body(errorInfo);

    }
    @ExceptionHandler(value = AuthorizationServiceException.class)
    public ResponseEntity<ErrorInfo> handleAuthorizationServiceException(AuthorizationServiceException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("TOKEN_NOT_EXIST",e.getMessage(),401,-120);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorInfo);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorInfo> handleF(UserAlreadyExistException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("ALREADY_EXIST_USER",e.getMessage(),400,-200);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);

    }
//    @ExceptionHandler(X.class)
//    public ResponseEntity<ErrorInfo> handleF(X e) {
////        e.printStackTrace();
//        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
//        return ResponseEntity.status(HttpStatus.).body(errorInfo);
//
//    }
//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }//    @ExceptionHandler(X.class)
////    public ResponseEntity<ErrorInfo> handleF(X e) {
//////        e.printStackTrace();
////        ErrorInfo errorInfo = new ErrorInfo("","",4,-);
////        return ResponseEntity.status(HttpStatus.).body(errorInfo);
////
////    }
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorInfo> handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("NULL_POINTER",e.getMessage(),400,-1);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);

    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ErrorInfo> handleTestException(Exception e) {
//        ErrorInfo errorInfo = new ErrorInfo("test","testexception","no detail",400,-1);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
//
//    }
}
