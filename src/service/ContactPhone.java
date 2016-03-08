package service;

import data.Contact;
import data.Phone;

import java.util.Collection;

public interface ContactPhone {
    boolean add(Contact contact);
    boolean remove(Contact contact);
    Collection<Contact> search(Contact contact);
}
