package user;

/**
 * Class: User (interface)
 * 
 * Interface to be implemented by the system's different user classes.
 * 
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * 
 * University of Washington, Tacoma
 * 
 * Winter 2014
 * 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Keith Lueneburg
 * @version 3/10/2014
 */
public interface User {

  /**
   * Returns the user's first name.
   * 
   * @return the first name.
   */
  String getFirstName();

  /**
   * Returns the user's last name.
   * 
   * @return the last name.
   */
  String getLastName();
  
  /**
   * Returns the user's username.
   * 
   * @return the username.
   */
  String getUsername();

  /**
   * Returns the user's password.
   * 
   * @return the password.
   */
  String getPassword();
}
