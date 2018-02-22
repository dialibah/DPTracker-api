package sn.dialibah.user.services;

import com.mongodb.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.dialibah.common.exception.AbstractException;
import sn.dialibah.user.dao.entities.UserEntity;
import sn.dialibah.user.dao.repositories.UserRepository;
import sn.dialibah.user.exception.LoginFailedException;
import sn.dialibah.user.model.ActivationToken;
import sn.dialibah.user.model.RegistrationDataBean;
import sn.dialibah.user.model.Role;
import sn.dialibah.user.model.UserDataBean;

import javax.annotation.PostConstruct;
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
@Slf4j
public class UserAccountService implements IUserAccountService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final DozerBeanMapper mapper;

    @Autowired
    public UserAccountService(UserRepository userRepository, PasswordEncoder passwordEncoder, DozerBeanMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDataBean register(final RegistrationDataBean registrationDataBean) throws NoSuchAlgorithmException, DuplicateKeyException {
        log.debug("Registering user : {}", registrationDataBean.getEmail());
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
        UserEntity saved = null;
        try{
            saved= userRepository.save(userEntity);
        }catch(org.springframework.dao.DuplicateKeyException dke){
            throw new AbstractException("Duplicated User") {
                @Override
                public HttpStatus getStatus() {
                    return HttpStatus.CONFLICT;
                }

                @Override
                public String getErrorCode() {
                    return "ALREADY_EXISTS";
                }
            };
        }

        return fromEntity(saved);
    }

    @PostConstruct
    private void registerRoot() throws NoSuchAlgorithmException {
        final String email = "root@dialibah.fr";
        userRepository.deleteByEmail(email);
        UserDataBean user = this.register(RegistrationDataBean.builder()
                .email(email)
                .password("Azerty123")

                .build());
        this.activateUser(user.getId());
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

    @Override
    public UserDataBean activateUser(String id) {
        log.debug("Activating user {}", id);
        final UserDataBean user = this.mapper.map(this.userRepository.findUserById(id), UserDataBean.class);
        user.setActive(!user.isActive());
        UserEntity userEntity = this.userRepository.save(this.mapper.map(user, UserEntity.class));
        return this.mapper.map(userEntity, UserDataBean.class);
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
