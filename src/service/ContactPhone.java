package service;

import data.Contact;

import java.io.File;
import java.util.Collection;

public interface ContactPhone<E>{
    boolean add(Contact contact);
    boolean remove(Contact contact);
    Collection<Contact> search(Contact contact);
    void write(String path, Collection<Object> contacts);
    void save(String path);
}
