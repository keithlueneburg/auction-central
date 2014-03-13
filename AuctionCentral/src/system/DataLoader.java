package system;

import auction.Auction;
import auction.Bid;
import auction.Item;
import bidding.Address;
import bidding.CreditCard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import user.AbstractUser;
import user.AuctionCentralStaff;
import user.Bidder;
import user.NonProfitUser;
import user.User;


/**
 * This class load the data txt files to the system.
 * @author kaiyuan Shi
 * @version Win. 2014
 */
final class DataLoader {
  
  /** Number 3. */
  private static final int THREE = 3;
  
  /** Number 4. */
  private static final int FOUR = 4;
  
  /** Number 5. */
  private static final int FIVE = 5;
  
  /** Number 6. */
  private static final int SIX = 6;
  
  /** Number 7. */
  private static final int SEVEN = 7;
  
  /** Number 8. */
  private static final int EIGHT = 8;
  
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
   * The user.txt scanner.
   */
  private static Scanner my_user_scanner;
  
  /**
   * The auction.txt scanner.
   */
  private static Scanner my_auction_scanner;
  
  /**
   * The item.txt scanner.
   */
  private static Scanner my_item_scanner;
  
  /**
   * The bid.txt scanner.
   */
  private static Scanner my_bid_scanner;
  
  /**
   * The address.txt scanner.
   */
  private static Scanner my_address_scanner;
  
  /**
   * The card.txt scanner.
   */
  private static Scanner my_card_scanner;
  
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
  private DataLoader() { }
  
  /**
   * The static method that be called to read the data from txt files to the system.
   * @param a_system the system that would be loaded
   */
  public static void loadData(final AuctionCentralSystem a_system) throws IOException {
    
    try {
      my_user_scanner = new Scanner(new FileInputStream(DATA_LOCATION + "user.txt"));
      my_auction_scanner = new Scanner(new FileInputStream(DATA_LOCATION + "auction.txt"));
      my_item_scanner = new Scanner(new FileInputStream(DATA_LOCATION + "item.txt"));
      my_bid_scanner = new Scanner(new FileInputStream(DATA_LOCATION + "bid.txt"));
      my_card_scanner = new Scanner(new FileInputStream(DATA_LOCATION + "card.txt"));
      my_address_scanner = new Scanner(new FileInputStream(DATA_LOCATION + "address.txt"));
      
      loadAddressList();
      loadCardList();
      loadBidList();
      loadItemList();
      loadAuctionList();
      loadUserList();
      
    } catch (final FileNotFoundException ex) {
      showError();
    } catch (final NullPointerException ex) {
      showError();
    } catch (final NumberFormatException ex) {
      showError();
    } catch (final ArrayIndexOutOfBoundsException ex) {
      showError();
    } catch (final Exception ex) {
      showError();
    }
    
    
    
    my_user_scanner.close();
    my_auction_scanner.close();
    my_item_scanner.close();
    my_bid_scanner.close();
    my_address_scanner.close();
    my_card_scanner.close();
    
    a_system.loadUser(my_user_list);
    a_system.loadAuction(my_auction_list);
    
  }
    
  private static void showError() throws IOException {
    JOptionPane.showMessageDialog(null,
        "Input file damaged, loading failure!", "error", JOptionPane.ERROR_MESSAGE);
    throw new IOException();
  }
  
  /**
   * This method load the address list from an address.txt file.
   */
  private static void loadAddressList() throws IOException {
    while (my_address_scanner.hasNextLine()) {
      final String line = my_address_scanner.nextLine();
      final String[] address = line.split(DATA_SEPARATOR);
      
      my_address_list.add(new Address(address[0],
          Integer.parseInt(address[1]), address[2],
          address[THREE], Integer.parseInt(address[FOUR])));     
    }
  }
  
  /**
   * This method load the credit card list from an card.txt file.
   */
  private static void loadCardList() throws IOException {
    
    while (my_card_scanner.hasNextLine()) {
      final String line = my_card_scanner.nextLine();
      final String[] card = line.split(DATA_SEPARATOR);
      
      final String[] exp_string = card[1].split(CALENDAR_SEPARATOR);
      final Calendar exp_date = Calendar.getInstance();
      exp_date.set(Calendar.MONTH, Integer.parseInt(exp_string[0]));
      exp_date.set(Calendar.YEAR, Integer.parseInt(exp_string[1]));
      
      my_card_list.add(new CreditCard(Long.parseLong(card[0]),
          exp_date, Integer.parseInt(card[2]), card[THREE], 
          my_address_list.get(Integer.parseInt(card[FOUR])), card[FIVE]));
    }
  }
  
