package com.lumah_shop.lumah_shop.pojo;

import DAO.DAOOrder;
import DAO.DAOProduct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 * Orders generated by hbm2java
 */
@ManagedBean
public class Orders implements java.io.Serializable {
    private Integer id;
    private Products products;
    private Users users;
    private int quantity;
    private int total;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public List<Orders> getAllOrder() {
        DAOOrder ord = new DAOOrder();
        return ord.showAll();
    }

    public List<Products> getAllProduct() {
        DAOProduct prod = new DAOProduct();
        return prod.showAll();
    }

    public List<Orders> getUserOrder() {
        int idUser = new Users().getUserData().getId();
        List<Orders> orde = new ArrayList<Orders>();

        return idUser != 0 ? new DAOOrder().showByUser(idUser) : orde;
    }

    public void store() {
        DAOOrder ord = new DAOOrder();
        ord.store(this);
    }

    public String getById(int id) {
        DAOOrder ord = new DAOOrder();
        List<Orders> pro = ord.Show(id);

        id = pro.get(0).id;
        products = pro.get(0).products;
        users = pro.get(0).users;
        quantity = pro.get(0).quantity;
        status = pro.get(0).status;
        createdAt = pro.get(0).createdAt;
        updatedAt = pro.get(0).updatedAt;

        return "order_edit";
    }

    public void updateStatusToProcess(int id) {
        DAOOrder ord = new DAOOrder();
        Orders ordeLama = ord.showOrder(id);
        ordeLama.setStatus("process");

        ord.update(ordeLama);
    }

    public void updateStatusToDone(int id) {
        DAOOrder ord = new DAOOrder();
        Orders ordeBaru = ord.showOrder(id);
        ordeBaru.setStatus("done");

        ord.update(ordeBaru);
    }

    public boolean statusProccess(String status) {
        return status.equals("proccess");
    }

    public boolean statusWait(String status) {
        return status.equals("wait");
    }

    public boolean statusDone(String status) {
        return status.equals("done");
    }

    public void delete(int id) {
        DAOOrder order = new DAOOrder();
        order.destroy(id);
    }

    public int orderTotal() {
        return new DAOOrder().orderTotal();
    }

    public Orders() {
    }

    public Orders(Products products, Users users, int quantity, String status, Date createdAt, Date updatedAt) {
        this.products = products;
        this.users = users;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Orders(int id, Products products, Users users, int quantity, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.products = products;
        this.users = users;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Products getProducts() {
        return this.products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getTotal() {
        return products.getPrice() * quantity;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}