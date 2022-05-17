/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lumah_shop.lumah_shop.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.LumahShopUtil;
import pojo.Users;

/**
 *
 * @author lutfi
 */
public class Auth extends DAO {
    public Users Login(String email, String password) {
        Users user = new Users();

        Session session = getSession();
        try {
            Query query = session.createQuery("from Users where email=:email and password=:password");
            query.setString("email", email);
            query.setString("password", password);

            user = (Users) query.uniqueResult();
            close(session);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void Register(Users user) {
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
}
