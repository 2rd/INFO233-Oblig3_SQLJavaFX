package DAO;

import Entities.Customer;
import GUI.Main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO {
    private static CustomerDAO customerDAO = null;
    private static Connection conn = Main.connextion;

    public static CustomerDAO getInstance(){
       if(customerDAO == null) {
           customerDAO = new CustomerDAO();
       }
       return customerDAO;
    }
    public static void addCustomer(Customer customer) {

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
        }
    }

    public static Customer getCustomerById(int id){

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

        return customer;
    }

    public static void removeCustomer(int id){

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            statement.executeQuery("DELETE FROM customer WHERE customer_id = " + id);


        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new LinkedList<Customer>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                Customer currCustomer = new Customer();

                currCustomer.setCustomerId(rs.getInt("customer_id"));
                currCustomer.setCustomerName(rs.getString("customer_name"));
                currCustomer.setAddress(rs.getInt("address"));
                currCustomer.setPhoneNumber(rs.getString("phone_number"));
                currCustomer.setBillingAccount(rs.getString("billing_account"));

                customers.add(currCustomer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public static void editCustomer(Customer customer){

        try {

            PreparedStatement customerResult = conn.prepareStatement("UPDATE customer SET " +
                    "customer_name = ?," +
                    " address = ?," +
                    " phone_number = ?," +
                    " billing_account = ?" +
                    "WHERE customer_id = " + customer.getCustomerId());
            customerResult.setString(1, customer.getCustomerName());
            customerResult.setInt(2, customer.getAddress());
            customerResult.setString(3, customer.getPhoneNumber());
            customerResult.setString(4, customer.getBillingAccount());

            customerResult.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
