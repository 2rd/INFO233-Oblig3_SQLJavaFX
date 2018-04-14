package GUI;

import DAO.CategoryDAO;
import Entities.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for FXML-filen NewCategory.
 * Gjør det mulig å legge til nye kategorier i databasen.
 * @author Tord Kvifte, 13.04.2018
 */
public class NewCategoryController {
    @FXML
    TextField categoryId, categoryName;
    @FXML
    Parent parent;

    /**
     * Legger til ny kategori i databasen og går til scene1 når klikket.
     * @throws IOException
     */
    public void addCat_click() throws IOException {
        CategoryDAO.addCategory(newCategory());
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
     * Oppretter en ny kategori basert på dataene i feltene.
     * @return ny kategori
     */
    public Category newCategory(){
        Category newCategory = new Category();
        newCategory.setCategoryId(Integer.parseInt(categoryId.getText()));
        newCategory.setCategoryName(categoryName.getText());

        return newCategory;
    }
}
