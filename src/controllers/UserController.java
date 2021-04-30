package controllers;

import authentication.IPasswordManager;
import authentication.custom.impl.PasswordManager;
import dao.custom.UserDAO;
import dao.custom.impl.UserDAOImpl;
import java.util.InputMismatchException;
import java.util.Scanner;
import dto.UserDTO;

public class UserController {
     final int ADD_USER = 1;
     final int LOGIN = 2;

    public void start(){
        int selection  = selectTheMode();
        processUserInput( selection );
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

    private  void  processUserInput(int selection){
        Scanner input = new Scanner( System.in );
        IPasswordManager passwordManager = new PasswordManager();

        if(selection == ADD_USER){
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

            if(!isValid){
                System.out.println("INVALID INPUT");
                return;
            }

            UserDTO user = new UserDTO(userName, email, address, passwordManager.hashPassword( password));
            boolean userAdded = addUserToDatabase(user);

            if(!userAdded){
                System.out.println("NEW USER CREATION FAILED");
                return;
            }

            System.out.println("USER ADDED SUCCESSFULLY");

        }else if(selection == LOGIN){
            //display Login
            System.out.println("LOGIN\n");
            System.out.print("USER NAME: ");
            String userName = input.nextLine();
            System.out.print("EMAIL: ");
            String email = input.nextLine();
            System.out.print("PASSWORD: ");
            String password = input.nextLine();

            boolean isValid = validateUserDetails(userName, email, password);

            if(!isValid){
                System.out.println("INVALID INPUT");
                return;
            }

            UserDTO user = getUserFromDatabase( userName, email);

            if(user == null){
                System.out.println("USER NOT FOUND");
                return;
            }

            boolean passwordMatches = passwordManager.compare( password, user.getPassword());

            if(!passwordMatches){
                System.out.println("INVALID CREDENTIALS");
                return;
            }

            displayUserData( user );
        }
    }

    private  boolean validateUserDetails(String... userDetails){
        for(String userDetail: userDetails){
            if(userDetail.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private  boolean addUserToDatabase(UserDTO user){
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.addUser( user );
    }

    private  UserDTO  getUserFromDatabase(String userName,String email){
        UserDAO userDAO = new UserDAOImpl();
        return userDAO.searchUser( userName,email);
    }

    private  void displayUserData(UserDTO user){
        System.out.println();
        System.out.println("Your are Logged in as: ");
        System.out.print("User Name: ");
        System.out.println(user.getUserName());

        System.out.print("Email: ");
        System.out.println(user.getEmail());

        System.out.print("Address: ");
        System.out.println(user.getAddress());
    }
}
