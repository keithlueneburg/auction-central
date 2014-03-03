package user;

//TODO: Possibly remove this class??

/**
 * Represents an administrator of the system
 * 
 * @author Josh
 *
 */
public class Administrator extends AbstractUser{
	
	/**
	 * Initializes the administrator
	 * 
	 * @param the_username the intended username
	 * @param the_password the intended password
	 * @param the_first_name the intended first name
	 * @param the_last_name the intended last name
	 */
	public Administrator(String the_username, String the_password, String the_first_name, String the_last_name){
		super(the_username, the_password, the_first_name, the_last_name);
	}
}
