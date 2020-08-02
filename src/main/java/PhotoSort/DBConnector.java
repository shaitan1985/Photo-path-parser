package PhotoSort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    /*
       Execute taskes for DataBase.
       write info about files in DB with hash,
       looking for similar hash in db
        */
    private String dbUser = "temp";
    private String dbPass = "temp";


    public Connection getNewConnection(String path) throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:" + path, dbUser, dbPass);
    }


}
