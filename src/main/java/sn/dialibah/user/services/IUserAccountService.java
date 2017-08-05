package sn.dialibah.user.services;

import sn.dialibah.user.model.RegistrationDataBean;
import sn.dialibah.user.model.UserDataBean;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by nureynisow on 25/03/2017.
 */
public interface IUserAccountService {

	/**
	 * Register a user in the system
	 *
	 * @param registrationDataBean object
	 * @return a {@link UserDataBean}
	 */
	UserDataBean register(RegistrationDataBean registrationDataBean) throws NoSuchAlgorithmException;

	/**
	 * Check username and passwordd and return UserProfile
	 *
	 * @param usernameOrEmail string
	 * @param password string
	 * @return a {@link UserDataBean} representing Profile
	 */
	UserDataBean login(String usernameOrEmail, String password);

	/**
	 *  Get the user profile details by username or email
	 * @param usernameOrEmail string
	 * @return a {@link UserDataBean}
	 */
	UserDataBean getUserDetails(String usernameOrEmail);

	/**
	 * Retrieves all users
	 * @return a List of {@link UserDataBean}
	 */
	List<UserDataBean> getUsers();
}
