package PhotoSort;

public class Properties {
    /*
    properies
     */
    private String currentPath;
    private String sourcePath;
    private String targetPath;

    Properties(String[] args){
        currentPath = System.getProperty("user.id");
        sourcePath = args[0];
        targetPath = args[1];
    }




}
