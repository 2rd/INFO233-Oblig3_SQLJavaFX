package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        VBox pane = FXMLLoader.load(getClass().getResource("NewInvoice.fxml"));
        Scene scene = new Scene(pane, 500, 300);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
    public void newCustomer_click() throws IOException{
        VBox pane = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
        Scene scene = new Scene(pane, 500, 300);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
}
