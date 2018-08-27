package ru.sms.lesson2.dao;

import ru.sms.lesson2.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface DAO {

    Product getProductByName(String name) throws SQLException;

    void bulkInsert(List<Product> products) throws SQLException;

    void createProductTable() throws SQLException;

    int updateProductPrice(String title, int newCost) throws SQLException;

    List<Product> getProductListByPrice(int fromPrice, int toPrice) throws SQLException;

}
