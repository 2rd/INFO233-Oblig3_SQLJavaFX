package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {
    @FXML
    private Parent parent;

    public void fbut_click() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    public void newInvoice_click() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewInvoice.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    public void newCustomer_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    public void newProduct_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewProduct.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    public void newCategory_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NewCategory.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    public void editCustomers_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCustomer.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    public void editInvoices_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditInvoice.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    public void editAddresses_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditAddress.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    public void editCategories_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCategory.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    public void editProducts_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditProduct.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

}
