package PhotoSort;

import PhotoSort.Exceptions.LaunchException;

public class PhotoSortLauncher {
    private DBConnector dbConnector;
    private Properties properties;

    public PhotoSortLauncher(String[] args) throws LaunchException {
        if (args.length != 2) throw new LaunchException("Wrong parameters");
        if (FSConnector.checkForExist(args[0]))
            properties = new Properties(args);
        else
            throw new LaunchException("Source path is failed");
    }

    public void start(){

    }


}
