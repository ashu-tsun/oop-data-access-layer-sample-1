package com.dkit.oop.sd2.DAOs;

/**
 * OOP Feb 2024
 * <p>
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 * <p>
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a MySql database.
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics. Changes to code related to
 * the database are all contained withing the DAO code base.
 * <p>
 * <p>
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by calling the DAO methods.
 * In this way, the Business Logic layer is seperated from the database specific code
 * in the DAO layer.
 */

import com.dkit.oop.sd2.DTOs.Task;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlTasksDao extends MySqlDao implements TaskDaoInterface {
    /**
     * Will access and return a List of all users in User database table
     * @return List of task objects
     * @throws DaoException
     */
    @Override
    public List<Task> findAllTasks() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> taskList = new ArrayList<>();

        try {
            //Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM tasks";
            preparedStatement = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int taskId = resultSet.getInt("taskid");
                String title = resultSet.getString("title");
                String tag = resultSet.getString("tag");
                String duedate = resultSet.getString("duedate");
                Task u = new Task(taskId, title, tag, duedate);
                taskList.add(u);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllTaskResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllTasks() " + e.getMessage());
            }
        }
        return taskList;     // may be empty
    }


}

