package sample;

import data.Contact;
import data.Phone;
import data.PhoneType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import sample.exception.WrongEnteredValueContactException;
import service.ContactPhone;
import service.impl.ContactPhoneImpl;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String regexForText = "^[a-zA-Z]+$";
        String regexForNumber = "^[0-9]+$";
        ContactPhone contactPhone = new ContactPhoneImpl();
        String phoneNumber = phoneField.getText().split(",")[0];
        String phoneTypeNumber = "";

        try {
            phoneTypeNumber = phoneField.getText().split(",")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (
                !testEnteredValue(firstNameField.getText(), regexForText) ||
                        !testEnteredValue(lastNameField.getText(), regexForText) ||
                        !testEnteredValue(phoneNumber, regexForNumber) ||
                        !testPhoneType(phoneTypeNumber)
                ) {
            if (!testEnteredValue(firstNameField.getText(), regexForText)){
                throw new WrongEnteredValueContactException(
                        "Warning",
                        "Incorrect entered value 'First Name'",
                        "Please click the button \"Ok\" and adjust the value 'First Name'"
                );
            }else
            if (!testEnteredValue(lastNameField.getText(), regexForText)){
                throw new WrongEnteredValueContactException(
                        "Warning",
                        "Incorrect entered value 'Last Name'",
                        "Please click the button \"Ok\" and adjust the value 'Last Name'"
                );
            }else
            if (!testEnteredValue(phoneNumber, regexForNumber)){
                throw new WrongEnteredValueContactException(
                        "Warning",
                        "Incorrect entered value 'Phone number'",
                        "Please click the button \"Ok\" and adjust the value 'Phone number'"
                );
            }else
            if (!testPhoneType(phoneTypeNumber)) {
                throw new WrongEnteredValueContactException(
                        "Warning",
                        "Incorrect entered value 'Phone Type'",
                        "Please click the button \"Ok\" and adjust the value 'Phone Type' to the specified"
                );
            }else
            {
                throw new WrongEnteredValueContactException(
                        "Warning",
                        "Incorrect entered values",
                        "Please click the button \"Ok\" and adjust the values"
                );
            }
        }

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

    private static boolean testEnteredValue(String testString, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    private Boolean testPhoneType(String text) {
        List<String> phoneTypes = new ArrayList<>();
        phoneTypes.add("FAX");
        phoneTypes.add("HOME_PHONE");
        phoneTypes.add("MOBILE");

        if (phoneTypes.contains(text)) {
            return true;
        } else {
           return false;
        }
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
