package GUI;

import DAO.ProductDAO;
import Entities.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for FXML-filen editProduct.
 * Gjør det mulig å endre eksisterende produkter i databasen
 * gjennom et grafisk brukergrensesnitt.
 * @author Tord Kvifte, 13.04.2018
 */
public class EditProductController implements Initializable{

    @FXML
    VBox prodID, prodName, description, category, price, currProd, editFields, editLabels;

    @FXML
    TextField ID, newName, newDescription, newCategory, newPrice;

    @FXML
    Parent parent;


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
        try {
            displayProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Henter alle produktene i databasen og viser produktdata i vinduet.
     * @throws IOException
     */
    private void displayProducts() throws IOException {

        List<Product> allProducts = ProductDAO.getAllProducts();
        for (Product product : allProducts) {
            Text id = new Text(""+product.getProductId());
            Text pName = new Text(product.getProductName());
            Text descr = new Text(product.getDescription());
            Text Pprice = new Text(""+product.getPrice());
            Text pCat = new Text(""+product.getCategory());


            prodID.getChildren().add(id);
            prodName.getChildren().add(pName);
            description.getChildren().add(descr);
            price.getChildren().add(Pprice);
            category.getChildren().add(pCat);
        }
    }

    /**
     * Laster inn vinduet på nytt.
     * @throws IOException
     */
    public void update() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditProduct.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Kalles når endre-knappen klikkes.
     * Kjører updateProduct og update dersom id-feltet er fylt ut.
     * @throws IOException
     */
    public void edit_click() throws IOException{
        if(!ID.getText().trim().isEmpty()){
            updateProduct();
            update();
        } else {
            currProd.getChildren().add(new Text("***Adresse ID er obligatorisk***"));
        }

    }

    /**
     * Oppdaterer kolonnene til et produkt i databasen,
     * basert på info hentet fra tekstfeltene i vinduet.
     */
    public void updateProduct(){

        int id = Integer.parseInt(ID.getText());
        Product product = ProductDAO.getProductById(id);
        if(!newName.getText().trim().isEmpty()){
            product.setProductName(newName.getText());
        }
        if (!newDescription.getText().trim().isEmpty()){
            product.setDescription(newDescription.getText());
        }
        if(!newCategory.getText().trim().isEmpty()){
            product.setCategory(Integer.parseInt(newCategory.getText()));
        }
        if(!newPrice.getText().trim().isEmpty()){
            product.setPrice(Float.parseFloat(newPrice.getText()));
        }
        ProductDAO.editProduct(product);
    }

    /**
     * Kalles når hjem-knappen klikkes.
     * Scene1 lastes inn.
     * @throws IOException
     */
    public void homebut_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
}
