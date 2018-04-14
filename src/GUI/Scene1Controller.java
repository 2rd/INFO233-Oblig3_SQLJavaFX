package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for FXML-filen Scene1.
 * Er programmets hjem-side og gir tilgang til alle programmets funksjoner
 * gjennom et grafisk grensesnitt.
 *
 * @author Tord Kvifte, 13.04.2018
 */
public class Scene1Controller {
    @FXML
    private Parent parent;

    /**
     * Laster inn Invoice.fxml når klikket.
     * @throws IOException
     */
    public void fbut_click() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn NewInvoice.fxml når klikket.
     * @throws IOException
     */
    public void newInvoice_click() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewInvoice.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn NewCustomer.fxml når klikket.
     * @throws IOException
     */
    public void newCustomer_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn NewProduct.fxml når klikket.
     * @throws IOException
     */
    public void newProduct_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewProduct.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn NewCategory.fxml når klikket.
     * @throws IOException
     */
    public void newCategory_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewCategory.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn EditCustomer.fxml når klikket.
     * @throws IOException
     */
    public void editCustomers_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCustomer.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn EditInvoice.fxml når klikket.
     * @throws IOException
     */
    public void editInvoices_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditInvoice.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn EditAddress.fxml når klikket.
     * @throws IOException
     */
    public void editAddresses_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditAddress.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Laster inn EditCategory.fxml når klikket.
     * @throws IOException
     */
    public void editCategories_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCategory.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * Laster inn EditProduct.fxml når klikket.
     * @throws IOException
     */
    public void editProducts_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditProduct.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

}
