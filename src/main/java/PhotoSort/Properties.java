package PhotoSort;

import PhotoSort.Exceptions.FSException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

public class Properties {
    /*
    properies
     */
    private String currentPath;
    private String sourcePath;
    private String targetPath;
    private String resources;
    private String dbPath;

    Properties(String[] args){
        currentPath = FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString();
        sourcePath = args[0];
        targetPath = args[1];
        resources = currentPath + "\\resources";
        if(!FSConnector.checkForExist(resources)){
            try {
                FSConnector.createPath(resources);
            } catch (FSException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public boolean saveProperies(){

        //try(OutputStream output = new FileOutputStream(resources + "\\"))


        return true;
    }

    public boolean restoreProperties(){


        return true;
    }




}
