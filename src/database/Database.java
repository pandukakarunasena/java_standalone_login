package database;

import util.Constants;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private static Connection con = null;
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


    /**
     * setting up the database connection by reading the config.properties file
     * assign the connection to this.con
     */
    private void setConnection() {

        File configFile = new File( Constants.DATABASE_CONFIG_FILE_PATH );
        FileReader reader = null;

        try {
            reader = new FileReader( configFile );
            Properties props = new Properties();
            props.load( reader );

            Class.forName( props.getProperty( "driver" ) );

            this.con = DriverManager.getConnection(
                    props.getProperty( "url" ),
                    props.getProperty( "user" ),
                    props.getProperty( "password" )
            );

        } catch ( ClassNotFoundException | SQLException | IOException e ) {
            e.printStackTrace();

        } finally {
            try {
                reader.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }


}