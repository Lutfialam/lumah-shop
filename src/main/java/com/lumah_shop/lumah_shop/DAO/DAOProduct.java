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
import pojo.Products;
import pojo.Users;

/**
 *
 * @author lutfi
 */
public class DAOProduct extends DAO {
    public List<Products> showAll () {
        List<Products> product = new ArrayList();
        Transaction trans = null;
        
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Users user = new Users().getUserData();
            
            Query query = session.createQuery("from Products where stock > 0");
            if (user.getLevel().equals("admin")) {
                query = session.createQuery("from Products");                
            }
            
            product = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
    
    public List<Products> showFourP () {
        List<Products> product = new ArrayList();
        Transaction trans = null;
        
        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Products where stock > 0 order by id desc");
            query.setMaxResults(4);
            product = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
    
    public List<Products> Show (int id) {
        Transaction trans = null;
        List<Products> product = new ArrayList<Products>();

        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Products where id=:id");
            query.setInteger("id", id);
            // product = (Products) query.uniqueResult();
            product = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
    
    public List<Products> ShowByName(String name) {
        Transaction trans = null;
        List<Products> product = new ArrayList<Products>();

        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Products where name=:name");
            query.setString("name", name);
            // product = (Products) query.uniqueResult();
            product = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
    
    public Products ShowP(int id) {
        Transaction trans = null;
        Products product = new Products();

        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Products where id=:id");
            query.setInteger("id", id);
            product = (Products) query.uniqueResult();
//            product = query.list();
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public void store (Products product) {
        Session session = getSession();
        Transaction trans = null;
        try {
            trans = session.beginTransaction();
            session.save(product);
            
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void update(Products product) {
        Session session = getSession();
        Transaction trans = null;
        
        try {
            trans = session.beginTransaction();
            session.update(product);
            
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
            
            Products prod = (Products) session.load(Products.class, new Integer(id));
            session.delete(prod);
            
            trans.commit();
            close(session);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int productTotal() {
        int total = 0;
        Transaction trans = null;

        Session session = getSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("select count(distinct products.id) from Products products");
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
