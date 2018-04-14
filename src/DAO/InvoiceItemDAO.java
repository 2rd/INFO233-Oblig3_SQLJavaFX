package DAO;

import Entities.InvoiceItem;
import GUI.Main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasse for å aksessere invoice item-tabellen i databasen.
 */
public class InvoiceItemDAO {
    private static Connection conn = Main.connextion;

    /**
     * Legger til et item i databasen.
     * @param invoiceItem item som skal legges til.
     */
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

    /**
     * Hent alle items som hører til en gitt faktura.
     * @param invoiceId id'en til fakturaen.
     * @return Items som er omfattet av fakturaen.
     */
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
    /**
     * Endre en eksisterende item i databasen.
     * @param item Item med evt nye egenskaper.
     */
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
