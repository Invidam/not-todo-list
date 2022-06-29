package controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import exception.emotion.AlreadyExistEmotionRelationException;
import exception.emotion.EmotionRelationNotFoundException;
import exception.item.ItemNotFoundException;
import exception.token.AccessTokenExpiredException;
import exception.token.InCorrectAccessTokenException;
import exception.token.InCorrectRefreshTokenException;
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

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final String INVALID_TOKEN = "Bearer error=invalid_token";

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
    @ExceptionHandler(value = InCorrectRefreshTokenException.class)
    public ResponseEntity<ErrorInfo> handleInCorrectRefreshTokenException(InCorrectRefreshTokenException e) {
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
    @ExceptionHandler(value = AccessTokenExpiredException.class)
    public ResponseEntity<ErrorInfo> handleInCorrectAccessTokenException(AccessTokenExpiredException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("ACCESS_TOKEN_EXPIRED",e.getMessage(),401,-115);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("WWW-Authenticate", INVALID_TOKEN).body(errorInfo);

    }
    @ExceptionHandler(value = InCorrectAccessTokenException.class)
    public ResponseEntity<ErrorInfo> handleInCorrectAccessTokenException(InCorrectAccessTokenException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("INCORRECT_ACCESS_TOKEN",e.getMessage(),401,-116);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("WWW-Authenticate", INVALID_TOKEN).body(errorInfo);

    }
    @ExceptionHandler(value = AuthorizationServiceException.class)
    public ResponseEntity<ErrorInfo> handleAuthorizationServiceException(AuthorizationServiceException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("TOKEN_NOT_EXIST",e.getMessage(),401,-120);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorInfo);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorInfo> handleUserAlreadyExistException(UserAlreadyExistException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("ALREADY_EXIST_USER",e.getMessage(),400,-200);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);

    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleItemNotFoundException(ItemNotFoundException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("ITEM_NOT_FOUND",e.getMessage(),404,-300);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler(AlreadyExistEmotionRelationException.class)
    public ResponseEntity<ErrorInfo> handleAlreadyExistEmotionException(AlreadyExistEmotionRelationException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("ALREADY_EXIST_EMOTION_RELATION",e.getMessage(),400,-400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);

    }
    @ExceptionHandler(EmotionRelationNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleEmotionNotFoundException(EmotionRelationNotFoundException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("EMOTION_RELATION_NOT_FOUND",e.getMessage(),404,-401);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);

    }
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
////    }
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorInfo> handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("NULL_POINTER",e.getMessage(),400,-1);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);

    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorInfo> handleException(Exception e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo("UNEXPECTED_ERROR",e.getMessage(),500,0);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);

    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ErrorInfo> handleTestException(Exception e) {
//        ErrorInfo errorInfo = new ErrorInfo("test","testexception","no detail",400,-1);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
//
//    }
}
