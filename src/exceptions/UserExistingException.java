package exceptions;

public class UserExistingException extends Exception{
    public UserExistingException(String message){
        super(message);
    }
}
