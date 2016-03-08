package service.tree;

import service.exception.WrongDirrectoryException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Artem on 08.03.2016.
 */
public class FileTreePrinter {
    private static final FileComparator FILE_COMPARATOR = new FileComparator();

    public void printTree(String path){
        File file = new File(path);
        if(!file.isDirectory()){
            throw new WrongDirrectoryException("No such directory: "+ path);
        }
        print(file,0);
    }
    private void print(File dir, int deepLevel){

        System.out.println(getOffset(deepLevel)+dir.getName());
        List<File> directories = new ArrayList<>();
        List<File> files = new ArrayList<>();

        for(File file:dir.listFiles()){
            if(file.isDirectory()){
                directories.add(file);
            } else {
                files.add(file);
            }
        }
        Collections.sort(directories);
        Collections.sort(files, FILE_COMPARATOR);
        for(File file: directories){
            print(file,deepLevel+1);
        }
        String offset = getOffset(deepLevel+1);
        for (File file: files){
            System.out.println(offset+file.getName()+" "+file.length()+" bytes");
        }
    }
    private String getOffset(int deepLevel){
        if (deepLevel == 0){
            return "";
        }
        String offset = "";
        for (int i=0; i<deepLevel - 1; i++){
            offset+="|\t";
        }
        return offset + "| - ";
    }

    private static class FileComparator implements Comparator<File> {

        @Override
        public int compare(File o1, File o2) {
            return Long.compare(o1.length(), o2.length());
        }
    }
}
