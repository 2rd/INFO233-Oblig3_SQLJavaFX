package GUI;


import DAO.ProductDAO;
import Entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NewProductController {
    @FXML
    TextField productId, productName, description, price, categoryId;
    @FXML
    Parent parent;

    public void addProd_click(ActionEvent actionEvent) throws IOException {
        ProductDAO.addProduct(newProduct());
        goHome();
    }

    public void homeBut_click(ActionEvent actionEvent) throws IOException {
        goHome();
    }
    public void goHome() throws IOException{
        HBox pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane, 500, 300);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
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