  /**
   * This method load the bid list from an bid.txt file.
   */
  private static void loadBidList() throws IOException {
    while (my_bid_scanner.hasNextLine()) {
      final String line = my_bid_scanner.nextLine();
      final String[] bid = line.split(DATA_SEPARATOR);
      
      final String[] bid_string = bid[THREE].split(CALENDAR_SEPARATOR);
      final Calendar bid_date = Calendar.getInstance();
      bid_date.set(Integer.parseInt(bid_string[2]),
          Integer.parseInt(bid_string[0]), Integer.parseInt(bid_string[1]),
          Integer.parseInt(bid_string[THREE]), Integer.parseInt(bid_string[FOUR]));
      
      my_bid_list.add(new Bid(bid[0], Double.parseDouble(bid[1]),
          bid[2], bid_date, my_card_list.get(Integer.parseInt(bid[FOUR]))));
    }
  }
  
  /**
   * This method load the item list from an item.txt file.
   */
  private static void loadItemList() throws IOException {
    while (my_item_scanner.hasNextLine()) {
      final String line = my_item_scanner.nextLine();
      final String[] item = line.split(DATA_SEPARATOR);
      
      if (" ".equals(item[EIGHT])) {
        item[EIGHT] = "";
      }
      
      final Item this_item = new Item(Integer.parseInt(item[0]), item[1],
          Integer.parseInt(item[2]), Double.parseDouble(item[THREE]),
          item[FOUR], item[FIVE], item[SIX], item[SEVEN], item[EIGHT]);
      
      this_item.setSellingPrice(Double.parseDouble(item[9]));
      
      for (int i = 10; i < item.length; i++) {
        this_item.addBid(my_bid_list.get(Integer.parseInt(item[i])));
      }
      
      my_item_list.add(this_item);   
    }
  }
  
  /**
   * This method load the auction list from an adauctiondress.txt file.
   */
  private static void loadAuctionList() throws IOException {
    while (my_auction_scanner.hasNextLine()) {
      final String line = my_auction_scanner.nextLine();
      final String[] auction = line.split(DATA_SEPARATOR);
      
      final String[] auction_date_string = auction[FOUR].split(CALENDAR_SEPARATOR);
      final Calendar auction_date = Calendar.getInstance();
      auction_date.set(Calendar.MONTH, Integer.parseInt(auction_date_string[0]));
      auction_date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(auction_date_string[1]));
      auction_date.set(Calendar.YEAR, Integer.parseInt(auction_date_string[2]));
      auction_date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(auction_date_string[THREE]));
      auction_date.set(Calendar.MINUTE, 0);
      auction_date.set(Calendar.SECOND, 0);
      
      if (" ".equals(auction[SIX])) {
        auction[SIX] = "";
      }
      
      final Auction current_auction = new Auction(auction[0], auction[1],
          auction[2], auction[THREE], auction_date,
          Integer.parseInt(auction[FIVE]), auction[SIX]);
      
      final List<Item> auction_bids = new ArrayList<Item>();
      for (int i = SEVEN; i < auction.length; i++) {
        auction_bids.add(my_item_list.get(Integer.parseInt(auction[i])));
      }
      
      current_auction.setItems(auction_bids);
      
      my_auction_list.add(current_auction);
    }
    
    
  }
  
  /**
   * This method load the user list from an user.txt file.
   */
  private static void loadUserList() throws IOException {
    
    while (my_user_scanner.hasNextLine()) {
      final String line = my_user_scanner.nextLine();
      final String[] user = line.split(DATA_SEPARATOR);
      final String username = user[1];
      final String password = user[2];
      final String first_name = user[THREE];
      final String last_name = user[FOUR];
      
      if ("Bidder".equals(user[0])) {   
        final Bidder this_bidder = new Bidder(username, password, first_name, 
            last_name);
        
        if ("true".equals(user[FIVE])) {
     
          final CreditCard card = my_card_list.get(Integer.parseInt(user[SIX]));
          final Calendar exp_date = card.getExpDate();
          
          if (exp_date.compareTo(Calendar.getInstance()) <= 0) {
            this_bidder.cardExpired();
          } else {
            this_bidder.regisiter(card, my_address_list.get(Integer.parseInt(user[7])));

            this_bidder.regisiter(card, my_address_list.get(Integer.parseInt(user[SEVEN])));
            
          }
        }
          
          
        for (int i = 8; i < user.length; i++) {
          this_bidder.addBid(my_bid_list.get(Integer.parseInt(user[i])));
        }
         
        
        my_user_list.add(this_bidder); 
        
      } else if ("AuctionCentralStaff".equals(user[0])) {
        my_user_list.add(new AuctionCentralStaff(username, password, 
            first_name, last_name));
      } else {
        my_user_list.add(new NonProfitUser(username, password, 
            first_name, last_name, user[FIVE]));
      }   
    }
  }
  
  
  
}