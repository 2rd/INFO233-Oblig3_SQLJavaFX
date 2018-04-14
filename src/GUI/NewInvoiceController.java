package GUI;

import DAO.InvoiceDAO;
import DAO.InvoiceItemDAO;
import Entities.Invoice;
import Entities.InvoiceItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for FXML-filen NewInvoice.
 * Gjør det mulig å legge til nye fakturaer i databasen.
 * @author Tord Kvifte, 13.04.2018
 */
public class NewInvoiceController{
    @FXML
    TextField invoiceId, customerId, date;
    @FXML
    VBox products;
    @FXML
    private Parent parent;

    /**
     * laster inn scene1.
     * @throws IOException
     */
    public void goHome() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Laster inn Scene1 når klikket.
     * @throws IOException
     */
    public void homeBut_click()throws IOException{
        goHome();
    }

    /**
     * Oppretter en ny faktura basert på data fra tekstfeltene.
     * @throws IOException
     */
    public Invoice newInvoice(){
        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceID(Integer.parseInt(invoiceId.getText()));
        newInvoice.setCustomer(Integer.parseInt(customerId.getText()));
        newInvoice.setDate(date.getText());
        return newInvoice;
    }

    /**
     * Oppretter en ny invoice item basert på data fra tekstfeltene.
     * @throws IOException
     */
    public InvoiceItem newInvoiceItem(int productId, int invoiceId){
        InvoiceItem newItem = new InvoiceItem();
        newItem.setProduct(productId);
        newItem.setInvoice(invoiceId);
        return newItem;
    }

    /**
     * Legger til ny faktura i databasen og går til scene1 når klikket.
     * @throws IOException
     */
    public void button_addInvoice()throws IOException{
        Invoice newInvoice = newInvoice();
        InvoiceDAO.addInvoice(newInvoice);
        for (Node product : products.getChildren()) {
            TextField productText = (TextField) product;
            int productId = Integer.parseInt(productText.getText());
            InvoiceItemDAO.addInvoiceItem(newInvoiceItem(productId, newInvoice.getInvoiceId()));
        }
        goHome();
    }

    /**
     * Legger til et ekstra tekstfelt når klikket.
     * @throws IOException
     */
    public void newProd_click()throws IOException{
        TextField additionalProduct = new TextField();
        products.getChildren().add(additionalProduct);
    }
}
