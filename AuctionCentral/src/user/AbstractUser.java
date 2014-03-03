package user;

/**
 * Provides default implementation of shared user fields
 * 
 * @author Josh Hammer
 * @author Keith Lueneburg (minor changes)
 * 
 * @version 3/2/2014
 *
 */
public abstract class AbstractUser implements User {

	/**
	 * the username that the user will use
	 */
	private String myUsername;
	
	/**
	 * the password the user shall use
	 */
	private String myPassword;
	
	/**
	 * the first name of the user
	 */
	private String myFirstName;
	
	/**
	 * the last name of the user
	 */
	private String myLastName;
	
	/**
	 * A constructor that initializes the fields of the user
	 * 
	 * @param the_username a username
	 * @param the_password a password
	 * @param the_first_name a first name
	 * @param the_last_name a last name
	 */
  public AbstractUser(String the_username, String the_password,
      String the_first_name, String the_last_name) {
    myUsername = the_username;
    myPassword = the_password;
    myFirstName = the_first_name;
    myLastName = the_last_name;
  }
	
	
	/**
	 * Returns the user's first name.
	 * 
	 * @return the first name.
	 * 
	 * @author Keith Lueneburg
	 */
  public String getFirstName() {
    return myFirstName;
  }
	
  /**
   * Returns the user's last name.
   * 
   * @return the last name.
   * 
   * @author Keith Lueneburg
   */
  public String getLastName() {
    return myLastName;
  }
  
  /**
   * Returns the user's username.
   * 
   * @return the username.
   * 
   * @author Keith Lueneubrg
   */
  public String getUsername() {
    return myUsername;
  }
}
