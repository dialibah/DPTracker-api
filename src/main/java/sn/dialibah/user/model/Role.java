package sn.dialibah.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by nureynisow on 15/04/2017.
 * for DekWay
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {


	private String roleId;

	@Override
	public String getAuthority() {
		return roleId;
	}
}
