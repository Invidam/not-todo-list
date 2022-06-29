package interceptor;

import auth.JwtToken;
import exception.token.AccessTokenExpiredException;
import exception.token.InCorrectAccessTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor implements HandlerInterceptor {
    //걍 냅둔다.
    //
    @Autowired
    private final JwtToken jwtToken;

    public AuthInterceptor(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }
    /*
    *없는 경우
    * 1. 로그인
    * 2. 회원가입
    *
    * 4. 게시글 보기
    * 있는 경우
    * 1. 로그 아웃
    * 2. 개인정보수정 페이지
    * 3. 회원 탈퇴 페이지
    * 4. jwt 갱신 요청
    *
    * 5. 게시글 생성
    * 6. 게시글 수정
    * 7. 게시글 삭제
    *
    * 8. 감정표현
    * 9. 공유하기
    * */
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
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {

            //CASE:
            //GET item/{id}
            //GET user/{id}
            if(Objects.equals(httpServletRequest.getMethod(), "GET") && hasPathVariable(httpServletRequest.getServletPath()))
                return true;

            if(httpServletRequest.getHeader("Authorization") == null || httpServletRequest.getHeader("Authorization").isEmpty())
                throw new AuthorizationServiceException("Header Authorization Is Empty");
            String accessToken = jwtToken.getAccessTokenInHeader(httpServletRequest.getHeader("Authorization"));
            if(jwtToken.isExpiredAccessToken(accessToken))
                throw new AccessTokenExpiredException("Access Token is expired.");

            if (Objects.isNull(jwtToken.verify(accessToken)))
                throw new InCorrectAccessTokenException("Header's Access Token is not equal to DB's Refresh Token.");
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            httpServletRequest.setAttribute("name",e.getClass().getSimpleName());
            httpServletRequest.setAttribute("message",e.getMessage());
            httpServletRequest.getRequestDispatcher("/interceptor-error").forward(httpServletRequest,httpServletResponse);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
