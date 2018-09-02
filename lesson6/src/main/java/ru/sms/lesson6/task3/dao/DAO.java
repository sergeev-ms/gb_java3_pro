package ru.sms.lesson6.task3.dao;

import ru.sms.lesson6.task3.model.Student;

import java.sql.SQLException;

public interface DAO {

    void createTable(String tableName) throws SQLException;

    void deleteTable(String tableName) throws SQLException;

    void insertStudent(Student student) throws SQLException;

    Student getStudentByID(int ID) throws SQLException;

    void updateStudent(Student student) throws SQLException;
}
