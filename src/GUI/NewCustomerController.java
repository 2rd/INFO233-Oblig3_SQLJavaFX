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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class NewCustomerController {

    @FXML
    TextField customerId, customerName, phoneNr, account,
            addressId, streetName, streetNr, postCode, postTown;
    @FXML
    Parent parent;

    public void butAddCust_click(ActionEvent actionEvent) throws IOException {
        AddressDAO.addAddress(newAddress());
        CustomerDAO.addCustomer(newCustomer());
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
    public Address newAddress(){
        Address newAddress = new Address();
        newAddress.setAddressId(Integer.parseInt(addressId.getText()));
        newAddress.setStreetName(streetName.getText());
        newAddress.setStreetNumber(streetNr.getText());
        newAddress.setPostalCode(postCode.getText());
        newAddress.setPostalTown(postTown.getText());
        return newAddress;
    }
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