package sample;

import data.Contact;
import data.Phone;
import data.PhoneType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import service.ContactPhone;
import service.impl.ContactPhoneImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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


    private void initData() {
        ContactPhone contactPhone = new ContactPhoneImpl();

        Contact contact = new Contact("sdsdsd","sadsdsds", Collections.singletonList(new Phone("555", PhoneType.FAX)));
        Contact contact2 = new Contact("sdsfgfgfdsd","fgfgfg", Collections.singletonList(new Phone("4545", PhoneType.FAX)));
        Contact contact3 = new Contact("sdsfsdfsgsggfgfdsd","fgfdsfsdfsdfsgfg", Collections.singletonList(new Phone("3232", PhoneType.HOME_PHONE)));
        contactPhone.add(contact);
        contactPhone.add(contact2);
        contactPhone.add(contact3);
        contactPhone.save("output.csv");

        phoneBookData.add(contact);




//        contactPhone.add(contact);
//        contactPhone.add(contact1);
//        contactPhone.remove(contact);
//        contactPhone.save("output.csv");

        List<String> texts = new ArrayList<>();
        String[] allText = new String[0];
        try(BufferedReader br = new BufferedReader(new FileReader("output.csv"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            File csv = new File("output.csv");
            CSVParser parser = CSVParser.parse(csv, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
            List<CSVRecord> list = parser.getRecords();

            for (CSVRecord s: list){
            }

            String everything = sb.toString();
            allText = everything.replace("\r","").split("\n");
            for (int i = 0; i<allText.length; i++){
                String[] allTexts = allText[i].split(";");
                for (int j = 0; j<allTexts.length; j++){
                    texts.add(allTexts[j]);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        for (int i = 0; i<allText.length; i++){
            for (String s: texts){
                Contact contactTestWithFile = new Contact(s,s, Collections.singletonList(new Phone(s, PhoneType.FAX)));
                phoneBookData.add(contactTestWithFile);
            }

        }


    }


}
