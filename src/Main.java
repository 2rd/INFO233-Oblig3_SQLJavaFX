import DAO.InvoiceDAO;
import GUI.InvoiceInterface;

public class Main {
    public static void main(String[] args) {

        InvoiceDAO invoices = new InvoiceDAO();
        System.out.println(invoices.getInvoiceById(1).getDate());
        InvoiceInterface invoiceUI = new InvoiceInterface();
    }

}
