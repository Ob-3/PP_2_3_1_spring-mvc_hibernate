package com.web.service;

import com.web.model.User;
import com.web.dao.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id);
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.update(user);
    }


    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
