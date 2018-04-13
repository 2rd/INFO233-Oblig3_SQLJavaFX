package DAO;

import Entities.Address;
import GUI.Main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AddressDAO {

    private static Connection conn = Main.connextion;

    private static AddressDAO addressDAO = null;

    public static AddressDAO getInstance() {
        if (addressDAO == null) {
            addressDAO = new AddressDAO();
        }
        return addressDAO;
    }

    public static void addAddress(Address address) {

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

        }
    }

    public static Address getAddressById(int id) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return address;
    }


    public static void removeAddress ( int id){

            try {
                Statement statement = conn.createStatement();
                statement.setQueryTimeout(30);

                statement.executeQuery("DELETE FROM address WHERE address_id = " + id);

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static List<Address> getAllAddresses() {
        List<Address> addresses = new LinkedList<Address>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM address");
            while (rs.next()) {
                Address currAddress = new Address();

                currAddress.setAddressId(rs.getInt("address_id"));
                currAddress.setStreetName(rs.getString("street_name"));
                currAddress.setStreetNumber(rs.getString("street_number"));
                currAddress.setPostalTown(rs.getString("postal_town"));
                currAddress.setPostalCode(rs.getString("postal_code"));

                addresses.add(currAddress);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    public static void editAddress(Address address){

        try {

            PreparedStatement addressResult = conn.prepareStatement("UPDATE address SET " +
                    "street_name = ?," +
                    " street_number = ?," +
                    " postal_code = ?," +
                    " postal_town = ?" +
                    "WHERE address_id = " + address.getAddressId());
            addressResult.setString(1, address.getStreetName());
            addressResult.setString(2, address.getStreetNumber());
            addressResult.setString(3, address.getPostalCode());
            addressResult.setString(4, address.getPostalTown());

            addressResult.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    }
