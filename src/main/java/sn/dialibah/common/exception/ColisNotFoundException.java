package sn.dialibah.common.exception;

import org.springframework.http.HttpStatus;

/**
 * by osow on 13/12/17.
 * for dpt
 */
public class ColisNotFoundException extends AbstractException {
	public ColisNotFoundException(String msg) {super(msg);}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public String getErrorCode() {
		return "NOT_FOUND";
	}
}
