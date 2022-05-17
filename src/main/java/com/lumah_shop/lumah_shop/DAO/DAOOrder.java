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
import org.hibernate.annotations.OrderBy;
import pojo.Categories;
import pojo.LumahShopUtil;
import pojo.Orders;

/**
 *
 * @author lutfi
 */
public class DAOOrder extends DAO {
    public List<Orders> showAll() {
        List<Orders> order = new ArrayList();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Orders ORDER BY id DESC");
            order = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public List<Orders> showByUser(int user_id) {
        List<Orders> order = new ArrayList();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Orders where user_id=:user_id ORDER BY id DESC");
            query.setInteger("user_id", user_id);

            order = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public Orders showOrder(int id) {
        Orders order = new Orders();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Orders where id=:id");
            query.setInteger("id", id);
            order = (Orders) query.uniqueResult();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public List<Orders> Show(int id) {
        List<Orders> order = new ArrayList<Orders>();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Orders where id=:id");
            query.setInteger("id", id);
            order = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public void store(Orders order) {
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();
            session.save(order);
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Orders order) {
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();
            session.update(order);
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void destroy(int id) {
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();

            Orders prod = (Orders) session.load(Orders.class, new Integer(id));
            session.delete(prod);

            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int orderTotal() {
        int total = 0;
        Transaction trans = null;

        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("select count(distinct orders.id) from Orders orders");
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
