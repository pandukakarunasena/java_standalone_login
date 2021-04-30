package authentication.custom.impl;

import org.mindrot.jbcrypt.BCrypt;
import authentication.IPasswordManager;

public class PasswordManager implements IPasswordManager{

    @Override
    public  String hashPassword(String passwordToHash){
        return  BCrypt.hashpw(passwordToHash, org.mindrot.jbcrypt.BCrypt.gensalt(12));

    }

    @Override
    public  boolean compare(String plainPassword, String hashedPassword){
        return  BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
