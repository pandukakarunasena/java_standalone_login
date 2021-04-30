package authentication.custom.impl;

import org.mindrot.jbcrypt.BCrypt;

import authentication.IPasswordManager;

/**
 * Class for hashing and comparing the hashed passwords
 */
public class PasswordManager implements IPasswordManager{

  /**
   * @param passwordToHash password needed to be hashed to store
   * @return hashed version of the entered password
   */
  @Override
  public String hashPassword(String passwordToHash) {
        return  BCrypt.hashpw( passwordToHash, BCrypt.gensalt(12) );
    }

  /**
   * @param passwordToCheck user entered password
   * @param hashedPassword database stored password
   * @return boolean value after checking the params
   */
  @Override
  public boolean compare(String passwordToCheck, String hashedPassword) {
        return  BCrypt.checkpw( passwordToCheck, hashedPassword );
    }
}
