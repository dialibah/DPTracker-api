package sn.dialibah.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sn.dialibah.common.authentication.model.DialibahUserDetails;
import sn.dialibah.user.model.LoginDataBean;
import sn.dialibah.user.services.TokenAuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nureynisow on 24/05/2017.
 * for DPTracker
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {


    private final static ObjectMapper objectMapper = new ObjectMapper();

    JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        final LoginDataBean credentials = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), LoginDataBean.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(),
                        credentials.getPassword()));
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult)
            throws IOException, ServletException {
        TokenAuthService.addAuthentication(response, (DialibahUserDetails) authResult.getPrincipal(), objectMapper);
    }
}
