package sn.dialibah.user.dao.repositories;

import sn.dialibah.user.model.UserDataBean;

/**
 * Created by nureynisow on 10/08/2017.
 * for DPTracker
 */
public interface UserRepositoryCustom {

    UserDataBean patchUser(String id, UserDataBean user);
}
