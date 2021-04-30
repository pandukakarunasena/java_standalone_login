package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/test";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "mysql";
    private static Connection con = null;

    //singleton
    private static Database db = null;

    private Database(){
        setConnection();
    }

    public static Database getInstance()  {
        if(db == null){
            db = new Database();
        }
        return db;
    }

    public Connection getConnection(){
        return con;
    }

    private void setConnection() {
        try {
            Class.forName( DRIVER );
            this.con = DriverManager.getConnection( URL, USERNAME, PASSWORD );

        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
    }
}