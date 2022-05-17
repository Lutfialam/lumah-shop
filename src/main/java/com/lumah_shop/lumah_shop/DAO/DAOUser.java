/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lumah_shop.lumah_shop.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.LumahShopUtil;
import pojo.Users;

/**
 *
 * @author lutfi
 */
public class DAOUser extends DAO {
    public List<Users> showAll() {
        List<Users> user = new ArrayList();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Users");
            user = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public Users ShowUser(int id) {
        Users user = new Users();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Users where id=:id");
            query.setInteger("id", id);

            user = (Users) query.uniqueResult();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<Users> Show(int id) {
        List<Users> user = new ArrayList<Users>();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Users where id=:id");
            query.setInteger("id", id);
            // user = (Users) query.uniqueResult();
            user = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void store(Users user) {
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();
            session.save(user);
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Users user) {
        System.out.println(user.getId() + user.getName());
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();
            session.update(user);
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRole(int id, String role) {
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();
            Users user = (Users) session.get(Users.class, id);
            user.setLevel(role);
            session.update(user);

            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void destroy(int id) {
        Session session = getSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();

            Users prod = (Users) session.load(Users.class, new Integer(id));
            session.delete(prod);

            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int userTotal() {
        int total = 0;
        Transaction trans = null;

        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("select count(distinct user.id) from Users user");
            Long ltotal = (Long) query.uniqueResult();
            total = ltotal.intValue();

            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return total;
    }
}
