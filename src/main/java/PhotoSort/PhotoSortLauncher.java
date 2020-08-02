package PhotoSort;

import PhotoSort.Exceptions.LaunchException;

import java.sql.Connection;
import java.sql.SQLException;

public class PhotoSortLauncher {
    private DBConnector dbConnector;
    private Properties properties;

    public PhotoSortLauncher(String[] args) throws LaunchException {
        if (args.length != 2) throw new LaunchException("Wrong parameters");
        if (!FSConnector.checkForExist(args[0]))
           throw new LaunchException("Source path is failed");
        properties = Properties.getInstance();
        properties.setProperties(args);


    }

    public void start() throws SQLException {

        dbConnector = new DBConnector();
        try(Connection con = dbConnector.getNewConnection(properties.getResources()
                + properties.getPathSeparator()
                + "DB")){
            System.out.println("connected");
        }
    }


}
