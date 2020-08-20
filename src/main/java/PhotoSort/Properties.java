package PhotoSort;

import PhotoSort.Exceptions.FSException;

import java.io.File;
import java.nio.file.Paths;

public class Properties {
    /*
    properies
     */
    //FS
    private static Properties instance;
    private static String sourcePath;
    private static String targetPath;
    private static String resources;
    private static String pathSeparator;


    public String getSourcePath() {
        return sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getResources() {
        return resources;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    private Properties() {

    }

    public static Properties getInstance(){
        if(instance == null)
            instance = new Properties();
        return instance;

    }

    public void setProperties(String[] args){

        sourcePath = args[0];
        targetPath = args[1];
        pathSeparator = FSConnector.getSeparator();
        resources = targetPath + pathSeparator + "resources";
        try {
            if(!FSConnector.checkForExist(targetPath))
                FSConnector.createPath(targetPath);

            if(!FSConnector.checkForExist(resources))
                FSConnector.createPath(resources);

        } catch (FSException e) {
            System.out.println(e.getMessage());
        }
    }

    }






