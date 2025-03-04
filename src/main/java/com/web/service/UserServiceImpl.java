package com.web.service;

import com.web.model.User;
import com.web.dao.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }


    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
