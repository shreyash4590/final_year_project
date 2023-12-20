package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {


        public Connection connection;
        public Statement statement;

        public conn() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement", "root", "shreyash");
                statement = connection.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }