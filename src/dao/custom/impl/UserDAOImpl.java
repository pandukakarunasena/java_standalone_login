package dao.custom.impl;

import util.Constants;
import dao.custom.UserDAO;
import database.Database;
import dto.UserDTO;
import exceptions.UserExistingException;
import exceptions.UserNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser( UserDTO user ){
        PreparedStatement ps = null;
        try {
            String addUserQuery = Constants.INSERT_NEW_USER_QUERY;
            ps = Database.getInstance().getConnection().prepareStatement(addUserQuery);
            UserDTO existingUser = searchUser( user.getUserName(), user.getEmail());

            if (existingUser == null) {
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getAddress());
                ps.setString(4, user.getPassword());
                ps.execute();
            }else {
                throw new UserExistingException(Constants.USER_ALREADY_EXISTS_MESSAGE);
            }


        }catch ( UserExistingException | SQLException e ) {
            e.getMessage();
            return false;

        }finally {

            try {
                ps.close();
            }catch ( SQLException throwable ) {
                throwable.getMessage();
            }
        }
        return true;
    }


    @Override
    public UserDTO searchUser( String userName, String email )  {
        UserDTO user = null;
        PreparedStatement ps = null;
        try {
            String getNewUserByUsernameAndEmailQuery = Constants.GET_USER_QUERY;
            ps = Database.getInstance().getConnection().prepareStatement(getNewUserByUsernameAndEmailQuery);
            ps.setString(1,userName);
            ps.setString( 2, email );
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                user = new UserDTO( rs.getString( Constants.DATABASE_FIELD_USERNAME ),
                                    rs.getString( Constants.DATABASE_FIELD_EMAIL ),
                                    rs.getString( Constants.DATABASE_FIELD_ADDRESS ),
                                    rs.getString( Constants.DATABASE_FIELD_PASSWORD )
                );
            }else {
                throw new UserNotFoundException( Constants.USER_NOT_FOUND_MESSAGE );
            }

        } catch( SQLException | UserNotFoundException e){
            e.getMessage();

        }finally {
            try {
                ps.close();
            } catch ( SQLException throwable ) {
                throwable.getMessage();
            }
        }
        return user;
    }

}
