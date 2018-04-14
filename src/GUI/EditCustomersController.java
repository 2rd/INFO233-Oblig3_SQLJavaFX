package GUI;

import DAO.AddressDAO;
import DAO.CustomerDAO;
import Entities.Address;
import Entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * Controller for FXML-filen editCustomer.
 * Gjør det mulig å endre eksisterende kunder i databasen
 * gjennom et grafisk brukergrensesnitt.
 * @author Tord Kvifte, 13.04.2018
 */
public class EditCustomersController implements Initializable{

    @FXML
    VBox custID, custName, fullAddress, phone, account, currCust, editFields, editLabels;

    @FXML
    TextField ID, newName, newAddress, newPhone, newAccount;

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
            displayCustomers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Henter alle kundene i databasen og viser kundedata i vinduet.
     * @throws IOException
     */
    private void displayCustomers() throws IOException {

        List<Customer> allCustomers = CustomerDAO.getAllCustomers();
        for (Customer customer : allCustomers) {
            Address address = AddressDAO.getAddressById(customer.getAddress());
            Text id = new Text(""+customer.getCustomerId());
            Text name = new Text(customer.getCustomerName());
            Text addr = new Text(address.getStreetName() + " " + address.getStreetNumber() + ", " +
                    address.getPostalCode() + " " + address.getPostalTown());
            Text pNumber = new Text(customer.getPhoneNumber());
            Text aNumber = new Text(customer.getBillingAccount());


            custID.getChildren().add(id);
            custName.getChildren().add(name);
            fullAddress.getChildren().add(addr);
            phone.getChildren().add(pNumber);
            account.getChildren().add(aNumber);
        }
    }

    /**
     * Laster inn vinduet på nytt.
     * @throws IOException
     */
    public void update() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCustomer.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    /**
     * Kalles når endre-knappen klikkes.
     * Kjører updateCustomer og update dersom id-feltet er fylt ut.
     * @throws IOException
     */
    public void edit_click() throws IOException{
        if(!ID.getText().trim().isEmpty()){
            updateCustomer();
            update();
        } else {
            currCust.getChildren().add(new Text("***Kunde ID er obligatorisk***"));
        }

    }

    /**
     * Oppdaterer kolonnene til en kunde i databasen,
     * basert på info hentet fra tekstfeltene i vinduet.
     */
    public void updateCustomer(){

        int id = Integer.parseInt(ID.getText());
        Customer customer = CustomerDAO.getCustomerById(id);
        if(!newName.getText().trim().isEmpty()){
            customer.setCustomerName(newName.getText());
        }
        if (!newAddress.getText().trim().isEmpty()){
            customer.setAddress(Integer.parseInt(newAddress.getText()));
        }
        if(!newPhone.getText().trim().isEmpty()){
            customer.setPhoneNumber(newPhone.getText());
        }
        if(!newAccount.getText().trim().isEmpty()){
            customer.setBillingAccount(newAccount.getText());
        }
        CustomerDAO.editCustomer(customer);
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
