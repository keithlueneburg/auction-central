package user;

import auction.Auction;

/**
 * Represents a nonprofit user
 * 
 * @author Josh Hammer
 *
 */
public class NonProfitUser extends AbstractUser{

	/**
	 * The organization of the nonprofit user
	 */
	private String myOrganization;
	
	/**
	 * The Auction of the nonprofit user
	 */
	private Auction myAuction;
	
	/**
	 * Initializes the nonprofituser
	 * 
	 * @param the_username the intended username
	 * @param the_password the intended password
	 * @param the_first_name the intended first name
	 * @param the_last_name the intended last name
	 * @param the_organization the intended organization
	 * @param the_auction the intended auction
	 */
	public NonProfitUser(String the_username, String the_password, String the_first_name, String the_last_name,
			String the_organization, Auction the_auction){
		super(the_username, the_password, the_first_name, the_last_name);
		myOrganization = the_organization;
		myAuction = the_auction;
	}
	
}
