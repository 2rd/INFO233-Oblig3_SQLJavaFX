package GUI;

import DAO.InvoiceDAO;
import DAO.InvoiceItemDAO;
import Entities.Invoice;
import Entities.InvoiceItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewInvoiceController implements Initializable{
    @FXML
    TextField invoiceId, customerId, date, prod1, prod2;
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

    }

    public void goHome() throws IOException{
        HBox pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane, 500, 300);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    public void homeBut_click()throws IOException{
        goHome();
    }



    public Invoice newInvoice(){
        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceID(Integer.parseInt(invoiceId.getText()));
        newInvoice.setCustomer(Integer.parseInt(customerId.getText()));
        newInvoice.setDate(date.getText());
        return newInvoice;
    }

    public InvoiceItem newInvoiceItem(int productId, int invoiceId){
        InvoiceItem newItem = new InvoiceItem();
        newItem.setProduct(productId);
        newItem.setInvoice(invoiceId);
        return newItem;
    }

    public void button_addInvoice()throws IOException{
        Invoice newInvoice = newInvoice();
        InvoiceDAO.addInvoice(newInvoice);
        if (prod1.getText() != null){
            InvoiceItemDAO.addInvoiceItem(newInvoiceItem(Integer.parseInt(prod1.getText()), newInvoice.getInvoiceId()));
        }
        if (prod2.getText() != null){
            InvoiceItemDAO.addInvoiceItem(newInvoiceItem(Integer.parseInt(prod2.getText()), newInvoice.getInvoiceId()));
        }
        goHome();
    }
}