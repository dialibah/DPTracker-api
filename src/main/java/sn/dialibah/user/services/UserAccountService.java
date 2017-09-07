package sn.dialibah.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.dialibah.user.dao.entities.UserEntity;
import sn.dialibah.user.dao.repositories.UserRepository;
import sn.dialibah.user.exception.LoginFailedException;
import sn.dialibah.user.model.ActivationToken;
import sn.dialibah.user.model.RegistrationDataBean;
import sn.dialibah.user.model.Role;
import sn.dialibah.user.model.UserDataBean;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by nureynisow on 25/03/2017.
 * for DekWay
 */
@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDataBean register(final RegistrationDataBean registrationDataBean) throws NoSuchAlgorithmException {
        final LocalDateTime now = LocalDateTime.now();
        final UserEntity userEntity = UserEntity.builder()
                .firstName(registrationDataBean.getFirstName())
                .lastName(registrationDataBean.getLastName())
                .username(registrationDataBean.getUsername())
                .password(passwordEncoder.encode(registrationDataBean.getPassword()))
                .email(registrationDataBean.getEmail())
                .activationToken(
                        ActivationToken.builder()
                                .token(Base64.getEncoder().encodeToString(now.toString().getBytes()))
                                .expirationDateTime(now.plusDays(1))
                                .build()
                ).role(Role.builder()
                        .roleId("USER")
                        .build()
                )
                .build();
        return fromEntity(userRepository.save(userEntity));
    }

    @Override
    public UserDataBean login(final String usernameOrEmail, final String password) {
        final UserEntity userEntity;
        if (usernameOrEmail.contains("@")) {
            userEntity = userRepository.findUserByEmail(usernameOrEmail);
        } else {
            userEntity = userRepository.findUserByUsername(usernameOrEmail);
        }
        if (userEntity == null || !passwordEncoder.matches(password, userEntity.getPassword()))
            throw new LoginFailedException("USERNAME OR PASSWORD WRONG");
        return fromEntity(userEntity);
    }

    @Override
    public UserDataBean getUserDetails(final String usernameOrEmail) {
        final UserEntity userEntity;
        if (usernameOrEmail.contains("@")) {
            userEntity = userRepository.findUserByEmail(usernameOrEmail);
        } else {
            userEntity = userRepository.findUserByUsername(usernameOrEmail);
        }
        if (userEntity == null)
            throw new LoginFailedException("USERNAME NOT FOUND");
        return fromEntity(userEntity);
    }

    @Override
    public List<UserDataBean> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), true)
                .map(UserAccountService::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDataBean patch(String id, UserDataBean user) {
        return this.userRepository.patchUser(id, user);
    }

    private static UserDataBean fromEntity(final UserEntity userEntity) {
        return UserDataBean.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .active(userEntity.isActive())
                .activationToken(userEntity.getActivationToken())
                .build();
    }
}
