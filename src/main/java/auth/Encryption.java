package auth;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Encryption {
    public final String salt = BCrypt.gensalt(10);

    public boolean isMatch(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public String generate(String password) {
        return BCrypt.hashpw(password, salt);
    }
}
//{
//        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJoc3BhcmsiLCJuaWNrbmFtZSI6InRlc3QiLCJleHAiOjE2NTYwNzYwMDAsImFjY291bnQiOiJ0ZXN0In0.6nVldUsHSWzzXKdwi6etfen7M8zuzo-8BBSfttCU0p4",
//        "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJoc3BhcmsiLCJpZCI6IjYiLCJleHAiOjE2NTcyODM4MDB9.GBdRxe7o_d6lu9IuT5kNzRQG-FaPt2zl7poj7jJoBwM",
//        "tokenType": "bearer"
//        }