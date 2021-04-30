package dao.custom.impl;

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
            String addUserQuery = "INSERT INTO user(username, email, address, password) VALUES (?,?,?,?)";
            ps = Database.getInstance().getConnection().prepareStatement(addUserQuery);
            UserDTO existingUser = searchUser( user.getUserName(), user.getEmail());

            if (existingUser == null) {
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getAddress());
                ps.setString(4, user.getPassword());
                ps.execute();
            }else {
                throw new UserExistingException("USER ALREADY EXIST");
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
            String checkUserQuery = "SELECT * FROM user WHERE user.username=? AND user.email=?";
            ps = Database.getInstance().getConnection().prepareStatement(checkUserQuery);
            ps.setString(1,userName);
            ps.setString( 2, email );
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                user = new UserDTO( rs.getString("username"),
                                    rs.getString("email"),
                                    rs.getString("address"),
                                    rs.getString( "password" )
                );
            }else {
                throw new UserNotFoundException( "USER NOT FOUND" );
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
