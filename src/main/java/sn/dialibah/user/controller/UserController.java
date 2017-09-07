package sn.dialibah.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.dialibah.user.model.LoginDataBean;
import sn.dialibah.user.model.RegistrationDataBean;
import sn.dialibah.user.model.UserDataBean;
import sn.dialibah.user.services.IUserAccountService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by nureynisow on 25/03/2017.
 * for DekWay
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final String LOG_HEADER = "[SECURITY][USER CONTROLLER]";

    @Autowired
    private IUserAccountService userAccountService;

    @RequestMapping(method = RequestMethod.POST, value = "signup")
    public ResponseEntity<UserDataBean> register(
            @Valid @RequestBody final RegistrationDataBean registrationDataBean)
            throws NoSuchAlgorithmException {
        LOGGER.debug(LOG_HEADER + " SIGNING UP user ",registrationDataBean);
        return new ResponseEntity<>(userAccountService.register(registrationDataBean), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public ResponseEntity<UserDataBean> updateUser(@PathVariable String id,
                                     @RequestBody final UserDataBean user){
        LOGGER.debug(LOG_HEADER + " Updating user ",id);
        return new ResponseEntity<>(userAccountService.patch(id, user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<UserDataBean>> getAllUsers(){
        LOGGER.debug(LOG_HEADER + "Get all users in db");
        return new ResponseEntity<>(userAccountService.getUsers(), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public ResponseEntity<UserDataBean> login(@Valid @RequestBody final LoginDataBean loginDataBean,
                                              final HttpServletResponse response) {
        LOGGER.debug(LOG_HEADER + " SIGNING IN user ",loginDataBean);
        final UserDataBean userDataBean = userAccountService
                .login(loginDataBean.getEmail(), loginDataBean.getPassword());
        return new ResponseEntity<>(userDataBean, HttpStatus.OK);
    }
}
