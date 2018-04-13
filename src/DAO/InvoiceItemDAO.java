package DAO;

import Entities.InvoiceItem;
import GUI.Main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class InvoiceItemDAO {
    private static Connection conn = Main.connextion;

    public static void addInvoiceItem(InvoiceItem invoiceItem) {

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newInvoiceItem = conn.prepareStatement("INSERT INTO invoice_items (invoice, product) VALUES (?,?)");
            newInvoiceItem.setInt(1, invoiceItem.getInvoice());
            newInvoiceItem.setInt(2, invoiceItem.getProduct());
            newInvoiceItem.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<InvoiceItem> getItemsByInvoice(int invoiceId){

        List<InvoiceItem> invoiceItems = new LinkedList<>();

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet itemResult = statement.executeQuery("SELECT * FROM invoice_items WHERE invoice = " + invoiceId);

            while (itemResult.next()) {
                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setInvoice(itemResult.getInt("invoice"));
                invoiceItem.setProduct(itemResult.getInt("product"));
                invoiceItems.add(invoiceItem);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceItems;
    }

    public static void removeInvoiceItem(int invoiceId){

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("DELETE FROM invoice_items WHERE invoice = " + invoiceId);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void editInvoiceItem(InvoiceItem item){
        try {
            PreparedStatement itemResult = conn.prepareStatement("UPDATE invoice_items SET " +
                    "product = ?," +
                    "WHERE invoice_id = " + item.getInvoice());
            itemResult.setInt(1, item.getProduct());

            itemResult.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
