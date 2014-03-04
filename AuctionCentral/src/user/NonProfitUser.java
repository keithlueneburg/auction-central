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
   * This method gets all the Auction this nonprofit user holds.
   * @return the auction list of this nonprofit user holds
   */
  public List<Auction> getAuction() {
    refreshAuction();
    return my_auction;
  }
  
  /**
   * This method refresh the auction list, remove all the auctions before or on today.
   * <dt><b>Postconditions:</b><dd>
   * All Auctions in the list are in the future
   */
  private void refreshAuction() {
    final Calendar today = Calendar.getInstance();
    final int today_year = today.get(Calendar.YEAR);
    final int today_month = today.get(Calendar.MONTH) + 1;
    final int today_day = today.get(Calendar.DATE);
    
    for (Auction each: my_auction) {
      final Calendar auction_date = each.getAuctionDate();
      final int auction_year = auction_date.get(Calendar.YEAR);
      final int auction_month = auction_date.get(Calendar.MONTH) + 1;
      final int auction_day = auction_date.get(Calendar.DATE);
      
      if (today.compareTo(auction_date) > 0 || //if the auction is past
          (today_year == auction_year && today_month == auction_month
          && today_day == auction_day)) { // if the auction is on today
        my_auction.remove(each); //remove the past auction
      }
    }
  }

}
