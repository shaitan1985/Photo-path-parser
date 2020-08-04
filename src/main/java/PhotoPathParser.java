import PhotoSort.Exceptions.LaunchException;
import PhotoSort.PhotoSortLauncher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.SQLException;

public class PhotoPathParser {


    public static void main(String[] args) {

        //incomming args are source path and target path
        /*
        1. parser class and fileVisitor
        2. DB JDBC class connector
         */

        try {
            PhotoSortLauncher photoSortLauncher = new PhotoSortLauncher(args);
            photoSortLauncher.start();
        }
        catch (LaunchException | SQLException | IOException ex){
            System.out.println(ex);
        }

        }
    }

