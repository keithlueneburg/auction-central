package system;

import auction.Auction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class is a tester used to test if the new Auction would added to the AuctionList
 * meet all requirements (Business Rule #1,2,3,4).
 * @author Kaiyuan Shi
 * @version Win. 2014
 */
final class AuctionDateTester {
  
  /**
   * The solution of the tester.
   */
  private static String my_error_message = null;
  
  /**
   * The list of the current Auction list.
   */
  private static List<Auction> my_auction;
  
  /**
   * Default constructor.
   */
  private AuctionDateTester() {
    
  }

  /**
   * This method get the solution of the test.
   * @param an_auction the new auction would be added
   * @param an_auction_list the current auction list
   * @return the solution of the test, null if the new Auction can be added,
   *  error message if the new Auction can not be added
   */
  public static String getSolution(final Auction an_auction,
      final List<Auction> an_auction_list) {
    my_auction = new ArrayList<Auction>(an_auction_list);
    my_error_message = null;
    
    final Calendar today = Calendar.getInstance();
    final Calendar auction_date = an_auction.getAuctionDate();
    
    //business rule #1
    if (my_auction.size() >= 25) { //no more than 25 auctions
      //too much auction
      my_error_message = "No more than 25 auctions may be schedule into the future!";
    }  else if (today.compareTo(auction_date) > 0
        || (today.get(Calendar.YEAR) == auction_date.get(Calendar.YEAR)
        && today.get(Calendar.DAY_OF_YEAR) == auction_date.get(Calendar.DAY_OF_YEAR))) {
      //auction date is before today or is today
      my_error_message = "you can only schedule Auction in the future!";
    } else if (!testIn3Month(today, auction_date)) {
      //business rule #2
      //true if in 3 month
      //false if out of than 3 month
      my_error_message = "An auction may not be scheduled more"
          + " than 3 months from the current date!";
    } else if (test7DayFull(an_auction)) {
      //business rule #3
      //true if 7 day is full
      //false if 7 day is available
      my_error_message = "No more than 5 auctions may be"
          + " scheduled for any 7 day period!";
    } else if (test1DayFull(an_auction)) {
      //business rule #4
      //true if same day is full
      //false if same day is available
      my_error_message = "No more than 2 auctions can be scheduled"
          + " on the same day, and the start time of the second"
          + " can be no earlier than 2 hours after the end time"
          + " of the first!";
    }
    
    return my_error_message;
  }
  
  /**
   * This method test if the new Auction's date is in 3 month after today.
   * @param the_today today's date
   * @param the_auction_date new Auction's date
   * @return true if new Auction's date is in 3 month after today, 
   *  false if new Auction's date is not in 3 month after today. 
   */
  private static boolean testIn3Month(final Calendar the_today,
      final Calendar the_auction_date) {
    boolean is_in_3_month = true;   
    final int today_year = the_today.get(Calendar.YEAR);
    final int today_month = the_today.get(Calendar.MONTH) + 1;
    final int today_day = the_today.get(Calendar.DATE);
    final int auction_year = the_auction_date.get(Calendar.YEAR);
    final int auction_month = the_auction_date.get(Calendar.MONTH) + 1;
    final int auction_day = the_auction_date.get(Calendar.DATE);
    
    if (auction_year > today_year) { 
      
      //year difference
      if (auction_year > (today_year + 1)) { //is next year?
        is_in_3_month = false; //not next year
      } else { //is next year
        if ((auction_month + 12) > (today_month + 2)) { //longer than 2 month?
          if ((auction_month + 12) == (today_month + 3)) { //longer than 2 month, exact 3 month
            if (auction_day > today_day) {
              is_in_3_month = false; //3 month, but longer than the same day of the month
            }
          } else {
            is_in_3_month = false; // longer than 3 month
          }
        }
      }
      
    } else if (auction_month > (today_month + 2)) {
      
      //same year, longer than 2 month
      if (auction_month > (today_month + 2)) { //longer than 2 month
        if (auction_month == (today_month + 3)) { //exact 3 month
          if (auction_day > today_day) {
            is_in_3_month = false; //3 month, but longer than the same day of the month
          }
        } else {
          is_in_3_month = false; // longer than 3 month
        }
      }
      
    } else if (today_month == auction_month && today_day == auction_day) {
      is_in_3_month = false; //same day
    }
    return is_in_3_month;
  }
  
  /**
   * This method test if the new Auction was added, would be more than 5 Auctions
   * in 7 days.
   * @param an_auction the new Auction would added to the AuctionList
   * @return true if more than 5 Auctions in 7 days,
   *  false if no more than 5 Auctions in 7 days
   * <dt><b>Preconditions:</b><dd>
   * The new Auction's date must in 30 days after today
   */
  private static boolean test7DayFull(final Auction an_auction) {
    boolean is_full = false;
    
    //try to add the new Auction
    my_auction.add(an_auction);
    
    for (Auction each: my_auction) {
      
      final int auction_num = calculate7Day(each.getAuctionDate());
      if (auction_num > 5) {
        is_full = true;
        break;
      }
    }
    
    //remove the new Auction
    my_auction.remove(an_auction);
    
    return is_full;
  }
  
  /**
   * This method calculate how many Auctions in the list in 7 days after
   * the parameter's Date.
   * @param an_auction_date a Date that be started
   * @return the number of Auctions in the list in 7 days after
   *  the parameter's Date
   * <dt><b>Preconditions:</b><dd>
   * The date of the parameter's must be one of the date in Auction list.
   */
  private static int calculate7Day(final Calendar an_auction_date) {
    
    int seven_num = 0;
    final int auction_year = an_auction_date.get(Calendar.YEAR);
    final int auction_day = an_auction_date.get(Calendar.DAY_OF_YEAR);
    
    for (Auction each: my_auction) {
      
      final Calendar each_date = each.getAuctionDate();
      final int each_year = each_date.get(Calendar.YEAR);
      final int each_day = each_date.get(Calendar.DAY_OF_YEAR);
     
      if (each_date.compareTo(an_auction_date) >= 0) {
        
        //only calculate Auction after the parameter
        if (auction_year == each_year) {
          if (each_day < (auction_day + 7)) {
            seven_num++;
          }
        } else { //eachYear = auctionYear + 1;
          if ((auction_year % 4 == 0 && auction_year % 100 != 0)
              || auction_year % 400 == 0) { // leap year!
            if ((each_day + 366) < (auction_day + 7)) {
              seven_num++;
            }
          } else {
            if ((each_day + 365) < (auction_day + 7)) {
              seven_num++;
            }
          }
        }
      } 
    }
    return seven_num;
  }
  
  /**
   * This method test if the the new Auction was added, would be more than 2 Auctions
   * in 2 hours.
   * @param an_auction the new Auction would added to the AuctionList
   * @return true if there would be more than 2 Auctions in 2 hours, else false
   * <dt><b>Preconditions:</b><dd>
   * There must be no more than 5 Auctions in 7 days
   */
  private static boolean test1DayFull(final Auction an_auction) {
    boolean is_full = false;
    
    final Calendar auction_date = an_auction.getAuctionDate();
    final int auction_year = auction_date.get(Calendar.YEAR);
    final int auction_day = auction_date.get(Calendar.DAY_OF_YEAR);
    
    Auction another_in_list = null;
    int same_day_num = 0;
    
    for (Auction each: my_auction) {
      
      final Calendar each_date = each.getAuctionDate();
      final int each_year = each_date.get(Calendar.YEAR);
      final int each_day = each_date.get(Calendar.DAY_OF_YEAR);
      
      if (auction_year == each_year && auction_day == each_day) {
        another_in_list = each;
        same_day_num++;
      }
      
    }
    
    if (same_day_num == 0) { 
      return is_full;
    } else if (same_day_num >= 2) {
      is_full = true;
      return is_full;
    } else {
      if (another_in_list.getAuctionDate().compareTo(auction_date) >= 0) {
        is_full = !is2Hour(an_auction, another_in_list);
      } else {
        is_full = !is2Hour(another_in_list, an_auction);
      }
      return is_full;
    }
  }
  
  /**
   * This method test if the second auction begin after at least 2 hours
   * the first auction finish.
   * @param the_before the auction that begin first
   * @param the_after the auction that begin second
   * @return true if the second auction begin after at least 2 hours
   *  the first auction finish, else false
   * <dt><b>Preconditions:</b><dd>
   * The time of the Auction before must before the time of the Auction after
   */
  private static boolean is2Hour(final Auction the_before, final Auction the_after) {
    boolean is_2_hour = true;
    
    final int before_hour = the_before.getAuctionDate().get(Calendar.HOUR_OF_DAY);
    final int after_hour = the_after.getAuctionDate().get(Calendar.HOUR_OF_DAY);
    final int hours = the_before.getAuctionDuration();
    
    if ((before_hour + hours + 2) > after_hour) {
      is_2_hour = false;
    }
    
    return is_2_hour; 
  }
}


