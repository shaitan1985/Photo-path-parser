package PhotoSort;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DBConnector {
    /*
       Execute taskes for DataBase.
       write info about files in DB with hash,
       looking for similar hash in db
        */
    private String dbUser = "temp";
    private String dbPass = "temp";
    private String path;


    public Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:" + path, dbUser, dbPass);
    }

    public void checkConnection() throws SQLException {
        try(Connection con = getNewConnection()){
            System.out.println("connected");
        }
    }

    public void init() throws SQLException {
        checkConnection();
        //dropTable();
        createTables();
    }

    private void executeUpdate(String query) throws SQLException {
        Statement statement = getNewConnection().createStatement();
        statement.executeUpdate(query);
    }

    private ResultSet executeSelect(String query) throws SQLException {
        Statement statement = getNewConnection().createStatement();
        return statement.executeQuery(query);
    }

    private void createTables()throws SQLException{

        String query = "CREATE TABLE IF NOT EXISTS files" +
                "(id IDENTITY NOT NULL PRIMARY KEY " +
                ",path TEXT" +
                ", fDate DATE" +
                ", hash TEXT)";
        executeUpdate(query);

    }

    private void dropTable() throws SQLException{
        String query = "DROP TABLE IF EXISTS files";
        executeUpdate(query);

    }

    public String getPath() {
        return path;
    }

    public DBConnector(String path) {
        this.path = path;
    }

    public boolean checkFileInBase(String filePath){
        return true;
    }

    public void addToDB(String filePath, Date date, String hash){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String query = "INSERT INTO files (path, fDate, hash) " +
                "VALUES (" +
                "'" + filePath +"', " +
                "DATE '" + sf.format(date) + "' , " +
                "'" + hash + "')";
        try {
            executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printTable(){

        HashMap<String, MediaData> map = getDataMap();

        map.entrySet().stream().forEach(s -> System.out.println(s.getValue()));
    }


    public HashMap<String, MediaData> getDataMap() {
        HashMap<String, MediaData> map = new HashMap<>();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sf = new SimpleDateFormat(pattern);

        String query = "SELECT * FROM files";
        try {
            ResultSet rs = executeSelect(query);
            while(rs.next()){
                MediaData mDataa = new MediaData(rs.getInt(1),
                        rs.getString(2),
                        sf.parse(rs.getString(3)),
                        rs.getString(4));
                map.put(rs.getString(4), mDataa);

            }


        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }
}

