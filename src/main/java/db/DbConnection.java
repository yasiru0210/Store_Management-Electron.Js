package db;
import model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    private final Connection connection;

    private DbConnection() throws SQLException {
        this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
    }

    private static DbConnection instance;

    public Connection getConnection(){
        return connection;
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance==null) {
            return instance = new DbConnection();
        }
        return instance;
    }

    }

