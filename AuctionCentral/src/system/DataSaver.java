package system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import user.AbstractUser;
import user.AuctionCentralStaff;
import user.Bidder;
import auction.Auction;
import auction.Bid;
import auction.Item;
import bidding.Address;
import bidding.CreditCard;

final class DataSaver {

  private static List<AbstractUser> my_user_list;
  private static List<Auction> my_auction_list;
  private static List<Item> my_item_list;
  private static List<Bid> my_bid_list;
  private static List<Address> my_address_list;
  private static List<CreditCard> my_card_list;
  
  private static PrintWriter my_user_writer = null;
  private static PrintWriter my_auction_writer = null;
  private static PrintWriter my_item_writer = null;
  private static PrintWriter my_bid_writer = null;
  private static PrintWriter my_address_writer = null;
  private static PrintWriter my_card_writer = null;

  public static void saveData(final List<AbstractUser> a_user_list,
      final List<Auction> an_auction_list) {
    my_user_list = a_user_list;
    my_auction_list = an_auction_list;
    
    try {
      my_user_writer = new PrintWriter(new FileOutputStream("output/user.txt"));
      my_auction_writer = new PrintWriter(new FileOutputStream("output/auction.txt"));
      my_item_writer = new PrintWriter(new FileOutputStream("output/item.txt"));
      my_bid_writer = new PrintWriter(new FileOutputStream("output/bid.txt"));
      my_address_writer = new PrintWriter(new FileOutputStream("output/address.txt"));
      my_card_writer = new PrintWriter(new FileOutputStream("output/card.txt"));
    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    }
    
    
    outputUserLists();
    outputAuctionLists();
    outputItemLists();
    outputBidLists();
    outputCardLists();
    outputAddressLists();
    
    my_user_writer.close();
    my_auction_writer.close();
    my_item_writer.close();
    my_bid_writer.close();
    my_address_writer.close();
    my_card_writer.close();
  }
  
  
  private static void outputUserLists() {
    for (AbstractUser each: my_user_list) {
      
      String output = "";
      if (each instanceof Bidder) {
        
        output += "Bidder,";
        
        printUser(output, each, my_user_writer);
        
        //card
        output += "card" + my_card_list.size() + ",";
        my_card_list.add(((Bidder) each).getCard());
        
        //address
        output += "address" + my_address_list.size() + ",";
        my_address_list.add(((Bidder) each).getAddress());
        
        //bids
        for (Bid each_bid: ((Bidder) each).getBids()) {
          output += "bid" + my_bid_list.size() + ",";
          my_bid_list.add(each_bid);
        }
        
      } else if (each instanceof AuctionCentralStaff) {
        output += "AuctionCentralStaff,";
        printUser(output, each, my_user_writer);
      } else {
        output += "NonProfitUser,";
        printUser(output, each, my_user_writer);
      }
      my_user_writer.println(output);
    }
    
    
    
    
    
    
    
  }
  
  /**
   * This merhod print out the basic message of a user
   * @param a_user
   * @param a_writer
   */
  private static void printUser(String a_output, AbstractUser a_user, PrintWriter a_writer) {
    String username = a_user.getUsername();
    String first_name = a_user.getFirstName();
    String last_name = a_user.getLastName();
    a_output += (username+ "," + first_name+ "," + last_name + ",");
  }
  
  private static void outputAuctionLists() {
    
    for (Auction each: my_auction_list) {
      String output = "";
      
      output += each.getAuctionNumber() + ",";
      output += each.getAuctionName() + ",";
      output += each.getContactPerson() + ",";
      output += each.getContactPhone() + ",";
      output += each.getIntakePerson() + ",";
      
      Calendar auction_date = each.getAuctionDate();  
      output += auction_date.get(Calendar.MONTH) + "/";
      output += auction_date.get(Calendar.DAY_OF_MONTH) + "/";
      output += auction_date.get(Calendar.YEAR) + "/";
      output += auction_date.get(Calendar.HOUR_OF_DAY) + ",";
      
      output += each.getAuctionDuration() + ",";
      output += each.getComments() + ",";
      
      for (Item each_item: each.getItems()) {
        output += "item" + my_item_list.size() + ",";
        my_item_list.add(each_item);
      }
      
      
      my_auction_writer.println(output);
    }
    
  }
  private static void outputItemLists() {
    
    for (Item each: my_item_list) {
      String output = "";
      
      output += each.getItemNumber() + ",";
      output += each.getItemName() + ",";
      output += each.getItemQuantity() + ",";
      output += each.getStartingBid() + ",";
      output += each.getDonor() + ",";
      output += each.getSize() + ",";
      output += each.getStorage() + ",";
      output += each.getCondition() + ",";
      output += each.getComments() + ",";
      output += each.getPhotoLocation() + ",";
      output += each.getSellingPrice() + ",";
      
      for (Bid each_bid: each.getBids()) {
        output += "bid" + my_bid_list.size() + ",";
        my_bid_list.add(each_bid);
      }
      
      my_item_writer.println(output);
    }
    
  }
  private static void outputBidLists() {
    
  }
  private static void  outputCardLists(){
    
  }
  private static void outputAddressLists(){
    
  }
  
  
}
