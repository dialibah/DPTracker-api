package sn.dialibah.common.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sn.dialibah.common.authentication.model.DialibahUserDetails;
import sn.dialibah.user.model.UserDataBean;
import sn.dialibah.user.services.IUserAccountService;

/**
 * Created by nureynisow on 15/04/2017.
 * for DekWay
 */
@Configuration
public class DialibahUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserAccountService userAccountService;

    @Override
    public DialibahUserDetails loadUserByUsername(final String usernameOrEmail) throws UsernameNotFoundException {
        final UserDataBean userDataBean = userAccountService.getUserDetails(usernameOrEmail);
        return DialibahUserDetails.builder()
                .user(userDataBean).build();
    }
}
