package service.impl;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import data.Contact;
import data.Phone;
import service.ContactPhone;
import service.exception.WrongDestinationException;

import java.io.*;
import java.util.*;

public class ContactPhoneImpl implements ContactPhone {
    private static final String SEPARATOR = ";";


    public Map<Phone, Collection<Contact>> phonesInContact = new HashMap<>();


    @Override
    public boolean add(Contact contact) {
        boolean flagIsAdd = false;
        for (Phone phone: contact.getPhones()) {
            Collection<Contact> contacts = phonesInContact.get(contact);
            if (contacts == null) {
                contacts = new HashSet<>();
                phonesInContact.put(phone, contacts);
            }
            flagIsAdd = contacts.add(contact);
        }

        return flagIsAdd;
    }

    @Override
    public boolean remove(Contact contact) {
        boolean flagIsDelete = false;
        for (Phone phone: contact.getPhones()) {
            Collection<Contact> contacts = phonesInContact.get(contact);
            if (contacts != null) {
                flagIsDelete = contacts.remove(contact);
            }
        }
        return flagIsDelete;
    }

    @Override
    public Collection<Contact> search(Contact contact) {
        Collection<Contact> contacts = phonesInContact.get(contact);
        return contacts == null ? Collections.EMPTY_SET : contacts;
    }

    @Override
    public void save(String path) {
        write(path, phonesInContact);
    }

    @Override
    public void write(String path, Map collection) {
        File file = new File(path);
        if(file.isDirectory()){
            throw new WrongDestinationException("Can't write to "+ path);
        }
        try {
            try(
                    FileOutputStream fos = new FileOutputStream(path);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    PrintWriter pw = new PrintWriter(oos);
            ){
                pw.println(convertToCSV(collection));
                pw.flush();}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertToCSV(Map<Phone, Collection<Contact>> phonesAndContact){
        String result = "";

        for (Collection<Contact> contacts: phonesAndContact.values()){
            for (Contact contact: contacts) {
                result+=contact.getFirstName()+SEPARATOR+contact.getLastName()+SEPARATOR+
                        contact.getPhones()+"\n";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "ContactPhoneImpl{" +
                "phonesInContact=" + phonesInContact +
                '}';
    }

}
