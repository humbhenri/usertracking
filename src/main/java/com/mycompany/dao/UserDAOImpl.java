package com.mycompany.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.model.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User user) {
        em.merge(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = em.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public void delete(User user) {
        Query q = em.createQuery("delete from User where id = :id");
        q.setParameter("id", user.getId());
        q.executeUpdate();
    }

}
