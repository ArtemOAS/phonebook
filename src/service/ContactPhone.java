package service;

import data.Contact;
import data.Phone;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public interface ContactPhone<E>{
    boolean add(Contact contact);
    boolean remove(Contact contact);
    Collection<Contact> search(Contact contact);
    void write(String path, Map<Object, Object> contacts);
    void save(String path);
}
