package controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import enums.ErrorStatus;
import exception.emotion.AlreadyExistEmotionRelationException;
import exception.emotion.EmotionRelationNotFoundException;
import exception.item.ItemNotFoundException;
import exception.item.ItemNotPassedDeadlineException;
import exception.token.AccessTokenExpiredException;
import exception.token.InCorrectAccessTokenException;
import exception.token.InCorrectRefreshTokenException;
import exception.token.RefreshTokenExpiredException;
import exception.user.UserAlreadyExistException;
import exception.user.UserMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import utility.Response.ErrorInfo;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final String INVALID_TOKEN_HEADER_KEY = "WWW-Authenticate";
    private final String INVALID_TOKEN_HEADER_VALUE = "Bearer error=invalid_token";

    private ResponseEntity<ErrorInfo> responseFor(ErrorStatus errorStatus, Exception e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo(errorStatus.getType(), e.getMessage(), errorStatus.getStatus().value(),errorStatus.getCode());
        return ResponseEntity.status(errorStatus.getStatus()).body(errorInfo);
    }
    private ResponseEntity<ErrorInfo> tokenResponseFor(ErrorStatus errorStatus, Exception e) {
        e.printStackTrace();
        ErrorInfo errorInfo = new ErrorInfo(errorStatus.getType(), e.getMessage(), errorStatus.getStatus().value(),errorStatus.getCode());
        return ResponseEntity.status(errorStatus.getStatus()).header(INVALID_TOKEN_HEADER_KEY, INVALID_TOKEN_HEADER_VALUE).body(errorInfo);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleUserNotFoundException(UsernameNotFoundException e) {
        e.printStackTrace();
        return responseFor(ErrorStatus.USER_NOT_FOUND, e);
    }
    @ExceptionHandler(value = AccountExpiredException.class)
    public ResponseEntity<ErrorInfo> handleAccountExpiredException(AccountExpiredException e) {
        return responseFor(ErrorStatus.ACCOUNT_EXPIRED, e);
    }
    @ExceptionHandler(value = BadCredentialsException.class) //WrongPasswordException
    public ResponseEntity<ErrorInfo> handleBadCredentialsException(BadCredentialsException e) {
        return responseFor(ErrorStatus.WRONG_PASSWORD, e);

    }
    @ExceptionHandler(UserMismatchException.class)
    public ResponseEntity<ErrorInfo> handleUserMismatchException(UserMismatchException e) {
        return responseFor(ErrorStatus.USER_MISMATCH, e);

    }

    @ExceptionHandler(value = RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorInfo> handleRefreshTokenExpiredException(RefreshTokenExpiredException e) {
        return responseFor(ErrorStatus.REFRESH_TOKEN_IS_EXPIRED, e);
    }

    @ExceptionHandler(value = InCorrectRefreshTokenException.class)
    public ResponseEntity<ErrorInfo> handleInCorrectRefreshTokenException(InCorrectRefreshTokenException e) {
        return responseFor(ErrorStatus.INCORRECT_REFRESH_TOKEN, e);
    }

    @ExceptionHandler(value = JWTCreationException.class)
    public ResponseEntity<ErrorInfo> handleJWTCreationException(JWTCreationException e) {
        return responseFor(ErrorStatus.TOKEN_NOT_CREATED, e);

    }

    @ExceptionHandler(value = JWTVerificationException.class)
    public ResponseEntity<ErrorInfo> handleJWTVerificationException(JWTVerificationException e) {
        return responseFor(ErrorStatus.TOKEN_NOT_VERIFIED, e);
    }

    @ExceptionHandler(value = JWTDecodeException.class)
    public ResponseEntity<ErrorInfo> handleJWTDecodeException(JWTDecodeException e) {
        return responseFor(ErrorStatus.TOKEN_NOT_DECODED, e);
    }

    @ExceptionHandler(value = AccessTokenExpiredException.class)
    public ResponseEntity<ErrorInfo> handleInCorrectAccessTokenException(AccessTokenExpiredException e) {
        return responseFor(ErrorStatus.ACCESS_TOKEN_EXPIRED, e);
    }

    @ExceptionHandler(value = InCorrectAccessTokenException.class)
    public ResponseEntity<ErrorInfo> handleInCorrectAccessTokenException(InCorrectAccessTokenException e) {
        return responseFor(ErrorStatus.INCORRECT_ACCESS_TOKEN, e);
    }

    @ExceptionHandler(value = AuthorizationServiceException.class)
    public ResponseEntity<ErrorInfo> handleAuthorizationServiceException(AuthorizationServiceException e) {
        return responseFor(ErrorStatus.TOKEN_NOT_EXIST, e);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorInfo> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return responseFor(ErrorStatus.ALREADY_EXIST_USER, e);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleItemNotFoundException(ItemNotFoundException e) {
        return responseFor(ErrorStatus.ITEM_NOT_FOUND, e);
    }

    @ExceptionHandler(ItemNotPassedDeadlineException.class)
    public ResponseEntity<ErrorInfo> handleItemPassedDeadlineException(ItemNotPassedDeadlineException e) {
        return responseFor(ErrorStatus.ITEM_NOT_PASSED_DEADLINE, e);
    }

    @ExceptionHandler(AlreadyExistEmotionRelationException.class)
    public ResponseEntity<ErrorInfo> handleAlreadyExistEmotionException(AlreadyExistEmotionRelationException e) {
        return responseFor(ErrorStatus.ALREADY_EXIST_EMOTION_RELATION, e);
    }

    @ExceptionHandler(EmotionRelationNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleEmotionNotFoundException(EmotionRelationNotFoundException e) {
        return responseFor(ErrorStatus.EMOTION_RELATION_NOT_FOUND, e);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorInfo> handleException(Exception e) {
        return responseFor(ErrorStatus.UNEXPECTED_ERROR, e);
    }
}
