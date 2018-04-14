package GUI;


import DAO.ProductDAO;
import Entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for FXML-filen NewProduct.
 * Gjør det mulig å legge til nye kategorier i databasen.
 * @author Tord Kvifte, 13.04.2018
 */
public class NewProductController {
    @FXML
    TextField productId, productName, description, price, categoryId;
    @FXML
    Parent parent;

    /**
     * Legger til nytt produkt i databasen og går til scene1 når klikket.
     * @throws IOException
     */
    public void addProd_click() throws IOException {
        ProductDAO.addProduct(newProduct());
        goHome();
    }

    /**
     * Laster inn Scene1 når klikket.
     * @throws IOException
     */
    public void homeBut_click() throws IOException {
        goHome();
    }

    /**
     * Laster inn scene1
     * @throws IOException
     */
    public void goHome() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("GUI/Scene1.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Oppretter et nytt produkt basert på dataene i feltene.
     * @return nytt produkt
     */
    public Product newProduct(){
        Product newProduct = new Product();
        newProduct.setProductId(Integer.parseInt(productId.getText()));
        newProduct.setProductName(productName.getText());
        newProduct.setDescription(description.getText());
        newProduct.setCategory(Integer.parseInt(categoryId.getText()));
        newProduct.setPrice(Integer.parseInt(price.getText()));
        return newProduct;
    }
}
