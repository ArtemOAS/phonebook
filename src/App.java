import data.Contact;
import data.Phone;
import data.PhoneType;
import service.ContactPhone;
import service.Writer;
import service.impl.ContactPhoneImpl;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Artem on 07.03.2016.
 */
public class App {
    public static void main(String[] args) {
        List<Phone> phones = new ArrayList<>();

        phones.add(new Phone(555, PhoneType.FAX));
        phones.add(new Phone(555, PhoneType.HOME_PHONE));

        Contact contact = new Contact("lalalala", "aaaaaaaaa", phones);
        Contact contact1 = new Contact("rrrrr", "rtgfgdf", phones);
        ContactPhone contactPhone = new ContactPhoneImpl();

        contactPhone.add(contact);
        contactPhone.add(contact1);

        contactPhone.write("output.csv", Arrays.asList(contact,contact1));

//        Writer fileWriter = new Writer();
//
//        try {
//            fileWriter.write("output.csv", Arrays.asList(contact,contact1));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
