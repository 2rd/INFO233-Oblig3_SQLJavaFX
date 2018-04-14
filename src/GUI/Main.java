package GUI;

import DAO.ConnectionDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Programmets Main-metode.
 * Oppretter forbindelse med databasen og starter det grafiske grensesnittet.
 */
public class Main extends Application{
    private Stage window;
    public static Connection connextion;

    public static void main(String[] args) throws SQLException {
        connextion = ConnectionDAO.getConnection();
        launch(args);
        connextion.close();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        primaryStage.setTitle("The Super Sales Register 5000");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
