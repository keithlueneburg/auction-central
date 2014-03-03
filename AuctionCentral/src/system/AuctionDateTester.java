package system;

import java.util.Calendar;
import java.util.List;

import auction.Auction;

/**
 * This class is a tester used to test if the new Auction would added to the AuctionList
 * meet all requirements (Business Rule #1,2,3,4).
 * @author Kaiyuan Shi
 * @version Win. 2014
 */
class AuctionDateTester {
  
  /**
   * The solution of the tester.
   */
  private boolean mySolution = true;
  
  /**
   * The list of the current Auction list.
   */
  private List<Auction> myAuction;
  
  /**
   * The constructor of the tester
   * @param anAuction the new Auction would added to the AuctionList
   * @param anAuctionList current Auction list
   * <dt><b>Preconditions:</b><dd>
   * The Auction != null
   * The user of adding this auction is a non-profit user
   */
  public AuctionDateTester(Auction anAuction, List<Auction> anAuctionList) {
    
    myAuction = anAuctionList;
    
    Calendar today = Calendar.getInstance();
    Calendar auctionDate = anAuction.getAuctionDate();
    
    //business rule #1
    if (myAuction.size() > 25) {
      mySolution = false; //too much auction
    }  else if (today.compareTo(auctionDate) > 0 ||
        (today.get(Calendar.YEAR) == auctionDate.get(Calendar.YEAR) &&
            today.get(Calendar.DAY_OF_YEAR) == auctionDate.get(Calendar.DAY_OF_YEAR))) {
      mySolution = false; //auction date is before today or is today
    } else if (!testIn3Month(today, auctionDate)) {
      //business rule #2
      //true if in 3 month
      //false if out of than 3 month
      mySolution = false;
    } else if (test7DayFull(anAuction)) {
      //business rule #3
      //true if 7 day is full
      //false if 7 day is available
      mySolution = false;
    } else if (test1DayFull(anAuction)) {
      //business rule #4
      //true if same day is full
      //false if same day is available
      mySolution = false;
    }
  }
  
  /**
   * This method get the solution of the test.
   * @return the solution of the test, true if the new Auction can be added,
   *  false if the new Auction can not be added
   */
  public boolean getSolution() {
    return mySolution;
  }
  
  /**
   * This method test if the new Auction's date is in 3 month after today.
   * @param today today's date
   * @param auctionDate new Auction's date
   * @return true if new Auction's date is in 3 month after today, 
   *  false if new Auction's date is not in 3 month after today. 
   */
  private boolean testIn3Month(Calendar today, Calendar auctionDate) {
    boolean isIn3Month = true;   
    int todayYear = today.get(Calendar.YEAR);
    int todayMonth = today.get(Calendar.MONTH) + 1;
    int todayDay = today.get(Calendar.DATE);
    int auctionYear = auctionDate.get(Calendar.YEAR);
    int auctionMonth = auctionDate.get(Calendar.MONTH) + 1;
    int auctionDay = auctionDate.get(Calendar.DATE);
    
    if (auctionYear > todayYear) { 
      
      //year difference
      if (auctionYear > (todayYear + 1)) { //is next year?
        isIn3Month = false; //not next year
      } else { //is next year
        if ((auctionMonth + 12) > (todayMonth + 2)) { //longer than 2 month?
          if ((auctionMonth + 12) == (todayMonth + 3)) { //longer than 2 month, exact 3 month
            if (auctionDay > todayDay) {
              isIn3Month = false; //3 month, but longer than the same day of the month
            }
          } else {
            isIn3Month = false; // longer than 3 month
          }
        }
      }
      
    } else if (auctionMonth > (todayMonth + 2)) {
      
      //same year, longer than 2 month
      if (auctionMonth > (todayMonth + 2)) { //longer than 2 month
        if (auctionMonth == (todayMonth + 3)) { //exact 3 month
          if (auctionDay > todayDay) {
            isIn3Month = false; //3 month, but longer than the same day of the month
          }
        } else {
          isIn3Month = false; // longer than 3 month
        }
      }
      
    } else if (todayMonth == auctionMonth && todayDay == auctionDay) {
      isIn3Month = false; //same day
    }
    return isIn3Month;
  }
  
