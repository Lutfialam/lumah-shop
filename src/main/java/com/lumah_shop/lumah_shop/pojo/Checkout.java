/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lumah_shop.lumah_shop.pojo;

import DAO.DAOOrder;
import DAO.DAOProduct;

import java.sql.Timestamp;
import java.util.Date;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author lutfi
 */
@ManagedBean
public class Checkout {
    private int quantity;
    private int product_id;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    Products product;
    Timestamp ts;

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public String Checkout(int id) {
        Date date = new Date();
        long time = date.getTime();
        ts = new Timestamp(time);

        DAOProduct pro = new DAOProduct();
        product = pro.ShowP(id);
        product_id = id;

        if (quantity > product.getStock()) {
            new Products().getById(id);
            return "customer_product_detail";
        } else {
            return "customer_checkout";
        }
    }

    public String ProccessPayment() {
        DAOOrder order = new DAOOrder();
        Products prod = new DAOProduct().ShowP(product_id);

        if (prod.getStock() < quantity) {
            return "customer_checkout";
        } else {
            Users user = new Users().getUserData();
            Orders ord = new Orders(prod, user, quantity, "wait", ts, ts);
            order.store(ord);

            prod.setStock(prod.getStock() - quantity);
            new DAOProduct().update(prod);

            return "customer_order";
        }
    }

}
