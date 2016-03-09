package service;

import data.Contact;

import java.util.Collection;

public interface ContactPhone<E>{
    boolean add(Contact contact);
    boolean remove(Contact contact);
    Collection<Contact> search(Contact contact);
}
