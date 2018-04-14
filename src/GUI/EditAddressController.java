package GUI;

import DAO.AddressDAO;
import Entities.Address;
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
 * Controller for FXML-filen editAddress.
 * Gjør det mulig å endre eksisterende addresser i databasen
 * gjennom et grafisk brukergrensesnitt.
 * @author Tord Kvifte, 13.04.2018
 */
public class EditAddressController implements Initializable{
    @FXML
    VBox addrID, streetName, streetNo, town, postalCode, currAddr, editFields, editLabels;

    @FXML
    TextField ID, newStreet, newStNo, newTown, newPCode;

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
            displayAddresses();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Henter alle adressene i databasen og viser adressedata i vinduet.
     * @throws IOException
     */
    private void displayAddresses() throws IOException {

        List<Address> allAddresses = AddressDAO.getAllAddresses();
        for (Address address : allAddresses) {
            Text id = new Text(""+address.getAddressId());
            Text sName = new Text(address.getStreetName());
            Text sNo = new Text(address.getStreetNumber());
            Text pTown = new Text(address.getPostalTown());
            Text pCode = new Text(address.getPostalCode());


            addrID.getChildren().add(id);
            streetName.getChildren().add(sName);
            streetNo.getChildren().add(sNo);
            town.getChildren().add(pTown);
            postalCode.getChildren().add(pCode);
        }
    }

    /**
     * Laster inn vinduet på nytt.
     * @throws IOException
     */
    public void update() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditAddress.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Kalles når endre-knappen klikkes.
     * Kjører updateAddress og update dersom id-feltet er fylt ut.
     * @throws IOException
     */
    public void edit_click() throws IOException{
        if(!ID.getText().trim().isEmpty()){
            updateAddress();
            update();
        } else {
            currAddr.getChildren().add(new Text("***Adresse ID er obligatorisk***"));
        }

    }

    /**
     * Oppdaterer kolonnene til en adresse i databasen,
     * basert på info hentet fra tekstfeltene i vinduet.
     */
    public void updateAddress(){

        int id = Integer.parseInt(ID.getText());
        Address address = AddressDAO.getAddressById(id);
        if(!newStreet.getText().trim().isEmpty()){
            address.setStreetName(newStreet.getText());
        }
        if (!newStNo.getText().trim().isEmpty()){
            address.setStreetNumber(newStNo.getText());
        }
        if(!newTown.getText().trim().isEmpty()){
            address.setPostalTown(newTown.getText());
        }
        if(!newPCode.getText().trim().isEmpty()){
            address.setPostalCode(newPCode.getText());
        }
        AddressDAO.editAddress(address);
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
