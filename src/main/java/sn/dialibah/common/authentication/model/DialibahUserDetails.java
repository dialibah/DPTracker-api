package sn.dialibah.common.authentication.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.dialibah.user.model.UserDataBean;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by nureynisow on 15/04/2017.
 * for DekWay
 */
@Data
@Builder
public class DialibahUserDetails implements UserDetails {

    private UserDataBean user;

    public DialibahUserDetails(final UserDataBean user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(user.getRole());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
