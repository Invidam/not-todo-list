package auth;

import DTO.TokenDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@PropertySource("classpath:application.properties")
@Component
public class JwtToken {

    @Value("${jwt.accessSecretKey:default}")
    private String accessSecretKey;
    @Value("${jwt.refreshSecretKey:default}")
    private String refreshSecretKey;

    private final int accessTokenValidTime = 1000 * 60 * 30; // 30m
    private final int refreshTokenValidTime = 1000 * 60 * 60 * 24 * 14; // 14d

    @Autowired
    public JwtToken() {}
    private Map<String, Object> makeHeader() {

        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HMCA256");
        headerClaims.put("typ", "JWT");

        return headerClaims;
    }
    private Map<String, String> makePayload(User user, boolean isAccessToken) {

        Map<String, String> payloadClaims  = new HashMap<>();
        if(isAccessToken) {
            payloadClaims.put("account", user.getAccount());
            payloadClaims.put("nickname", user.getNickname());
        }
        else
            payloadClaims.put("id", Long.toString(user.getId()));
        return payloadClaims;
    }
    public String createToken(User user, boolean isAccessToken) {
        return JWT.create().withIssuer("hspark").withExpiresAt(new Date((new Date().getTime() + (isAccessToken ? accessTokenValidTime : refreshTokenValidTime)))).withHeader(makeHeader()).withPayload(makePayload(user,isAccessToken)).sign(Algorithm.HMAC256(isAccessToken ? accessSecretKey : refreshSecretKey));
    }
    public TokenDTO create(User user, boolean isOnlyAccess) {
        try {
            return isOnlyAccess ? new TokenDTO(createToken(user,true),null) : new TokenDTO(createToken(user,true), createToken(user,false));
        }
        catch(JWTCreationException exception) {
            throw exception;
        }
    }
    private DecodedJWT verifyToken(String accessToken,String secretKey, int validTime) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .withIssuer("hspark")
                .acceptExpiresAt(validTime)
                .build();

        return verifier.verify(accessToken);
    }

    public boolean isValidAccessToken(TokenDTO tokenDTO) {
        User user = new User();
        try {
            DecodedJWT jwt = verifyToken(tokenDTO.getAccessToken(),accessSecretKey,accessTokenValidTime);
            return jwt.getExpiresAt().after(new Date());
        }
        catch(JWTVerificationException exception){
            throw exception;
        }
    }

    public boolean isValidRefreshToken(TokenDTO tokenDTO) {
        User user = new User();
        try {
            DecodedJWT jwt = verifyToken(tokenDTO.getRefreshToken(),refreshSecretKey,refreshTokenValidTime);
            return jwt.getExpiresAt().after(new Date());
        }
        catch(JWTVerificationException exception){
            throw exception;
        }
    }


    //ref 만료 확인
        //create
        //access 만료 확인
            //재발급
            //
    //토큰이 유효하지 않으면교체해주는 함수
    //엑세스 토큰이 리프레시 토큰과 같은 값을 가지는지 확인하는 함수
    public Long getIdInRefreshToken(TokenDTO tokenDTO) {
        return Long.parseLong(verifyToken(tokenDTO.getRefreshToken(),refreshSecretKey,refreshTokenValidTime).getClaim("id").asString());
    }
    public User verify(TokenDTO tokenDTO) {
        User user = new User();
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(accessSecretKey))
                    .withIssuer("hspark")
                    .acceptExpiresAt(60* 60) // 1 hour
                    .build();
            DecodedJWT jwt = verifyToken(tokenDTO.getAccessToken(),accessSecretKey,accessTokenValidTime);

            user.setId(Long.parseLong(jwt.getClaim("id").asString()));
            user.setAccount(jwt.getClaim("account").asString());
            user.setNickname(jwt.getClaim("nickname").asString());

            System.out.println("DEOCDE" + jwt.getToken());
            System.out.println(user.getId());
            return user;
        }
        catch(JWTVerificationException exception){
            System.out.println("JWT VERIFY EXCEPT: "+ exception.getMessage());
            user.setId(-1L);
            return user;
        }
    }

}
