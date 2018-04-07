package GUI;

import DAO.AddressDAO;
import DAO.CustomerDAO;
import DAO.ProductDAO;
import Entities.Invoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable{

    @FXML
    Text fDato, fId, cNumber, fCustomer, customerAdd, prodName, price;
    @FXML
    private Parent parent;

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
        displayInvoice();
    }

    public void displayInvoice(){
        Entities.Invoice invoice = DAO.InvoiceDAO.getInvoiceById(1);
        setInvoice(invoice);

    }
    public void setInvoice(Invoice invoice){
        Entities.Customer customer = CustomerDAO.getCustomerById(invoice.getCustomer());
        Entities.Product product = ProductDAO.getProductById(DAO.InvoiceItemDAO.getItemByInvoice(invoice.getInvoiceId()).getProduct());
        Entities.Address address = AddressDAO.getAddressById(customer.getAddress());
        fDato.setText(invoice.getDate());
        fId.setText("" + invoice.getInvoiceId());
        cNumber.setText("" + customer.getCustomerId());
        fCustomer.setText(customer.getCustomerName());
        customerAdd.setText(address.getStreetName() + " " + address.getStreetNumber() + ", " + address.getPostalCode() + " " + address.getPostalTown());
        prodName.setText(product.getProductName());
        price.setText("" + product.getPrice());

    }

    public void backBut_click(ActionEvent actionEvent) throws IOException {
        HBox pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane, 500, 300);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
}
