package exceptions;

public class UserCreationFailedException extends Exception {
  public UserCreationFailedException(String message) {
    super(message);
  }
}
