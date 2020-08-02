package PhotoSort;

import PhotoSort.Exceptions.FSException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class FSConnector {
    /*
    Execute all tasks fo filesystem.
    parse, move, delete
     */
    public static boolean checkForExist(String filePath){
        return Files.exists(Paths.get(filePath));
    }

    public static void createPath(String path) throws FSException {
        try {
            Files.createDirectory(Paths.get(path));
        } catch (IOException e) {
            throw new FSException(e.getMessage());
        }

    }





}
