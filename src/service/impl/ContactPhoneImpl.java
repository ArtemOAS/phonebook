package service.impl;

import data.Contact;
import data.Phone;
import service.ContactPhone;
import service.exception.WrongDestinationException;

import java.io.*;
import java.util.*;

public class ContactPhoneImpl implements ContactPhone {
    private static final String SEPARATOR = ";";


    private Map<Phone, Collection<Contact>> phonesInContact = new HashMap<>();

    @Override
    public boolean add(Contact contact) {
        boolean flagIsAdd = false;
            for (Phone phone : contact.getPhone()) {
                Collection<Contact> contacts = phonesInContact.get(phone);
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
        for (Phone phone: contact.getPhone()) {
            Collection<Contact> contacts = phonesInContact.get(phone);
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
        try (
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
        ) {
            pw.println(convertToCSV(collection));
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertToCSV(Map<Phone, Collection<Contact>> phonesAndContact){
        String result = "";

        for (Collection<Contact> contacts: phonesInContact.values()){
            for (Contact contact: contacts) {
                result+=contact.getFirstName()+SEPARATOR+
                        contact.getLastName()+SEPARATOR+
                        contact.getPhone()+"\n";
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
