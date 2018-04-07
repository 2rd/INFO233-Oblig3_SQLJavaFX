package GUI;

import DAO.*;
import Entities.Invoice;
import Entities.InvoiceItem;
import Entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable{

    @FXML
    Text fDato, fId, cNumber, fCustomer, customerAdd;
    @FXML
    VBox labels, values;
    @FXML
    private Parent parent;
    int currentIndex;
    List<Invoice> invoices = InvoiceDAO.getAllInvoices();
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentIndex = 0;
        displayInvoice(currentIndex);
    }

    public void displayInvoice(int index){
        currentIndex = index;
        setInvoice(invoices.get(index));
    }
    public void setInvoice(Invoice invoice){
        Entities.Customer customer = CustomerDAO.getCustomerById(invoice.getCustomer());
        List<InvoiceItem> invoiceItems = InvoiceItemDAO.getItemsByInvoice(invoice.getInvoiceId());

        Entities.Address address = AddressDAO.getAddressById(customer.getAddress());
        fDato.setText(invoice.getDate());
        fId.setText("" + invoice.getInvoiceId());
        cNumber.setText("" + customer.getCustomerId());
        fCustomer.setText(customer.getCustomerName());
        customerAdd.setText(address.getStreetName() + " " + address.getStreetNumber() + ", " + address.getPostalCode() + " " + address.getPostalTown());

        displayItems(invoiceItems);

    }

    private void displayItems(List<InvoiceItem> invoiceItems){

        float totalPrice = 0;
        for (InvoiceItem item : invoiceItems) {
            Product product = ProductDAO.getProductById(item.getProduct());
            Text prodLabel = new Text("Produkt:");
            Text prodName = new Text(product.getProductName());
            Text priceLabel = new Text("Pris:");
            Text prodPrice = new Text(""+product.getPrice());

            totalPrice += product.getPrice();
            labels.getChildren().add(prodLabel);
            values.getChildren().add(prodName);
            labels.getChildren().add(priceLabel);
            values.getChildren().add(prodPrice);
        }
        Text totalLabel = new Text("Totalpris:");
        Text totalValue = new Text(""+ totalPrice);
        labels.getChildren().add(totalLabel);
        values.getChildren().add(totalValue);
    }

    public void backBut_click(ActionEvent actionEvent) throws IOException {
        HBox pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane, 500, 300);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    public void nextBut_click(ActionEvent actionEvent) throws IOException {
        if(currentIndex < invoices.size() - 1) {
            currentIndex += 1;
            displayInvoice(currentIndex);
        }
    }
    public void prevBut_click(ActionEvent actionEvent) throws IOException {
        if (currentIndex != 0) {
            currentIndex -= 1;
            displayInvoice(currentIndex);
        }
    }
}
