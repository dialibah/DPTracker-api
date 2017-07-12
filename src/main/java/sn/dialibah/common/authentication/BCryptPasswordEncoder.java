package sn.dialibah.common.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by nureynisow on 15/04/2017.
 * for DekWay
 */
@Configuration
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(final CharSequence rawPassword) {
        return BCrypt.hashpw((String) rawPassword, BCrypt.gensalt(12));
    }

    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        return BCrypt.checkpw(String.valueOf(rawPassword), encodedPassword);
    }
}
