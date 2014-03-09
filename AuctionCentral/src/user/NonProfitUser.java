package user;

import auction.Auction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Represents a nonprofit user.
 * 
 * @author Josh Hammer
 * 
 * @version 3/2/2014
 * 
 */
public class NonProfitUser extends AbstractUser {

  /**
   * The organization of the nonprofit user.
   */
  private String my_organization;

  /**
   * The Auction of the nonprofit user.
   */
  private List<Auction> my_auction = new ArrayList<Auction>();

  /**
   * Initializes the nonprofituser.
   * 
   * @param the_username
   *          the intended username
   * @param the_password
   *          the intended password
   * @param the_first_name
   *          the intended first name
   * @param the_last_name
   *          the intended last name
   * @param the_organization
   *          the intended organization
   */
  public NonProfitUser(final String the_username, final String the_password,
      final String the_first_name, final String the_last_name, final String the_organization) {
    super(the_username, the_password, the_first_name, the_last_name);
    my_organization = the_organization;
    //my_auction = the_auction;
  }
  
  /**
   * Get the String representing the user's non-profit organization.
   * 
   * @return The organization.
   */
  public String getOrganization() {
    return my_organization;
  }
  
  /**
   * This method gets all the Auction this nonprofit user holds.
   * @return the auction list of this nonprofit user holds
   */
  public List<Auction> getAuction() {
    return my_auction;
  }

}
