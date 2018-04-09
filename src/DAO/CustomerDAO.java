package DAO;

import Entities.Customer;

import java.sql.*;

public class CustomerDAO {
    private static ConnectionDAO connection = new ConnectionDAO();

    public static void addCustomer(Customer customer) {
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newCustomer = conn.prepareStatement("INSERT INTO customer (customer_id, customer_name, address, phone_number, billing_account) VALUES (?,?,?,?,?)");
            newCustomer.setInt(1, customer.getCustomerId());
            newCustomer.setString(2, customer.getCustomerName());
            newCustomer.setInt(3, customer.getAddress());
            newCustomer.setString(4, customer.getPhoneNumber());
            newCustomer.setString(5, customer.getBillingAccount());
            newCustomer.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.closeConnection();
        }
    }

    public static Customer getCustomerById(int id){
        Connection conn = connection.getConnection();
        Customer customer = new Customer();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet customerResult = statement.executeQuery("SELECT * FROM customer WHERE customer_id = " + id);

            customer.setCustomerId(customerResult.getInt("customer_id"));
            customer.setCustomerName(customerResult.getString("customer_name"));
            customer.setAddress(customerResult.getInt("address"));
            customer.setPhoneNumber(customerResult.getString("phone_number"));
            customer.setBillingAccount(customerResult.getString("billing_account"));

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
        return customer;
    }

    public static void removeCustomer(int id){
        Connection conn = connection.getConnection();

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            statement.executeQuery("DELETE FROM customer WHERE customer_id = " + id);


        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
    }

    public static void editCustomer(Customer customer){
        Connection conn = connection.getConnection();
        int id = customer.getCustomerId();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet customerResult = statement.executeQuery("UPDATE customer SET (customer_name, address, phone_number, billing_account) VALUES (?,?,?,?) WHERE customer_id = " + id);
            customer.setCustomerName(customerResult.getString("customer_name"));
            customer.setAddress(customerResult.getInt("address"));
            customer.setPhoneNumber(customerResult.getString("phone_number"));
            customer.setBillingAccount(customerResult.getString("billing_account"));

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
    }
}
