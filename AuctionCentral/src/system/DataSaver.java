package system;

import auction.Auction;
import auction.Bid;
import auction.Item;
import bidding.Address;
import bidding.CreditCard;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import user.AuctionCentralStaff;
import user.Bidder;
import user.NonProfitUser;
import user.User;

/**
 * This class save all the system data to some txt files.
 * @author Kaiyuan Shi
 * @version Win. 2014
 */
final class DataSaver {

  /**
   * The user list that would be saved.
   */
  private static List<User> my_user_list = new ArrayList<User>();
  
  /**
   * The auction list that would be saved.
   */
  private static List<Auction> my_auction_list = new ArrayList<Auction>();
  
  /**
   * The item list that would be saved.
   */
  private static List<Item> my_item_list = new ArrayList<Item>();
  
  /**
   * The bid list that would be saved.
   */
  private static List<Bid> my_bid_list = new ArrayList<Bid>();
  
  /**
   * The address list that would be saved.
   */
  private static List<Address> my_address_list = new ArrayList<Address>();
  
  /**
   * The card list that would be saved.
   */
  private static List<CreditCard> my_card_list = new ArrayList<CreditCard>();
  
  /**
   * The user.txt writer.
   */
  private static PrintWriter my_user_writer;
  
  /**
   * The auction.txt writer.
   */
  private static PrintWriter my_auction_writer;
  
  /**
   * The item.txt writer.
   */
  private static PrintWriter my_item_writer;
  
  /**
   * The bid.txt writer.
   */
  private static PrintWriter my_bid_writer;
  
  /**
   * The address.txt writer.
   */
  private static PrintWriter my_address_writer;
  
  /**
   * The card.txt writer.
   */
  private static PrintWriter my_card_writer;
  
  /**
   * The separator of all the data.
   */
  private static final String DATA_SEPARATOR = " ` ";
  
  /**
   * The separator of all the calendar data.
   */
  private static final String CALENDAR_SEPARATOR = "/";
  
  /**
   * The location of all the calendar data.
   */
  private static final String DATA_LOCATION = "data/";
  //private static final String DATA_LOCATION = "databackup/";
  
  /**
   * The default constructor that can not be called.
   */
  private DataSaver() { }

