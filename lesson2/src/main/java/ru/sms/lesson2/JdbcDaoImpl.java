package ru.sms.lesson2;

import com.sun.istack.internal.Nullable;
import ru.sms.lesson2.dao.DAO;
import ru.sms.lesson2.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDaoImpl implements DAO {
    private Connection connection;

    public JdbcDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product getProductByName(String name) throws SQLException {
        final String sqlStatement = "SELECT PRODUCT_ID, TITLE, COST FROM Products WHERE TITLE = ?";
        final PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setString(1, name);
        final ResultSet resultSet = preparedStatement.executeQuery();
        final boolean first = resultSet.next();
        if (first)
            return new Product(
                    resultSet.getInt("PRODUCT_ID"),
                    resultSet.getString("TITLE"),
                    resultSet.getInt("COST"));
        else return null;
    }

    @Override
    public void bulkInsert(List<Product> products) throws SQLException {
            final String sqlStatement = "INSERT INTO Products (PRODUCT_ID, TITLE, COST) VALUES (?, ?, ?)";
            final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        for (Product product : products) {
            statement.setInt(1, product.getProdId());
            statement.setString(2, product.getTitle());
            statement.setInt(3, product.getCost());
            statement.addBatch();
        }
        statement.executeBatch();
        statement.close();
    }

    @Override
    public void createProductTable() throws SQLException {
        final Statement statement = connection.createStatement();
        final String sqlStatement = "CREATE TABLE IF NOT EXISTS Products(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "PRODUCT_ID INTEGER NOT NULL," +
                "TITLE TEXT NOT NULL," +
                "COST INTEGER NOT NULL" +
                ");";
        statement.execute(sqlStatement);
        statement.close();
    }

    @Override
    public int updateProductPrice(String title, int newCost) throws SQLException {
        final String sqlStatement = "UPDATE Products SET COST = ? WHERE TITLE = ?";
        final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setInt(1, newCost);
        statement.setString(2, title);
        final int updateCount = statement.executeUpdate();
        statement.close();
        return updateCount;

    }

    @Override
    public List<Product> getProductListByPrice(int fromPrice, int toPrice) throws SQLException {
        final String sqlStatement = "SELECT PRODUCT_ID, TITLE, COST FROM Products WHERE COST BETWEEN ? AND ?";
        final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setInt(1, fromPrice);
        statement.setInt(2, toPrice);
        final ResultSet resultSet = statement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()){
            final Product product = new Product(
                    resultSet.getInt("PRODUCT_ID"),
                    resultSet.getString("TITLE"),
                    resultSet.getInt("COST"));
            products.add(product);
        }
        return products;
    }
}
