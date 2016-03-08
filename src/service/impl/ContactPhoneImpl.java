package service.impl;

import data.Contact;
import data.Phone;
import service.ContactPhone;

import java.util.*;

public class ContactPhoneImpl implements ContactPhone {

    private Map<Phone, Set<Contact>> phonesInContact = new HashMap<>();


    @Override
    public boolean add(Contact contact) {
        boolean flagIsAdd = false;
        for (Phone phone: contact.getPhones()) {
            Set<Contact> contacts = phonesInContact.get(contact);
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
            Set<Contact> contacts = phonesInContact.get(contact);
            if (contacts != null) {
                flagIsDelete = contacts.remove(contact);
            }
        }
        return flagIsDelete;
    }

    @Override
    public Collection<Contact> search(Contact contact) {
        Set<Contact> contacts = phonesInContact.get(contact);
        return contacts == null ? Collections.EMPTY_SET : contacts;
    }

    @Override
    public String toString() {
        return "ContactPhoneImpl{" +
                "phonesInContact=" + phonesInContact +
                '}';
    }
}
