package DAO;

import Entities.Address;

import java.sql.*;

public class AddressDAO {

    private static ConnectionDAO connection = new ConnectionDAO();

    public void addAddress(Address address) {
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newInvoice = conn.prepareStatement("INSERT INTO address (address_id, street_number, street_name, postal_code, postal_town) VALUES (?,?,?,?,?)");
            newInvoice.setInt(1, address.getAddressId());
            newInvoice.setString(2, address.getStreetNumber());
            newInvoice.setString(3, address.getStreetName());
            newInvoice.setString(4, address.getPostalCode());
            newInvoice.setString(5, address.getPostalTown());
            newInvoice.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.closeConnection();
        }
    }

    public static Address getAddressById(int id){
        Connection conn = connection.getConnection();
        Address invoice = new Address();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet addressResult = statement.executeQuery("SELECT * FROM invoice WHERE invoice_id = " + id);

            invoice.setAddressId(addressResult.getInt("address_id"));
            invoice.setStreetNumber(addressResult.getString("street_number"));
            invoice.setStreetName(addressResult.getString("street_name"));
            invoice.setStreetName(addressResult.getString("postal_code"));
            invoice.setStreetName(addressResult.getString("postal_town"));

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
        return invoice;
    }
}
