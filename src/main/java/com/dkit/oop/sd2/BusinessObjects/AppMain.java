package com.dkit.oop.sd2.BusinessObjects;

/** OOP Jan 2025
 * This AppMain demonstrates the use of a Data Access Layer
 * to separate Business logic from Database specific logic.
 * It uses:
 * Data Access Objects (DAOs) to implement the logic required to access a database.
 * Data Transfer Objects (DTOs), to transfer data between layers, and a
 * DAO Interface to define a 'contract' between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "CreateUsers.sql" included with this project
 * to create the required MySQL user_database and User table.
 */

import com.dkit.oop.sd2.DAOs.MySqlTasksDao;
import com.dkit.oop.sd2.DAOs.TaskDaoInterface;
import com.dkit.oop.sd2.DTOs.Task;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;

public class AppMain
{
    public static void main(String[] args)
    {
        TaskDaoInterface ITaskDao = new MySqlTasksDao();  //"IUserDao" -> "I" stands for Interface

        /// Notice that the userDao reference is an Interface type.
        /// This allows for the use of different concrete implementations.
        /// e.g. we could replace the MySqlUserDao with an OracleUserDao
        /// (accessing an Oracle Database)
        /// without changing anything in the Interface.
        /// If the Interface doesn't change, then none of the
        /// code in this file that uses the interface needs to change.
        /// This code is dependent of the Interface but not on the implementation
        /// of the interface.
        /// The 'contract' defined by the interface will not be broken.
        /// This means that this code is 'independent' of the code
        /// used to access the database. (Reduced coupling).

        /// The Business Objects require that all User DAOs implement
        /// the interface called "UserDaoInterface", as the code uses
        /// only references of the interface type to access the DAO methods.

        try
        {
            System.out.println("\nCall findAllTasks()");
            List<Task> tasks = ITaskDao.findAllTasks();     // call a method in the DAO

            if( tasks.isEmpty() )
                System.out.println("There are no Tasks");
            else {
                for (Task t: tasks)
                    System.out.println("Task: " + t.toString());
            }
        }
        catch( DaoException e )
        {
            /// This code is executed when the DAO layer throws an exception.
            /// We might place some logic here to deal with the issue, but in this case,
            /// we simply print out the exception error message to the console.
            e.printStackTrace();
        }
    }
}
