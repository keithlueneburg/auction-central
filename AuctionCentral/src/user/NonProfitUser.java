package user;

import auction.Auction;

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
  private Auction my_auction;

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
   * @param the_auction
   *          the intended auction
   */
  public NonProfitUser(final String the_username, final String the_password,
      final String the_first_name, final String the_last_name, final String the_organization,
      final Auction the_auction) {
    super(the_username, the_password, the_first_name, the_last_name);
    my_organization = the_organization;
    my_auction = the_auction;
  }

}
