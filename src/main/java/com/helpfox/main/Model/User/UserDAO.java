package com.helpfox.main.Model.User;

import com.helpfox.main.Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO {
    public long insertUser(User user) throws Exception;
    public boolean updateUser(User user) throws Exception;
    public boolean deleteUser(User user) throws Exception;

    public List<User> findByProp(UserSearchType searchType, Object value) throws Exception;
    public  List<User> findAll() throws Exception;
}
