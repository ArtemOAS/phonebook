package service;

import data.Contact;
import service.exception.WrongDestinationException;

import java.io.*;
import java.util.Collection;

/**
 * Created by Artem on 08.03.2016.
 */
public class Writer {
    private static final String SEPARATOR = ";";

    public void write(String path, Collection<Contact> contacts) throws IOException {
        File file = new File(path);
        if(file.isDirectory()){
            throw new WrongDestinationException("Can't write to "+ path);
        }
        try(
                FileWriter fw = new FileWriter(file);
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
