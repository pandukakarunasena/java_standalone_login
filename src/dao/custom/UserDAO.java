package dao.custom;

import dto.UserDTO;

public interface UserDAO {
  boolean addUser(UserDTO user);

  UserDTO searchUser(String userName, String email);
}
