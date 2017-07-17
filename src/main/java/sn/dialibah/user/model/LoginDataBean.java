package sn.dialibah.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by nureynisow on 25/03/2017.
 */
@Data
@Builder
@AllArgsConstructor
public class LoginDataBean {

	@NotNull
	private String email;

	@NotNull
	private String password;
}
