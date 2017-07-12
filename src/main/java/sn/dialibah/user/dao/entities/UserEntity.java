package sn.dialibah.user.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import sn.dialibah.user.model.ActivationToken;
import sn.dialibah.user.model.Role;

/**
 * Created by nureynisow on 25/03/2017.
 * for DekWay
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
@Builder
public class UserEntity {

	@Id
	private String id;

	private String firstName;

	private String lastName;

	@Indexed(unique = true)
	private String username; // TODO [nureynisow] validate with pattern

	@Indexed(unique = true)
	private String email;

	private String password;

	private ActivationToken activationToken;

	private boolean active;

	private Role role;

}
