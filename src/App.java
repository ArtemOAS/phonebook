import data.Contact;
import data.Phone;
import data.PhoneType;
import service.ContactPhone;
import service.Writer;
import service.impl.ContactPhoneImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Artem on 07.03.2016.
 */
public class App {
    public static void main(String[] args) {
        List<Phone> phones = new ArrayList<>();
        ContactPhone contactPhone = new ContactPhoneImpl();



//        Contact contact = new Contact("lalalala", "aaaaaaaaa");
//        Contact contact1 = new Contact("lasdfsddlalala", "aaasdfsdfsaaaaaa");

//        Phone phone = new Phone(Collections.singletonList(contact),555, PhoneType.FAX);
//        Phone phone1 = new Phone(Collections.singletonList(contact1),555, PhoneType.FAX);

//        contactPhone.add(phone);
//        contactPhone.add(phone1);
//        contactPhone.remove(contact);
        contactPhone.save("output.csv");
        /*List<String> texts = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("output.csv"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            String[] allText = everything.split("\n");
            for (int i = 0; i<allText.length; i++){
                String[] allTexts = allText[i].split(";");
                for (int j = 0; j<allTexts.length; j++){
                    texts.add(allTexts[j]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(texts.get(0) + texts.get(1));*/
    }
}
