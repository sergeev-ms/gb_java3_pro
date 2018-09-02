package ru.sms.lesson6.task3;

import ru.sms.lesson6.task3.dao.DAO;
import ru.sms.lesson6.task3.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDaoImpl implements DAO {
    private Connection connection;

    public JdbcDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(String tableName) throws SQLException {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS  %s\n" +
                "(\n" +
                "  ID    integer\n" +
                "    primary key\n" +
                "  autoincrement,\n" +
                "  FIO   text not null,\n" +
                "  SCORE text\n" +
                ");\n";
        sqlStatement = String.format(sqlStatement, tableName);
        final PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteTable(String tableName) throws SQLException {
        String sqlStatement = "DROP TABLE IF EXISTS %s";
        sqlStatement = String.format(sqlStatement, tableName);
        final PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void insertStudent(Student student) throws SQLException {
        String sqlStatement = "INSERT INTO STUDENT (FIO, SCORE) VALUES (?, ?)";
        final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setString(1, student.getFIO());
        statement.setInt(2, student.getScore());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Student getStudentByID(int ID) throws SQLException {
        String sqlStatement = "SELECT ID, FIO, SCORE FROM STUDENT WHERE ID = ?";
        final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setInt(1, ID);
        statement.execute();
        final ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        final Student student = new Student(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getInt(3));
        statement.close();
        return student;
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        String sqlStatement = "UPDATE STUDENT SET FIO = ?, SCORE = ? WHERE ID = ?";
        final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setString(1, student.getFIO());
        statement.setInt(2, student.getScore());
        statement.setInt(3, student.getId());
        statement.executeUpdate();
        statement.close();
    }
}
