package sn.dialibah.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by nureynisow on 25/03/2017.
 * for DekWay
 */
@Data
@AllArgsConstructor
@Builder
public class ActivationToken {
    private String token;
    private LocalDateTime expirationDateTime;
}
