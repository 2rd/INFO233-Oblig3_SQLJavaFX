package GUI;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Entities.Category;
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

public class NewCategoryController {
    @FXML
    TextField categoryId, categoryName;
    @FXML
    Parent parent;

    public void addCat_click(ActionEvent actionEvent) throws IOException {
        CategoryDAO.addCategory(newCategory());
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
    public Category newCategory(){
        Category newCategory = new Category();
        newCategory.setCategoryId(Integer.parseInt(categoryId.getText()));
        newCategory.setCategoryName(categoryName.getText());

        return newCategory;
    }
}
