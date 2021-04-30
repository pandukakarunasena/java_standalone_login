package authentication;

public interface IPasswordManager {
    String hashPassword(String passwordToHash);
    boolean compare(String passwordToCheck, String hashedPassword);
}