  /**
   * The static method that be called to save the data from the system to txt files.
   * @param a_user_list the user list that would be saved
   * @param an_auction_list the auction list that would be saved
   * <dt><b>Preconditions:</b><dd>
   * The data in the system should meet all the business rules.
   * <dt><b>Postconditions:</b><dd>
   * All of the data would be saved into 6 txt files.
   */
  public static void saveData(final List<User> a_user_list,
      final List<Auction> an_auction_list) {
    my_user_list = a_user_list;
    my_auction_list = an_auction_list;
    
    try {
      my_user_writer = new PrintWriter(new FileOutputStream(DATA_LOCATION + "user.txt"));
      my_auction_writer = new PrintWriter(new FileOutputStream(DATA_LOCATION + "auction.txt"));
      my_item_writer = new PrintWriter(new FileOutputStream(DATA_LOCATION + "item.txt"));
      my_bid_writer = new PrintWriter(new FileOutputStream(DATA_LOCATION + "bid.txt"));
      my_card_writer = new PrintWriter(new FileOutputStream(DATA_LOCATION + "card.txt"));
      my_address_writer = new PrintWriter(new FileOutputStream(DATA_LOCATION + "address.txt"));
      
    } catch (final FileNotFoundException ex) {
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
  
  /**
   * This method save the user list to an user.txt file.
   * <dt><b>Preconditions:</b><dd>
   * 
   * <dt><b>Postconditions:</b><dd>
   */
  private static void outputUserLists() {
    for (User each: my_user_list) {
      
      String output = "";
      if (each instanceof Bidder) {
        
        output += "Bidder" + DATA_SEPARATOR;
        output += printUser(each);
        
        if (((Bidder) each).isRegistered()) {
        
          output += true + DATA_SEPARATOR;
          
          //card
          output += my_card_list.size() + DATA_SEPARATOR;
          my_card_list.add(((Bidder) each).getCard());
          
          //address
          output += my_address_list.size() + DATA_SEPARATOR;
          my_address_list.add(((Bidder) each).getAddress());
          
          //bids
          for (Bid each_bid: ((Bidder) each).getBids()) {
            output += my_bid_list.size() + DATA_SEPARATOR;
            my_bid_list.add(each_bid);
          }
        } else {
          output += false + DATA_SEPARATOR;
        }
        
      } else if (each instanceof AuctionCentralStaff) {
        output += "AuctionCentralStaff" + DATA_SEPARATOR;
        output += printUser(each);
      } else {
        output += "NonProfitUser" + DATA_SEPARATOR;
        output += printUser(each);
        output += ((NonProfitUser) each).getOrganization() + DATA_SEPARATOR;
      }
      my_user_writer.println(output);
    }
    
  }
  
  /**
   * This method add the user name, password, first name
   * and last name information to the output string.
   * 
   * @param a_user the user that would be output
   * @return a output string that the user name, password, first name
   *  and last name information have been added
   * <dt><b>Preconditions:</b><dd>
   * <dt><b>Postconditions:</b><dd>
   */
  private static String printUser(final  User a_user) {
    String ret = "";
    final String username = a_user.getUsername();
    final String password = a_user.getPassword();
    final String first_name = a_user.getFirstName();
    final  String last_name = a_user.getLastName();
    ret += username + DATA_SEPARATOR + password + DATA_SEPARATOR;
    ret += first_name + DATA_SEPARATOR + last_name + DATA_SEPARATOR;
    return ret;
  }
  
  /**
   * This method save the auction list to an auction.txt file.
   * <dt><b>Preconditions:</b><dd>
   * <dt><b>Postconditions:</b><dd>
   */
  private static void outputAuctionLists() {
    
    for (Auction each: my_auction_list) {
      String output = "";
      String comments = each.getComments();
      
      output += each.getAuctionName() + DATA_SEPARATOR;
      output += each.getContactPerson() + DATA_SEPARATOR;
      output += each.getContactPhone() + DATA_SEPARATOR;
      output += each.getIntakePerson() + DATA_SEPARATOR;
      
      final Calendar auction_date = each.getAuctionDate();  
      output += auction_date.get(Calendar.MONTH) + CALENDAR_SEPARATOR;
      output += auction_date.get(Calendar.DAY_OF_MONTH) + CALENDAR_SEPARATOR;
      output += auction_date.get(Calendar.YEAR) + CALENDAR_SEPARATOR;
      output += auction_date.get(Calendar.HOUR_OF_DAY) + DATA_SEPARATOR;
      
      output += each.getAuctionDuration() + DATA_SEPARATOR;
      
      if ("".equals(comments)) {
        comments = " ";
      }
      output += comments + DATA_SEPARATOR;
      
      for (Item each_item: each.getItems()) {
        output += my_item_list.size() + DATA_SEPARATOR;
        my_item_list.add(each_item);
      }
      
      
      my_auction_writer.println(output);
    }
    
  }
  
  /**
   * This method save the item list to an item.txt file.
   * <dt><b>Preconditions:</b><dd>
   * <dt><b>Postconditions:</b><dd>
   */
  private static void outputItemLists() {
    
    for (Item each: my_item_list) {
      String output = "";
      String comments = each.getComments();
      
      output += each.getItemNumber() + DATA_SEPARATOR;
      output += each.getItemName() + DATA_SEPARATOR;
      output += each.getItemQuantity() + DATA_SEPARATOR;
      output += each.getStartingBid() + DATA_SEPARATOR;
      output += each.getDonor() + DATA_SEPARATOR;
      output += each.getSize() + DATA_SEPARATOR;
      output += each.getStorage() + DATA_SEPARATOR;
      output += each.getCondition() + DATA_SEPARATOR;
      
      if ("".equals(comments)) {
        comments = " ";
      }
      
      output += comments + DATA_SEPARATOR;
      output += each.getSellingPrice() + DATA_SEPARATOR;
      
      for (Bid each_bid: each.getBids()) {
        output += my_bid_list.size() + DATA_SEPARATOR;
        my_bid_list.add(each_bid);
      }
      
      my_item_writer.println(output);
    }
    
  }
  
  /**
   * This method save the bid list to an bid.txt file.
   * <dt><b>Preconditions:</b><dd>
   * <dt><b>Postconditions:</b><dd>
   */
  private static void outputBidLists() {
    for (Bid each: my_bid_list) {
      String output = "";
      
      output += each.getItemName() + DATA_SEPARATOR;
      output += each.getPrice() + DATA_SEPARATOR;
      output += each.getBidderName() + DATA_SEPARATOR;
      
      final Calendar bid_time = each.getBidTime();
      output += bid_time.get(Calendar.MONTH) + CALENDAR_SEPARATOR;
      output += bid_time.get(Calendar.DAY_OF_MONTH) + CALENDAR_SEPARATOR;
      output += bid_time.get(Calendar.YEAR) + CALENDAR_SEPARATOR;
      output += bid_time.get(Calendar.HOUR_OF_DAY) + CALENDAR_SEPARATOR;
      output += bid_time.get(Calendar.MINUTE) + DATA_SEPARATOR;
      
      output += my_card_list.size() + DATA_SEPARATOR;
      my_card_list.add(each.getPayment());
      
      my_bid_writer.println(output);
    }
  }
  
  /**
   * This method save the credit card list to an card.txt file.
   * <dt><b>Preconditions:</b><dd>
   * <dt><b>Postconditions:</b><dd>
   */
  private static void outputCardLists() {
    
    for (CreditCard each: my_card_list) {
      String output = "";
      
      output += each.getCardNum() + DATA_SEPARATOR;
      
      final Calendar exp_date = each.getExpDate();
      output += exp_date.get(Calendar.MONTH) + CALENDAR_SEPARATOR;
      output += exp_date.get(Calendar.YEAR) + DATA_SEPARATOR;
      
      output += each.getCSC() + DATA_SEPARATOR;
      output += each.getCardHolder() + DATA_SEPARATOR;
      
      output += my_address_list.size() + DATA_SEPARATOR;
      my_address_list.add(each.getAddress());
      
      output += each.getBank() + DATA_SEPARATOR;
      
      my_card_writer.println(output);
    }
    
  }
  
  /**
   * This method save the address list to an address.txt file.
   * <dt><b>Preconditions:</b><dd>
   * <dt><b>Postconditions:</b><dd>
   */
  private static void outputAddressLists() {
    for (Address each: my_address_list) {
      String output = "";
      
      output += each.getMyStreet() + DATA_SEPARATOR;
      output += each.getMyApt() + DATA_SEPARATOR;
      output += each.getMyCity() + DATA_SEPARATOR;
      output += each.getMyState() + DATA_SEPARATOR;
      output += each.getMyZip() + DATA_SEPARATOR;
      
      my_address_writer.println(output);
    }
    
    
  }
  
  
}
