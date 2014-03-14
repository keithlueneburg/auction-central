package user;

/**
 * Represents a guest user of the system.
 * 
 * @author Josh Hammer
 * 
 * @version 3/10/2014
 */
public class Guest extends AbstractUser {

  /**
   * Initializes the administrator.
   * 
   * @param the_username
   *          the intended username
   * @param the_password
   *          the intended password
   * @param the_first_name
   *          the intended first name
   * @param the_last_name
   *          the intended last name
   */
  public Guest(final String the_username, final String the_password,
      final String the_first_name, final String the_last_name) {
    super(the_username, the_password, the_first_name, the_last_name);
  }
}