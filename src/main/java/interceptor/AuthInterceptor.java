package interceptor;

import auth.JwtToken;
import com.auth0.jwt.exceptions.JWTVerificationException;
import enums.ExceptionMessage;
import exception.token.AccessTokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private final JwtToken jwtToken;

    public AuthInterceptor(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    private boolean hasPathVariable(String servletPath) {
        try {
            String numericCand = servletPath.substring(servletPath.lastIndexOf("/") + 1);
            return numericCand.chars().allMatch(Character::isDigit);
        }
        //인덱스 관련 예외
        catch (Exception e){
            return false;
        }
    }


    private boolean isExceptionUrl(String method, String servletPath) {
        return Objects.equals(method, HttpMethod.GET.toString()) && hasPathVariable(servletPath);
    }
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            if (isExceptionUrl(httpServletRequest.getMethod(), httpServletRequest.getServletPath()))
                return true;

            if (httpServletRequest.getHeader("Authorization") == null || httpServletRequest.getHeader("Authorization").isEmpty())
                throw new AuthorizationServiceException(ExceptionMessage.AUTH_HEADER_IS_EMPTY.getMessage());
            String accessToken = jwtToken.getAccessTokenInHeader(httpServletRequest.getHeader("Authorization"));

            jwtToken.verifyAccessToken(accessToken);

            return true;
        }
        catch(JWTVerificationException e) {
            throw new AccessTokenExpiredException(e.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
