package DAO;

import Entities.Invoice;
import GUI.Main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class InvoiceDAO {
    private static Connection conn = Main.connextion;

    public static void addInvoice(Entities.Invoice invoice) {

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
        }
    }

    public static Invoice getInvoiceById(int id) {

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
        }
        return invoice;
    }

    public static List<Invoice> getAllInvoices() {
        Invoice currInvoice = new Invoice();
        java.util.List<Invoice> invoices = new LinkedList<Invoice>();


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
        }
        return invoices;
    }

    public static void removeInvoice(int id) {

        try    {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("DELETE FROM invoice WHERE invoice_id = " + id);

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void editInvoice(Invoice invoice){
        try {

            PreparedStatement invoiceResult = conn.prepareStatement("UPDATE invoice SET " +
                    "customer = ?," +
                    " date = ?," +
                    "WHERE invoice_id = " + invoice.getInvoiceId());
            invoiceResult.setInt(1, invoice.getCustomer());
            invoiceResult.setString(2, invoice.getDate());

            invoiceResult.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
