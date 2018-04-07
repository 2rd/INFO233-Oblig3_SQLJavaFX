package GUI;

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
    Text fDato;
    @FXML
    Text fCustomer;
    @FXML
    Text fId, customerAdd, prodName, ;
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
        Entities.Customer customer = DAO.CustomerDAO.getCustomerById(invoice.getCustomer());
        fId.setText(" -- FakturaNr: " + invoice.getInvoiceId());
        fDato.setText(" -- Dato:   " + invoice.getDate());
        fCustomer.setText(" --  Kundenavn: " + customer.getCustomerName());
    }

    public void backBut_click(ActionEvent actionEvent) throws IOException {
        HBox pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane, 500, 300);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
}
