package user;

/**
 * Provides default implementation of shared user fields
 * 
 * @author Josh Hammer
 *
 */
public class AbstractUser {

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
	public AbstractUser(String the_username, String the_password, String the_first_name, String the_last_name){
		myUsername = the_username;
		myPassword = the_password;
		myFirstName = the_first_name;
		myLastName = the_last_name;
	}
}
