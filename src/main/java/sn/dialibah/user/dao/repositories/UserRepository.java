package sn.dialibah.user.dao.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import sn.dialibah.user.dao.entities.UserEntity;

/**
 * Created by nureynisow on 25/03/2017.
 * for DekWay
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
    UserEntity findUserByUsername(String username);

    UserEntity findUserByEmail(String email);
}
