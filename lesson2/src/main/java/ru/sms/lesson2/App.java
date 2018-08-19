package ru.sms.lesson2;

import ru.sms.lesson2.dao.DAO;
import ru.sms.lesson2.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static final String JDBC = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:lesson2/sample.db";
    public static final String CHANGE_PRICE_COMMAND = "/сменитьцену";
    public static final String GET_PRICE_COMMAND = "/цена";
    public static final String GET_PRODUCTS_BY_PRICES = "/товарыпоцене";
    public static final String EXIT = "/завершить";
    public static final String WAITING_COMMAND_MESSAGE = "Ожидаю комманды...";
    public static final String PRODUCT_UNKNOWN_MESSAGE = "Товар не найден";
    public static final String UPDATED_MESSAGE = "Обновлено.";
    public static final String NOT_UPDATED_MESSAGE = "Не обновлено.";
    public static final String EXIT_MESSAGE = "Завершаю работу...";
    public static final String COMMAND_UNKNOWN = "Комманда не опознана";
    public static final String PRICE_MESSAGE = "Цена: ";

    public static void main(String[] args ) throws SQLException {
        try {
            Class.forName(JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(URL);
        DAO dao = new JdbcDaoImpl(connection);
        try {
            dao.createProductTable();

            List<Product> products = new ArrayList<>();
            for (int i = 1; i <= 1000; i++)
                products.add(new Product(i, "товар" + i, i * 10));

            dao.bulkInsert(products);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println(WAITING_COMMAND_MESSAGE);
                if (scanner.hasNext()) {
                    final String s = scanner.nextLine();
                    if (s.matches(String.format("(\\%s)(\\s+)(.+)", GET_PRICE_COMMAND))) {
                        final String[] split = (s.split("\\s+"));
                        final String productName = split[1];
                        final Product productByName = dao.getProductByName(productName);
                        if (productByName != null)
                            System.out.println(PRICE_MESSAGE + productByName.getCost());
                        else System.out.println(PRODUCT_UNKNOWN_MESSAGE);
                    } else if (s.matches(String.format("(\\%s)(\\s+)(.\\S+)(\\s+)(\\d+)", CHANGE_PRICE_COMMAND))) {
                        final String[] split = (s.split("\\s+"));
                        final String productName = split[1];
                        final int newPrice = Integer.parseInt(split[2]);
                        final int updateCount = dao.updateProductPrice(productName, newPrice);
                        if (updateCount > 0)
                            System.out.println(UPDATED_MESSAGE);
                        else System.out.println(NOT_UPDATED_MESSAGE);
                    } else if (s.matches(String.format("(\\%s)(\\s+)(\\d+)(\\s+)(\\d+)", GET_PRODUCTS_BY_PRICES))) {
                        final String[] split = (s.split("\\s+"));
                        final int fromPrice = Integer.parseInt(split[1]);
                        final int toPrice = Integer.parseInt(split[2]);
                        final List<Product> productList = dao.getProductListByPrice(fromPrice, toPrice);
                        productList.forEach(System.out::println);
                    } else if (s.matches(String.format("\\%s\\s*", EXIT))) {
                        System.out.println(EXIT_MESSAGE);
                        break;
                    } else System.out.println(COMMAND_UNKNOWN);
                }
            }
        } finally {
            connection.close();
        }

    }
}
