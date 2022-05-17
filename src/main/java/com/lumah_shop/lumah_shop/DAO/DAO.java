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

/**
 *
 * @author lutfi
 */
public class DAO {
    public void close(Session session) {
        session.close();
    }

    public Session getSession() {
        return LumahShopUtil.getSessionFactory().openSession();
    }
}
