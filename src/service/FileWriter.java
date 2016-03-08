package service;

import data.Contact;
import service.exception.WrongDestinationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Created by Artem on 08.03.2016.
 */
public class FileWriter {
    private static final String SEPARATOR = ";";

    public void write(String path, Collection<Contact> contacts) throws IOException {
        File file = new File(path);
        if(file.isDirectory()){
            throw new WrongDestinationException("Can't write to "+ path);
        }
        try(
                java.io.FileWriter fw = new java.io.FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
        ){
            pw.println(convertToCSV(contacts));
            pw.flush();}
    }

    private String convertToCSV(Collection<Contact> contacts){
        String result = "";
        for (Contact contact:contacts){
            result+=contact.getFirstName()+SEPARATOR+contact.getLastName()+SEPARATOR+
                    contact.getPhones()+"\n";
        }
        return result;
    }
}
