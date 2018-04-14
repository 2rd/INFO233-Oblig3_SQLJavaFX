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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for FXML-filen Invoice.
 * Gjør slik at man kan bla gjennom alle fakturaene i databasen
 * gjennom et grafisk grensesnitt.
 * @author Tord Kvifte, 13.04.2018
 */
public class InvoiceController implements Initializable{

    @FXML
    Text fDato, fId, cNumber, fCustomer, customerName, street, town, account, totalPrice;

    @FXML
    VBox products, prices;

    @FXML
    private Parent parent;
    private int currentIndex;
    private List<Invoice> invoices = InvoiceDAO.getAllInvoices();

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

    /**
     * Kaller den spesifikke fakturaen som skal vises.
     * @param index id'en til fakturaen som skal vises.
     */
    public void displayInvoice(int index){
        currentIndex = index;
        setInvoice(invoices.get(index));
    }

    /**
     * Legger til all fakturadata i vinduet.
     * @param invoice fakturaen som skal vises.
     */
    public void setInvoice(Invoice invoice){
        Entities.Customer customer = CustomerDAO.getCustomerById(invoice.getCustomer());
        List<InvoiceItem> invoiceItems = InvoiceItemDAO.getItemsByInvoice(invoice.getInvoiceId());

        Entities.Address address = AddressDAO.getAddressById(customer.getAddress());
        fDato.setText(invoice.getDate());
        fId.setText("" + invoice.getInvoiceId());
        cNumber.setText("" + customer.getCustomerId());
        fCustomer.setText(customer.getCustomerName());
        customerName.setText(customer.getCustomerName());
        account.setText(customer.getBillingAccount());
        street.setText(address.getStreetName() + " " + address.getStreetNumber());
        town.setText(address.getPostalCode() + " " + address.getPostalTown());
        displayItems(invoiceItems);

    }

    /**
     * Klarerer items fra vinduet før en annen faktura skal vises.
     */
    private void hideItems(){
        products.getChildren().clear();
        prices.getChildren().clear();
    }

    /**
     * Viser alle tilhørende items til en faktura
     * @param invoiceItems en liste med alle items i en faktura.
     */
    private void displayItems(List<InvoiceItem> invoiceItems){
        float totalPrice = 0;
        for (InvoiceItem item : invoiceItems) {
            Product product = ProductDAO.getProductById(item.getProduct());
            Text prodName = new Text(product.getProductName());
            Text prodPrice = new Text(""+product.getPrice());

            totalPrice += product.getPrice();
            products.getChildren().add(prodName);
            prices.getChildren().add(prodPrice);

        }
        this.totalPrice.setText(""+totalPrice);
    }

    /**
     * Laster inn scene1 når klikket.
     * @param actionEvent
     * @throws IOException
     */
    public void homebut_click(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Laster inn neste faktura når klikket.
     * @param actionEvent
     * @throws IOException
     */
    public void nextBut_click(ActionEvent actionEvent) throws IOException {
        if(currentIndex < invoices.size() - 1) {
            currentIndex += 1;
            hideItems();
            displayInvoice(currentIndex);
        }
    }

    /**
     * Laster inn forrige faktura når klikket.
     * @param actionEvent
     * @throws IOException
     */
    public void prevBut_click(ActionEvent actionEvent) throws IOException {
        if (currentIndex != 0) {
            currentIndex -= 1;
            hideItems();
            displayInvoice(currentIndex);
        }
    }

}
