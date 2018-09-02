/*
3. Создать небольшую БД (таблица: студенты; поля: id, фамилия, балл).
Написать тесты для проверки того, что при работе с базой корректно добавляются, обновляются и читаются записи.
Следует учесть, что в базе есть заранее добавленные записи, и после проведения тестов эти записи не должны быть
удалены/изменены/добавлены.
 */
package ru.sms.lesson6.task3;

import org.sqlite.JDBC;
import ru.sms.lesson6.task3.dao.DAO;
import ru.sms.lesson6.task3.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static final String URL = "jdbc:sqlite:lesson6/students.db";
    public static final String JDBC = "org.sqlite.JDBC";
    public static final String TABLE_NAME = "STUDENT";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        Connection connection = null;
        try (Connection connection = DriverManager.getConnection(URL)) {
            DAO dao = new JdbcDaoImpl(connection);
            dao.createTable(TABLE_NAME);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
