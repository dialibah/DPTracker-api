package sn.dialibah.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by nureynisow on 27/03/2017.
 * for DekWay
 */
public abstract class AbstractException extends RuntimeException {

    ResponseEntity forwardedResponse;

    public AbstractException(final String message, final ResponseEntity forwardedResponse) {
        super(message);
        this.forwardedResponse = forwardedResponse;
    }


    public AbstractException(final String message) {
        super(message);
    }


    public abstract HttpStatus getStatus();

    public abstract String getErrorCode();

    public ResponseEntity getForwardedResponse() {
        return this.forwardedResponse;
    }

    /**
     * Check if exception contains inner forwarded response to process
     *
     * @return true if {@code forwardedResponse} is not empty, false else
     */
    public boolean hasForwardedResponse() {
        return forwardedResponse != null;
    }

}
