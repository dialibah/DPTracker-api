package sn.dialibah.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class UserDataBean {

	private String firstName;

	private String lastName;

	private String password;

	@NotNull
	private String username; // TODO [nureynisow] validate with pattern

	@NotNull
	private String email;

	@NotNull
	private ActivationToken activationToken;

	private boolean active;

	private Role role;
}
