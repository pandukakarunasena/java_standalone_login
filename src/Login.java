import controllers.UserController;

public class Login {

  /** @param args main entry point for the application */
  public static void main(String[] args) {
    UserController userController = new UserController();
    userController.start();
  }
}
