package com.web.dao;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.web.model.User;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        System.out.println("DAO - saveUser: " + user.getName() + ", " + user.getEmail());
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User existingUser = entityManager.find(User.class, user.getId());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            entityManager.flush(); // Принудительно отправляем изменения в БД
        } else {
            throw new IllegalArgumentException("User with ID " + user.getId() + " does not exist");
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
