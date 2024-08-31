package db;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    private List<Customer> customerList;

    private DbConnection(){
        this.customerList=new ArrayList<>();
    }

    private static DbConnection instance;

    public List<Customer> getConnection(){
        return customerList;
    }

    public static DbConnection getInstance(){
        if (instance==null){
             return instance = new DbConnection();
        }
        return instance;
    }

    }

