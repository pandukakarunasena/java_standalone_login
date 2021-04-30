package util;

public final class Constants {

    //mode selection
    public static final int SELECT_ADD_USER_MODE = 1;
    public static final int SELECT_LOGIN_MODE = 2;

    //SQL queries
    public static final String INSERT_NEW_USER_QUERY = "INSERT INTO user(username, email, address, password) VALUES (?,?,?,?)";
    public static final String GET_USER_QUERY = "SELECT * FROM user WHERE user.username=? AND user.email=?";

    //Exception handling messages
    public static final String USER_ALREADY_EXISTS_MESSAGE = "USER ALREADY EXIST";
    public static final String USER_NOT_FOUND_MESSAGE = "USER NOT FOUND";
    public static final String INVALID_INPUT_MESSAGE = "INVALID INPUT";
    public static final String NEW_USER_CREATION_FAILED_MESSAGE = "NEW USER CREATION FAILED";
    public static final String USER_ADDED_SUCCESSFULLY_MESSAGE = "USER ADDED SUCCESSFULLY";
    public static final String INVALID_CREDENTIALS_PROVIDED_MESSAGE = "INVALID CREDENTIALS";

    //database table column names
    public static final String DATABASE_FIELD_USERNAME= "username";
    public static final String DATABASE_FIELD_EMAIL= "email";
    public static final String DATABASE_FIELD_ADDRESS= "address";
    public static final String DATABASE_FIELD_PASSWORD= "password";

    //database configuration file path
    public static final String DATABASE_CONFIG_FILE_PATH = "E:\\spring\\javastandalonelogin\\src\\Utils\\config.properties";
}
