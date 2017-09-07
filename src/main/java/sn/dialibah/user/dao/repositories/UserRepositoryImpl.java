package sn.dialibah.user.dao.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import sn.dialibah.user.dao.entities.UserEntity;
import sn.dialibah.user.model.UserDataBean;

/**
 * Created by nureynisow on 10/08/2017.
 * for DPTracker
 */

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserDataBean patchUser(String id, UserDataBean user) {
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(user.getId()));

        Update update = new Update();
        update.set("active", user.isActive());
        mongoOperations.updateFirst(q, update, UserEntity.class);
        return user;
    }
}
