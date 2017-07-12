package sn.dialibah.user.exception;

import org.springframework.http.HttpStatus;
import sn.dialibah.common.exception.AbstractException;

/**
 * Created by nureynisow on 27/03/2017.
 */
public class LoginFailedException extends AbstractException {

	public LoginFailedException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.UNAUTHORIZED;
	}

	@Override
	public String getErrorCode() {
		return "UNAUTHORIZED";
	}
}
