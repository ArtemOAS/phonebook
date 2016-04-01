package sample;

import data.Contact;
import data.Phone;
import data.PhoneType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import service.ContactPhone;
import service.impl.ContactPhoneImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class Controller {

    private ObservableList<Contact> phoneBookData = FXCollections.observableArrayList();

    @FXML
    private TableView<Contact> tableUsers;

    @FXML
    private TableColumn<Contact, String> firstNameColumn;

    @FXML
    private TableColumn<Contact, String> lastNameColumn;

    @FXML
    private TableColumn<Contact, String> phonesColumn;


    @FXML
    private void initialize() {
        initData();
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        phonesColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));

        tableUsers.setItems(phoneBookData);
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        tableUsers.getItems().remove(selectedIndex);
        ContactPhone contactPhone = new ContactPhoneImpl();
        contactPhone.remove(new Contact("sdsdsd", "sadsdsds", Collections.singletonList(new Phone("555", PhoneType.FAX))));
        contactPhone.save("output.csv");
    }


    private void initData() {
        ContactPhone contactPhone = new ContactPhoneImpl();

        Contact contact = new Contact("sdsdsd", "sadsdsds", Collections.singletonList(new Phone("555", PhoneType.FAX)));
        Contact contact2 = new Contact("sdsfgfgfdsd", "fgfgfg", Collections.singletonList(new Phone("4545", PhoneType.FAX)));
        Contact contact3 = new Contact("sdsfsdfsgsggfgfdsd", "fgfdsfsdfsdfsgfg", Collections.singletonList(new Phone("3232", PhoneType.HOME_PHONE)));
        Contact contact4 = new Contact("Julia", "Oleynik", Collections.singletonList(new Phone("000000", PhoneType.HOME_PHONE)));
        contactPhone.add(contact);
        contactPhone.add(contact2);
        contactPhone.add(contact3);
        contactPhone.add(contact4);
        contactPhone.save("output.csv");

        phoneBookData.add(contact);

        try {
            File csv = new File("output.csv");
            CSVParser parser = CSVParser.parse(csv, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
            List<CSVRecord> list = parser.getRecords();

            for (CSVRecord s : list) {
                Contact contactTestWithFile = new Contact(
                        s.get(0).split(";")[0],
                        s.get(0).split(";")[1],
                        Collections.singletonList(new Phone(s.get(0).split(";")[2] + s.get(1), null))
                );
                phoneBookData.add(contactTestWithFile);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void showPersonDetails(Contact contact) {
        if (contact != null) {
            firstNameColumn.setText(contact.getFirstName());
            lastNameColumn.setText(contact.getLastName());
            phonesColumn.setText(String.valueOf(contact.getPhone()));
        } else {
            firstNameColumn.setText("");
            lastNameColumn.setText("");
            phonesColumn.setText("");
        }
    }

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;

    @FXML
    protected void addContact(ActionEvent event) {
        ContactPhone contactPhone = new ContactPhoneImpl();
        Contact contactAdd = new Contact(
                firstNameField.getText(),
                lastNameField.getText(),
                Collections.singletonList(new Phone(phoneField.getText(), PhoneType.HOME_PHONE)));

        ObservableList<Contact> data = tableUsers.getItems();
        data.add(contactAdd);
        contactPhone.add(contactAdd);
        contactPhone.save("output.csv");

        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
    }
}
