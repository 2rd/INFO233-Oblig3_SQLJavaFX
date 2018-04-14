package GUI;

import DAO.AddressDAO;
import DAO.CustomerDAO;
import Entities.Address;
import Entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for FXML-filen NewCustomer.
 * Gjør det mulig å legge til nye kunder og adresser i databasen.
 * @author Tord Kvifte, 13.04.2018
 */
public class NewCustomerController {

    @FXML
    TextField customerId, customerName, phoneNr, account,
            addressId, streetName, streetNr, postCode, postTown;
    @FXML
    Parent parent;

    /**
     * Legger til ny kunde og adresse i databasen og går til scene1 når klikket.
     * @throws IOException
     */
    public void butAddCust_click() throws IOException {
        AddressDAO.addAddress(newAddress());
        CustomerDAO.addCustomer(newCustomer());
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
     * Oppretter en ny adresse basert på dataene i feltene.
     * @return ny adresse
     */
    public Address newAddress(){
        Address newAddress = new Address();
        newAddress.setAddressId(Integer.parseInt(addressId.getText()));
        newAddress.setStreetName(streetName.getText());
        newAddress.setStreetNumber(streetNr.getText());
        newAddress.setPostalCode(postCode.getText());
        newAddress.setPostalTown(postTown.getText());
        return newAddress;
    }

    /**
     * Oppretter en ny kunde basert på dataene i feltene.
     * @return ny kunde
     */
    public Customer newCustomer(){
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(Integer.parseInt(customerId.getText()));
        newCustomer.setCustomerName(customerName.getText());
        newCustomer.setAddress(Integer.parseInt(addressId.getText()));
        newCustomer.setPhoneNumber(phoneNr.getText());
        newCustomer.setBillingAccount(account.getText());
        return newCustomer;
    }
}
