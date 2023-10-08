package com.helpfox.main.Model.User;

import com.helpfox.main.Model.DAO;

import java.util.List;

public interface UserDAO extends DAO {
    public long insertUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);

    public List<User> findByProp(UserSearchType searchType, Object value);
    public  List<User> findAll();
}
