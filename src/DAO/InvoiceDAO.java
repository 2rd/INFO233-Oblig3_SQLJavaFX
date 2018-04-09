package DAO;

import Entities.Invoice;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class InvoiceDAO {
    private static ConnectionDAO connection = new ConnectionDAO();

    public static void addInvoice(Entities.Invoice invoice) {
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newInvoice = conn.prepareStatement("INSERT INTO invoice (invoice_id, customer, dato) VALUES (?,?,?)");
            newInvoice.setInt(1, invoice.getInvoiceId());
            newInvoice.setInt(2, invoice.getCustomer());
            newInvoice.setString(3, invoice.getDate());
            newInvoice.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.closeConnection();
        }
    }

    public static Invoice getInvoiceById(int id) {
        Connection conn = connection.getConnection();
        Invoice invoice = new Invoice();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet invoiceResult = statement.executeQuery("SELECT * FROM invoice WHERE invoice_id = " + id);

            invoice.setInvoiceID(invoiceResult.getInt("invoice_id"));
            invoice.setCustomer(invoiceResult.getInt("customer"));
            invoice.setDate(invoiceResult.getString("dato"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return invoice;
    }

    public static List<Invoice> getAllInvoices() {
        Invoice currInvoice = new Invoice();
        java.util.List<Invoice> invoices = new LinkedList<Invoice>();
        Connection conn = connection.getConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM invoice");
            while (rs.next()) {
                currInvoice = new Invoice();

                currInvoice.setInvoiceID(rs.getInt("invoice_id"));
                currInvoice.setCustomer(rs.getInt("customer"));
                currInvoice.setDate(rs.getString("dato"));

                invoices.add(currInvoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return invoices;
    }

    public static void removeInvoice(int id) {
        Connection conn = connection.getConnection();
        try    {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("DELETE FROM invoice WHERE invoice_id = " + id);

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            connection.closeConnection();
        }
    }
}
