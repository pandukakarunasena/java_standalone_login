package dto;

public class UserDTO {
  private String id;
  private String userName;
  private String email;
  private String address;
  private String password;

  public UserDTO() {}

  public UserDTO(String userName, String email, String password) {
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  public UserDTO(String userName, String email, String address, String password) {
    this.userName = userName;
    this.email = email;
    this.address = address;
    this.password = password;
  }

  public UserDTO(String id, String userName, String email, String address, String password) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.address = address;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserDTO{"
        + "id='"
        + id
        + '\''
        + ", userName='"
        + userName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", address='"
        + address
        + '\''
        + '}';
  }
}
