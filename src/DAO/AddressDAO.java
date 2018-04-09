package DAO;

import Entities.Address;

import java.sql.*;

public class AddressDAO {

    private static ConnectionDAO connection = new ConnectionDAO();

    public static void addAddress(Address address) {
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newAddress = conn.prepareStatement("INSERT INTO address (address_id, street_number, street_name, postal_code, postal_town) VALUES (?,?,?,?,?)");
            newAddress.setInt(1, address.getAddressId());
            newAddress.setString(2, address.getStreetNumber());
            newAddress.setString(3, address.getStreetName());
            newAddress.setString(4, address.getPostalCode());
            newAddress.setString(5, address.getPostalTown());
            newAddress.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.closeConnection();
        }
    }

    public static Address getAddressById(int id){
        Connection conn = connection.getConnection();
        Address address = new Address();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet addressResult = statement.executeQuery("SELECT * FROM address WHERE address_id = " + id);

            address.setAddressId(addressResult.getInt("address_id"));
            address.setStreetNumber(addressResult.getString("street_number"));
            address.setStreetName(addressResult.getString("street_name"));
            address.setPostalCode(addressResult.getString("postal_code"));
            address.setPostalTown(addressResult.getString("postal_town"));

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
        return address;
    }

    public static void removeAddress(int id){
        Connection conn = connection.getConnection();

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            statement.executeQuery("DELETE FROM address WHERE address_id = " + id);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
    }
}
