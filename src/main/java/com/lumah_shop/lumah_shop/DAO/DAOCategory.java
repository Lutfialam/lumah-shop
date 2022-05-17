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
import pojo.Categories;
import pojo.LumahShopUtil;

/**
 *
 * @author lutfi
 */
public class DAOCategory extends DAO {
    public List<Categories> showAll() {
        List<Categories> category = new ArrayList();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Categories");
            category = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    public List<Categories> Show(int id) {
        List<Categories> category = new ArrayList<Categories>();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Categories where id=:id");
            query.setInteger("id", id);
            // category = (Categories) query.uniqueResult();
            category = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    public Categories ShowCategory(int id) {
        Categories category = new Categories();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Categories where id=:id");
            query.setInteger("id", id);
            category = (Categories) query.uniqueResult();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    public List<Categories> ShowByName(String name) {
        List<Categories> category = new ArrayList<Categories>();

        Transaction trans = null;
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Categories where name=:name");
            query.setString("name", name);

            category = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    public void store(Categories category) {
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();
            session.save(category);
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Categories category) {
        Transaction trans = null;
        Session session = getSession();

        try {
            trans = session.beginTransaction();
            session.update(category);
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

            Categories prod = (Categories) session.load(Categories.class, new Integer(id));
            session.delete(prod);

            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int categoryTotal() {
        int total = 0;
        Transaction trans = null;

        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("select count(distinct category.id) from Categories category");
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
