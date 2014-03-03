package system;

import java.util.Calendar;
import java.util.List;

import auction.Auction;
import user.AbstractUser;
import user.NonProfitUser;

public class AuctionCentralSystem {
  private List<AbstractUser> myUsers;
  private AbstractUser myCurrentUser;
  private List<Auction> myAuction;
   
  /**
   * This method add a new Auction to the auction list
   * @param anAuction the new auction would be added to the list
   * @return true if the new auction was successfully added, else false
   */
  public boolean addAuction(Auction anAuction) {
    boolean isSuccess = true;
    
    if (anAuction == null) {
      isSuccess = false;
      return isSuccess;
    } else if(!(myCurrentUser instanceof NonProfitUser)) {
      isSuccess = false; // not right user
    }
    
    refreshAuction();
    
    //Test Business Rule #1, 2, 3, 4
    AuctionDateTester tester = new AuctionDateTester(anAuction, myAuction);
    isSuccess = tester.getSolution();
    
    //Test Business Rule #5
    if (((NonProfitUser) myCurrentUser).getAuction().getAuctionDate().get(Calendar.YEAR) ==
        anAuction.getAuctionDate().get(Calendar.YEAR)) {
      isSuccess = false;//two auction in same year
    }
    
    if (isSuccess) {
      myAuction.add(anAuction);
    }
    
    
    return isSuccess;
  }
  
  /**
   * This method refresh the auction list, remove all the auctions before or on today.
   * <dt><b>Postconditions:</b><dd>
   * All Auctions in the list are in the future
   */
  private void refreshAuction()
  {
    Calendar today = Calendar.getInstance();
    int todayYear = today.get(Calendar.YEAR);
    int todayMonth = today.get(Calendar.MONTH) + 1;
    int todayDay = today.get(Calendar.DATE);
    
    for (Auction each: myAuction) {
      Calendar auctionDate = each.getAuctionDate();
      int auctionYear = auctionDate.get(Calendar.YEAR);
      int auctionMonth = auctionDate.get(Calendar.MONTH) + 1;
      int auctionDay = auctionDate.get(Calendar.DATE);
      
      if (today.compareTo(auctionDate) > 0 || //if the auction is past
          (todayYear == auctionYear && todayMonth == auctionMonth
          && todayDay == auctionDay)) { // if the auction is on today
        myAuction.remove(each); //remove the past auction
      }
    }
  }
  
  
}
