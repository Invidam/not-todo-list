package auth;

import DTO.Token.TokenDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtToken {


    private final String accessSecretKey;


    private final String refreshSecretKey;

    private final int accessTokenValidTime = 1000 * 60 * 30; // 30m
    private final int refreshTokenValidTime = 1000 * 60 * 60 * 24 * 14; // 14d

    @Autowired
    public JwtToken(@Value("${jwt.accessSecretKey}") String accessSecretKey,@Value("${jwt.refreshSecretKey}") String refreshSecretKey) {
        this.accessSecretKey = accessSecretKey;
        this.refreshSecretKey = refreshSecretKey;
    }
    public String getAccessTokenInHeader(String header) {
        return header.substring(7);
    }
    private Map<String, Object> makeHeader() {

        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HS256");
        headerClaims.put("typ", "JWT");

        return headerClaims;
    }
    private Map<String, String> makePayload(User user, boolean isAccessToken) {

        Map<String, String> payloadClaims  = new HashMap<>();
        if(isAccessToken) {
            payloadClaims.put("id", Long.toString(user.getId()));
            payloadClaims.put("nickname", user.getNickname());
        }
        else
            payloadClaims.put("id", Long.toString(user.getId()));
        return payloadClaims;
    }
    public String createToken(User user, boolean isAccessToken) {
        return JWT.create().withIssuer("hspark")
                .withExpiresAt(new Date((new Date().getTime() + (isAccessToken ? accessTokenValidTime : refreshTokenValidTime))))
                .withHeader(makeHeader())
                .withPayload(makePayload(user,isAccessToken))
                .sign(Algorithm.HMAC256(isAccessToken ? accessSecretKey : refreshSecretKey));
    }
    public TokenDTO create(User user) {
        try {
            return new TokenDTO(createToken(user,true), createToken(user,false));
        }
        catch(JWTCreationException exception) {
            //클레임에 오류가 있을 경우
            exception.printStackTrace();
            throw new JWTCreationException("In Create Token, Error appeared.",null);
        }
    }
    private DecodedJWT decodeToken(String token, String secretKey, int validTime) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                    .withIssuer("hspark")
                    .build();

            return verifier.verify(token);
        }
        catch(JWTDecodeException exception) {
            exception.printStackTrace();
            throw new JWTDecodeException("In Decode Token, Error appeared.",null);
        }

    }

    public boolean isExpiredRefreshToken(String refreshToken) {
        User user = new User();
        try {
            DecodedJWT jwt = decodeToken(refreshToken,refreshSecretKey,refreshTokenValidTime);
            return jwt.getExpiresAt().before(new Date());
        }
        catch(TokenExpiredException exception) {
            return true;
        }
        catch(JWTVerificationException exception){
            exception.printStackTrace();
            throw new JWTVerificationException("In Verify Token, Error appeared.",null);
        }
    }

    public long verifyRefreshToken(String refreshToken) {
        return Long.parseLong(decodeToken(refreshToken,refreshSecretKey,refreshTokenValidTime).getClaim("id").asString());
    }
    public long verifyAccessToken(String accessToken) {

        DecodedJWT jwt = decodeToken(accessToken,accessSecretKey,accessTokenValidTime);

        return Long.parseLong(jwt.getClaim("id").asString());
    }

}

