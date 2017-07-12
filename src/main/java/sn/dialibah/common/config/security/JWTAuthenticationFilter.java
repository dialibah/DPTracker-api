package sn.dialibah.common.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import sn.dialibah.user.services.TokenAuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by nureynisow on 15/04/2017.
 * for DekWay
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
		Authentication authentication = TokenAuthService
				.getAuthentication((HttpServletRequest) servletRequest);
		SecurityContextHolder.getContext()
				.setAuthentication(authentication);
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