  /**
   * This method test if the new Auction was added, would be more than 5 Auctions
   * in 7 days.
   * @param anAuction the new Auction would added to the AuctionList
   * @return true if more than 5 Auctions in 7 days,
   *  false if no more than 5 Auctions in 7 days
   * <dt><b>Preconditions:</b><dd>
   * The new Auction's date must in 30 days after today
   */
  private boolean test7DayFull(Auction anAuction) {
    boolean isFull = false;
    
    //try to add the new Auction
    myAuction.add(anAuction);
    
    for (Auction each: myAuction) {
      
      int auctionNum = calculate7Day(each.getAuctionDate());
      if (auctionNum > 5) {
        isFull = true;
        break;
      }
    }
    
    //remove the new Auction
    myAuction.remove(anAuction);
    
    return isFull;
  }
  
  /**
   * This method calculate how many Auctions in the list in 7 days after
   * the parameter's Date
   * @param auctionDate a Date that be started
   * @return the number of Auctions in the list in 7 days after
   *  the parameter's Date
   * <dt><b>Preconditions:</b><dd>
   * The date of the parameter's must be one of the date in Auction list.
   */
  private int calculate7Day(Calendar auctionDate) {
    
    int sevenNum = 0;
    int auctionYear = auctionDate.get(Calendar.YEAR);
    int auctionDay = auctionDate.get(Calendar.DAY_OF_YEAR);
    
    for (Auction each: myAuction) {
      
      Calendar eachDate = each.getAuctionDate();
      int eachYear = eachDate.get(Calendar.YEAR);
      int eachDay = eachDate.get(Calendar.DAY_OF_YEAR);
     
      if (eachDate.compareTo(auctionDate) >= 0) {
        
        //only calculate Auction after the parameter
        if (auctionYear == eachYear) {
          if (eachDay < (auctionDay + 7))
            sevenNum ++;
        } else { //eachYear = auctionYear + 1;
          if ((auctionYear % 4 == 0 && auctionYear % 100 != 0) ||
              auctionYear % 400 == 0) { // leap year!
            if ((eachDay + 366) < (auctionDay + 7))
              sevenNum ++;
          } else {
            if ((eachDay + 365) < (auctionDay + 7))
              sevenNum ++;
          }
        }
      } 
    }
    return sevenNum;
  }
  
  /**
   * This method test if the the new Auction was added, would be more than 2 Auctions
   * in 2 hours.
   * @param auctionDate anAuction the new Auction would added to the AuctionList
   * @return true if there would be more than 2 Auctions in 2 hours, else false
   * <dt><b>Preconditions:</b><dd>
   * There must be no more than 5 Auctions in 7 days
   */
  private boolean test1DayFull(Auction anAuction) {
    boolean isFull = false;
    
    Calendar auctionDate = anAuction.getAuctionDate();
    int auctionYear = auctionDate.get(Calendar.YEAR);
    int auctionDay = auctionDate.get(Calendar.DAY_OF_YEAR);
    
    Auction anotherInList = null;
    int sameDayNum = 0;
    
    for (Auction each: myAuction) {
      
      Calendar eachDate = each.getAuctionDate();
      int eachYear = eachDate.get(Calendar.YEAR);
      int eachDay = eachDate.get(Calendar.DAY_OF_YEAR);
      
      if (auctionYear == eachYear && auctionDay == eachDay) {
        anotherInList = each;
        sameDayNum ++;
      }
      
    }
    
    if (sameDayNum == 0) { 
      return isFull;
    } else if (sameDayNum >= 2) {
      isFull = true;
      return isFull;
    } else {
      if (anotherInList.getAuctionDate().compareTo(auctionDate) >= 0)
        isFull = !is2Hour(anAuction, anotherInList);
      else
        isFull = !is2Hour(anotherInList, anAuction);
      
      return isFull;
    }
  }
  
  /**
   * This method test if the second auction begin after at least 2 hours
   * the first auction finish.
   * @param before the auction that begin first
   * @param after the auction that begin second
   * @return true if the second auction begin after at least 2 hours
   *  the first auction finish, else false
   * <dt><b>Preconditions:</b><dd>
   * The time of the Auction before must before the time of the Auction after
   */
  private boolean is2Hour(Auction before, Auction after) {
    boolean is2Hour = true;
    
    int beforeHour = before.getAuctionDate().get(Calendar.HOUR_OF_DAY);
    int afterHour = after.getAuctionDate().get(Calendar.HOUR_OF_DAY);
    int hours = before.getAuctionDuration();
    
    if ((beforeHour + hours + 2) > afterHour) {
      is2Hour = false;
    }
    
    return is2Hour; 
  }
}


