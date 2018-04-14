package Entities;

/**
 * Klasse for entiteten invoice item, med alle dens attributter, setters og getters.
 */
public class InvoiceItem {
    private int invoice;
    private int product;

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }


}
