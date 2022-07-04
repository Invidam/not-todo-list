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