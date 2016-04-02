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
    String pathToFile = "output.csv";

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

    private void initData() {
        try {
            File csv = new File(pathToFile);
            CSVParser parser = CSVParser.parse(csv, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
            List<CSVRecord> list = parser.getRecords();

            for (CSVRecord s : list) {
                Contact contactTestWithFile = new Contact(
                        s.get(0).split(";")[0],
                        s.get(0).split(";")[1],
                        Collections.singletonList(
                                new Phone(
                                        s.get(0).split(";")[2].replace("[number:", ""),
                                        PhoneType.valueOf(s.get(1).replace("type:","").replace("]","").replace(" ","")))
                        )
                );
                phoneBookData.add(contactTestWithFile);
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("List is empty");
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
        String phoneNumber = phoneField.getText().split(",")[0];
        String phoneTypeNumber = phoneField.getText().split(",")[1];
        Contact contactAdd = new Contact(
                firstNameField.getText(),
                lastNameField.getText(),
                Collections.singletonList(new Phone(phoneNumber, PhoneType.valueOf(phoneTypeNumber)))
        );
        ObservableList<Contact> data = tableUsers.getItems();
        data.add(contactAdd);
        for (Contact contact : data) {
            contactPhone.add(contact);
        }
        contactPhone.save(pathToFile);

        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
    }


    @FXML
    private void deleteContact(ActionEvent event) {
        ContactPhone contactPhone = new ContactPhoneImpl();
        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        ObservableList<Contact> data = tableUsers.getItems();
        data.remove(selectedIndex);
        for (Contact c : data) {
            contactPhone.add(c);
        }
        contactPhone.save(pathToFile);
    }


}
