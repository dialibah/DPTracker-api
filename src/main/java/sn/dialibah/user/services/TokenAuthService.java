package sn.dialibah.user.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import sn.dialibah.common.authentication.model.DialibahUserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyList;

/**
 * Created by nureynisow on 13/04/2017.
 * for DekWay
 */
public class TokenAuthService {

	private static final long EXPIRATION_TIME = 3600000;
	private static final String SECRET = "secret";
	private static final String TOKEN_PREFIX = "Bearer";
	private static final String AUTH_HEADER_NAME = "Authorization";


	public static void createAuthToken(HttpServletResponse res, String email){
		String jwt = Jwts.builder().compact();
	}

	public static void addAuthentication(HttpServletResponse response, DialibahUserDetails user, ObjectMapper objectMapper) throws IOException {
		String jwt = Jwts.builder()
				.setSubject(user.getUser().getEmail())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		Map<String, String> profile = new HashMap<>();
		profile.put("email", user.getUser().getEmail());
		profile.put("firstname", user.getUser().getFirstName());
		profile.put("lastname", user.getUser().getLastName());
		profile.put("username", user.getUser().getUsername());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		objectMapper.writeValue(response.getWriter(), profile);
		response.addHeader(AUTH_HEADER_NAME, TOKEN_PREFIX+" "+jwt);

	}

	public static Authentication getAuthentication(HttpServletRequest servletRequest) {
		String token = servletRequest.getHeader(AUTH_HEADER_NAME);
		if(token != null){
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX,""))
					.getBody().getSubject();
			return user == null ? null : new UsernamePasswordAuthenticationToken(user, null, emptyList());
		}
		return null;
	}
}
