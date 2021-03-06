package GUI;

import DAO.InvoiceDAO;
import Entities.Invoice;
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
 * Controller for FXML-filen editInvoice.
 * Gjør det mulig å endre eksisterende fakturaer i databasen
 * gjennom et grafisk brukergrensesnitt.
 * @author Tord Kvifte, 13.04.2018
 */
public class EditInvoiceController implements Initializable {

    @FXML
    VBox invID, custName, date, currInv, editFields, editLabels;

    @FXML
    TextField ID, newCust, newDate;

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
            displayInvoices();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Henter alle fakturaene i databasen og viser fakturadata i vinduet.
     * @throws IOException
     */
    private void displayInvoices() throws IOException {

        List<Invoice> allInvoices = InvoiceDAO.getAllInvoices();
        for (Invoice invoice : allInvoices) {
            Text id = new Text(""+invoice.getInvoiceId());
            Text cName = new Text(""+invoice.getCustomer());
            Text dato = new Text(invoice.getDate());

            invID.getChildren().add(id);
            custName.getChildren().add(cName);
            date.getChildren().add(dato);
        }
    }

    /**
     * Laster inn vinduet på nytt.
     * @throws IOException
     */
    public void update() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditInvoice.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Kalles når endre-knappen klikkes.
     * Kjører updateInvoice og update dersom id-feltet er fylt ut.
     * @throws IOException
     */
    public void edit_click() throws IOException{
        if(!ID.getText().trim().isEmpty()){
            updateInvoice();
            update();
        } else {
            currInv.getChildren().add(new Text("***Faktura ID er obligatorisk***"));
        }

    }

    /**
     * Oppdaterer kolonnene til en invoice i databasen,
     * basert på info hentet fra tekstfeltene i vinduet.
     */
    public void updateInvoice(){

        int id = Integer.parseInt(ID.getText());
        Invoice invoice = InvoiceDAO.getInvoiceById(id);
        if(!newCust.getText().trim().isEmpty()){
            invoice.setCustomer(Integer.parseInt(newCust.getText()));
        }
        if (!newDate.getText().trim().isEmpty()){
            invoice.setDate(newDate.getText());
        }

        InvoiceDAO.editInvoice(invoice);
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
