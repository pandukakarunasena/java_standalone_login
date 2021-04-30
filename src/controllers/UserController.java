package controllers;

import util.Constants;
import authentication.IPasswordManager;
import authentication.custom.impl.PasswordManager;
import dao.custom.UserDAO;
import dao.custom.impl.UserDAOImpl;
import exceptions.InvalidCredentialsException;
import exceptions.UserCreationFailedException;
import exceptions.UserInputInvalidException;
import exceptions.UserNotFoundException;
import dto.UserDTO;

import java.util.InputMismatchException;
import java.util.Scanner;


public class UserController {

    public void start(){
        int selection  = selectTheMode();

        try {
            processUserInput( selection );
        } catch ( UserNotFoundException |
                UserInputInvalidException |
                InvalidCredentialsException |
                UserCreationFailedException e ) {
            e.printStackTrace();
        }

    }

    private  int selectTheMode() {
        System.out.print("ADD NEW USER => 1  ++++ LOGIN => 2\nEnter here: ");
        Scanner input = new Scanner( System.in );
        int selection = -1;

        try{
            selection = input.nextInt();
            while(selection != 1  && selection != 2) {
                System.out.println("ENTER 1 OR 2");
                selection = input.nextInt();
            }

        }catch( InputMismatchException ime ){
            ime.getMessage();
        }
        return selection;
    }

    private  void  processUserInput(int selection)
            throws UserInputInvalidException, UserCreationFailedException, UserNotFoundException, InvalidCredentialsException{

        Scanner input = new Scanner( System.in );
        IPasswordManager passwordManager = new PasswordManager();

        if(selection == Constants.SELECT_ADD_USER_MODE){
            //display add user form
            System.out.println("\nADD NEW USER\n");
            System.out.print("USER NAME: ");
            String userName = input.nextLine();
            System.out.print("EMAIL: ");
            String email = input.nextLine();
            System.out.print("ADDRESS: ");
            String address = input.nextLine();
            System.out.print("PASSWORD: ");
            String password = input.nextLine();

            boolean isValid = validateUserDetails(userName, email, address, password);

            if( !isValid ){
                throw new UserInputInvalidException( Constants.INVALID_INPUT_MESSAGE );
            }

            UserDTO user = new UserDTO(userName, email, address, passwordManager.hashPassword( password ));
            boolean userAdded = addUserToDatabase(user);

            if(!userAdded){
                throw new UserCreationFailedException( Constants.NEW_USER_CREATION_FAILED_MESSAGE );
            }

            System.out.println( Constants.USER_ADDED_SUCCESSFULLY_MESSAGE );

        }else if( selection == Constants.SELECT_LOGIN_MODE ){
            //display Login
            System.out.println("LOGIN\n");
            System.out.print("USER NAME: ");
            String userName = input.nextLine();
            System.out.print("EMAIL: ");
            String email = input.nextLine();
            System.out.print("PASSWORD: ");
            String password = input.nextLine();

            boolean isValid = validateUserDetails( userName, email, password );

            if( !isValid ){
                throw new UserInputInvalidException( Constants.INVALID_INPUT_MESSAGE );
            }

            UserDTO existingUser = getUserFromDatabase( userName, email);

            if( existingUser == null ){
                throw new UserNotFoundException( Constants.USER_NOT_FOUND_MESSAGE );
            }

            boolean passwordMatches = passwordManager.compare( password, existingUser.getPassword());

            if( !passwordMatches ){
                throw new InvalidCredentialsException( Constants.INVALID_CREDENTIALS_PROVIDED_MESSAGE );
            }

            displayUserData( existingUser );
        }
    }

    private  boolean validateUserDetails( String... userDetails ){
        for( String userDetail: userDetails ){
            if( userDetail.isEmpty() ){
                return false;
            }
        }
        return true;
    }

    private  boolean addUserToDatabase( UserDTO user ){
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.addUser( user );
    }

    private  UserDTO  getUserFromDatabase( String userName,String email ){
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.searchUser( userName,email );
    }

    private  void displayUserData( UserDTO user ){

        System.out.println("\nYour are Logged in as: ");
        System.out.print("User Name: ");
        System.out.println( user.getUserName() );

        System.out.print("Email: ");
        System.out.println( user.getEmail() );

        System.out.print("Address: ");
        System.out.println( user.getAddress() );
    }
}
