package PhotoSort;

import PhotoSort.Exceptions.LaunchException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PhotoSortLauncher {
    private DBConnector dbConnector;
    private Properties properties;

    public PhotoSortLauncher(String[] args) throws LaunchException {
        if (args.length < 2) throw new LaunchException("Not enough parameters");
        if (!FSConnector.checkForExist(args[0]))
           throw new LaunchException("Source path is failed");
        properties = Properties.getInstance();
        properties.setProperties(args);


    }

    public void start() throws SQLException, IOException {

        dbConnector = new DBConnector(properties.getResources()
                + properties.getPathSeparator()
                + "DB");
        dbConnector.init();

        FSConnector.walkFileTree(properties.getSourcePath(), dbConnector);
        FSConnector.moveFiles(dbConnector.getDataMap(), properties.getTargetPath());
        dbConnector.printTable();

    }


}
