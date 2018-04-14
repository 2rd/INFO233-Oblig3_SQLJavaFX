package Entities;

/**
 * Klasse for entiteten invoice, med alle dens attributter, setters og getters.
 */
public class Invoice {

    private int invoiceID;
    private int customer;
    private String date;

    public int getInvoiceId() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
