package sn.dialibah.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by nureynisow on 25/03/2017.
 * for DekWay
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDataBean {

    private String firstName;

    private String lastName;

    @NotNull
    private String username; // TODO [nureynisow] validate with pattern

    @NotNull
    private String password;

    @NotNull
    public String email;
}
