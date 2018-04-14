package GUI;

import DAO.CategoryDAO;
import Entities.Category;
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
 * Controller for FXML-filen editCategory.
 * Gjør det mulig å endre eksisterende kategorier i databasen
 * gjennom et grafisk brukergrensesnitt.
 * @author Tord Kvifte, 13.04.2018
 */
public class EditCategoryController implements Initializable{

    @FXML
    VBox catID, catName, currCat, editFields, editLabels;

    @FXML
    TextField ID, newCat;

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
            displayCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Henter alle kategorier i databasen og viser kategoridata i vinduet.
     * @throws IOException
     */
    private void displayCategories() throws IOException {

        List<Category> allCategories = CategoryDAO.getAllCategories();
        for (Category category : allCategories) {
            Text id = new Text(""+category.getCategoryId());
            Text cName = new Text(""+category.getCategoryName());

            catID.getChildren().add(id);
            catName.getChildren().add(cName);
        }
    }

    /**
     * Laster inn vinduet på nytt.
     * @throws IOException
     */
    public void update() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCategory.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Kalles når endre-knappen klikkes.
     * Kjører updateCategory og update dersom id-feltet er fylt ut.
     * @throws IOException
     */
    public void edit_click() throws IOException{
        if(!ID.getText().trim().isEmpty()){
            updateCategory();
            update();
        } else {
            currCat.getChildren().add(new Text("***Kategori ID er obligatorisk***"));
        }

    }

    /**
     * Oppdaterer kolonnene til en kategori i databasen,
     * basert på info hentet fra tekstfeltene i vinduet.
     */
    public void updateCategory(){

        int id = Integer.parseInt(ID.getText());
        Category category = CategoryDAO.getCategoryById(id);
        if(!newCat.getText().trim().isEmpty()){
            category.setCategoryName(newCat.getText());
        }
        CategoryDAO.editCategory(category);
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
