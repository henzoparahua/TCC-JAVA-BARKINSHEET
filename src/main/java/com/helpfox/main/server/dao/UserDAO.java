package com.helpfox.main.server.dao;

import com.helpfox.main.server.entity.User;
import com.helpfox.main.server.type.UserSearchType;

import java.util.List;

public interface UserDAO extends DAO {
    public long insertUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);

    public List<User> findByProp(UserSearchType searchType, Object value);
    public  List<User> findAll();
}
