package user;

/**
 * Provides default implementation of shared user fields.
 * 
 * @author Josh Hammer
 * @author Keith Lueneburg (minor changes)
 * 
 * @version 3/2/2014
 * 
 */
public abstract class AbstractUser implements User {

  /**
   * the username that the user will use.
   */
  private String my_username;

  /**
   * the password the user shall use.
   */
  private String my_password;

  /**
   * the first name of the user.
   */
  private String my_first_name;

  /**
   * the last name of the user.
   */
  private String my_last_name;

  /**
   * A constructor that initializes the fields of the user.
   * 
   * @param the_username
   *          a username
   * @param the_password
   *          a password
   * @param the_first_name
   *          a first name
   * @param the_last_name
   *          a last name
   */
  public AbstractUser(final String the_username, final String the_password,
      final String the_first_name, final String the_last_name) {
    my_username = the_username;
    my_password = the_password;
    my_first_name = the_first_name;
    my_last_name = the_last_name;
  }

  /**
   * Returns the user's first name.
   * 
   * @return the first name.
   * 
   * @author Keith Lueneburg
   */
  public String getFirstName() {
    return my_first_name;
  }

  /**
   * Returns the user's last name.
   * 
   * @return the last name.
   * 
   * @author Keith Lueneburg
   */
  public String getLastName() {
    return my_last_name;
  }

  /**
   * Returns the user's username.
   * 
   * @return the username.
   * 
   * @author Keith Lueneubrg
   */
  public String getUsername() {
    return my_username;
  }
  
  /**
   * Returns the user's password.
   * 
   * @return my_password.
   */
  public String getPassword() {
    return my_password;
  }
}
