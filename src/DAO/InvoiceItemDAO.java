package DAO;

import Entities.InvoiceItem;

import java.sql.*;

public class InvoiceItemDAO {
    private static ConnectionDAO connection = new ConnectionDAO();

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newInvoiceItem = conn.prepareStatement("INSERT INTO invoice_items (invoice, product) VALUES (?,?)");
            newInvoiceItem.setInt(1, invoiceItem.getInvoice());
            newInvoiceItem.setInt(2, invoiceItem.getProduct());
            newInvoiceItem.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.closeConnection();
        }
    }

    public static InvoiceItem getItemByInvoice(int invoiceId){
        Connection conn = connection.getConnection();
        InvoiceItem invoiceItem = new InvoiceItem();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet itemResult = statement.executeQuery("SELECT * FROM invoice_items WHERE invoice = " + invoiceId);

            invoiceItem.setInvoice(itemResult.getInt("invoice"));
            invoiceItem.setProduct(itemResult.getInt("product"));

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
        return invoiceItem;
    }
}
