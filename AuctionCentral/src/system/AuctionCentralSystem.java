package system;

import auction.Auction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




import user.AbstractUser;
import user.NonProfitUser;

/**
 * This class is the core system of the Auction Central System.
 * @author Kaiyuan Shi
 * @version Win. 2014
 */
public class AuctionCentralSystem {
  
  /**
   * The list of users in the system.
   */
  private List<AbstractUser> my_users;
  
  /**
   * The current user who use the system.
   */
  private AbstractUser my_current_user;
  
  /**
   * A list of current user in the system.
   */
  private List<Auction> my_auction;
  
  /**
   * The constructor, initialized the system.
   * in check-in5 manually set the current user is a non-profit user
   */
  public AuctionCentralSystem() {
    
    my_auction = new ArrayList<Auction>();
    my_users = new ArrayList<AbstractUser>();
    
    //*****************************
    //for demo and test
    final List<Calendar> demo_calendar = new ArrayList<Calendar>();
    for (int i = 0; i < 15; i++) {
      
      //add 15 non-profit users
      my_users.add(new NonProfitUser("username" + i, "password" + i, "first name" + i,
          "last name" + i, "non-profit" + i));
      
      //add 15 auctions form 2014-4-1, 3, 5, 7, 9......29
      //from 9:00 everyday, duration 2 hours
      final Calendar current = Calendar.getInstance();
      current.set(2014, 3, 1 + (i * 2), 9, 0);
      demo_calendar.add(current);
      
      final Auction demo_auction = new Auction("auction name" + i, "contact person" + i,
          "phone number" + i, "intake person" + i, demo_calendar.get(i), 2, "comments" + i);
      
      my_auction.add(demo_auction);
    }
    
    my_current_user = my_users.get(1);
    //for demo and test
    //*****************************
    
    
  }
  
  /**
   * This method get the List of the current Auction.
   * @return a list of the current Auction
   * <dt><b>Postconditions:</b><dd>
   * All of the past auction would be removed.
   */
  public List<Auction> getAuctionList() {
    refreshAuction();
    return my_auction;
  }
   
  /**
   * This method add a new Auction to the auction list.
   * @param an_auction the new auction would be added to the list
   * @return null if the new auction was successfully added, else the error message
   */
  public String addAuction(final Auction an_auction) {
    String error_message = null;
    
    if (an_auction == null) {
      error_message = "input Auction is null!";
      return error_message;
    } else if (!(my_current_user instanceof NonProfitUser)) {
      error_message = "current user is not a non-profit user!"; // not right user
    }
    
    refreshAuction();
    
    //Test Business Rule #1, 2, 3, 4
    error_message = AuctionDateTester.getSolution(an_auction, my_auction);
    
    //Test Business Rule #5
    final List<Auction> current_user_list = ((NonProfitUser) my_current_user).getAuction();
    for (Auction each: current_user_list) {
      if (each.getAuctionDate().get(Calendar.YEAR)
          == an_auction.getAuctionDate().get(Calendar.YEAR)) {
        error_message = "No more than one auction per year"
            + " per Non-profit organization can be scheduled"; //two auction in same year
      }
    }
    
    if (error_message == null) {
      my_auction.add(an_auction);
      ((NonProfitUser) my_current_user).getAuction().add(an_auction);
    }
    
    
    return error_message;
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
