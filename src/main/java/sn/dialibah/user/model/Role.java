package sn.dialibah.user.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by nureynisow on 15/04/2017.
 * for DekWay
 */
@Builder
@Data
public class Role implements GrantedAuthority {


	private String roleId;

	public Role(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String getAuthority() {
		return roleId;
	}
}
