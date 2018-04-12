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

public class EditCustomersController implements Initializable{

    private int currentID;
    private Insets insets = new Insets(5, 5, 5, 5);

    @FXML
    VBox custID, custName, fullAddress, phone, account, currCust, editFields, editLabels;

    @FXML
    TextField ID;

    @FXML
    Parent parent;

    CustomerDAO customerDAO = CustomerDAO.getInstance();
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

    private void displayCustomers() throws IOException {
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        for (Customer customer : allCustomers) {
            Address address = AddressDAO.getAddressById(customer.getCustomerId());
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

    public void displayEditCustomer(int id){
        currCust.getChildren().clear();
        Customer customer = customerDAO.getCustomerById(id);
        Address address = AddressDAO.getAddressById(customer.getCustomerId());
        Text custId = new Text(""+customer.getCustomerId());
        Text name = new Text(customer.getCustomerName());
        Text addr = new Text(address.getStreetName() + " " + address.getStreetNumber() + ", " +
                address.getPostalCode() + " " + address.getPostalTown());
        Text pNumber = new Text(customer.getPhoneNumber());
        Text aNumber = new Text(customer.getBillingAccount());
        currCust.getChildren().add(custId);
        currCust.getChildren().add(name);
        currCust.getChildren().add(addr);
        currCust.getChildren().add(pNumber);
        currCust.getChildren().add(aNumber);
        currCust.setSpacing(5);
    }

    public void displayEditLabels(){
        Label newName = new Label("Nytt navn: ");
        Label newPNumber = new Label("Nytt tlf-nr: ");
        Label newANumber = new Label("Nytt kontonr: ");

        newName.setPadding(insets);
        newPNumber.setPadding(insets);
        newANumber.setPadding(insets);

        editLabels.getChildren().add(newName);
        editLabels.getChildren().add(newPNumber);
        editLabels.getChildren().add(newANumber);
    }

    public void displayEditFields(){
        TextField newName = new TextField("Nytt navn");
        TextField newPNumber = new TextField("Nytt tlf-nr");
        TextField newANumber = new TextField("Nytt kontonr");
        newName.setId("newName");
        newPNumber.setId("newPNumber");
        newANumber.setId("newANumber");
        editFields.getChildren().add(newName);
        editFields.getChildren().add(newPNumber);
        editFields.getChildren().add(newANumber);
        Button enter = new Button();
        enter.setOnAction(event -> {
            try {
                enterUpdates_click();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        editFields.getChildren().add(enter);
    }

    public void update() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditCustomer.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }

    public void enterUpdates_click() throws IOException{
        Customer customer = customerDAO.getCustomerById(currentID);
        customer.setCustomerName("Tormod Torsen");
        customer.setCustomerId(3);
        customer.setPhoneNumber("991 44 299");
        customer.setBillingAccount("4444 44 4444");
        customerDAO.addCustomer(customer);
        update();

    }

    public void edit_click() throws IOException{
        int id = Integer.parseInt(ID.getText());
        this.currentID = id;
        displayEditCustomer(id);
        displayEditLabels();
        displayEditFields();
    }

    public void homebut_click() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.setScene(scene);
    }
}
