package system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import bidding.Address;
import bidding.CreditCard;
import user.AbstractUser;
import user.Bidder;
import auction.Auction;
import auction.Bid;
import auction.Item;

/**
 * This class load the data txt files to the system
 * @author kaiyuan Shi
 *
 */
final class DataLoader {
  
  private static List<AbstractUser> my_user_list = new ArrayList<AbstractUser>();
  private static List<Auction> my_auction_list = new ArrayList<Auction>();
  private static List<Item> my_item_list = new ArrayList<Item>();
  private static List<Bid> my_bid_list = new ArrayList<Bid>();
  private static List<Address> my_address_list = new ArrayList<Address>();
  private static List<CreditCard> my_card_list = new ArrayList<CreditCard>();
  
  private static Scanner my_user_scanner = null;
  private static Scanner my_auction_scanner = null;
  private static Scanner my_item_scanner = null;
  private static Scanner my_bid_scanner = null;
  private static Scanner my_address_scanner = null;
  private static Scanner my_card_scanner = null;
  
  /**
   * The separator of all the data.
   */
  private static final String DATA_SEPARATOR = " ` ";
  
  public static void loadData(List<AbstractUser> the_user_list,
      List<Auction> the_auction_list) {
    
    try {
      my_user_scanner = new Scanner(new FileInputStream("data/user.txt"));
      my_auction_scanner = new Scanner(new FileInputStream("data/auction.txt"));
      my_item_scanner = new Scanner(new FileInputStream("data/item.txt"));
      my_bid_scanner = new Scanner(new FileInputStream("data/bid.txt"));
      my_card_scanner = new Scanner(new FileInputStream("data/card.txt"));
      my_address_scanner = new Scanner(new FileInputStream("data/address.txt"));
      
    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    }
    
//    loadAddressList();
//    loadCardList();
//    loadBidList();
//    loadItemList();
//    loadAuctionList();
//    loadUserList();
    
    my_user_scanner.close();
    my_auction_scanner.close();
    my_item_scanner.close();
    my_bid_scanner.close();
    my_address_scanner.close();
    my_card_scanner.close();
    
    the_user_list = my_user_list;
    the_auction_list = my_auction_list;
    
  }
  
  private static void loadAddressList() {
    while (my_address_scanner.hasNextLine()) {
      String line = my_address_scanner.nextLine();
      String[] address = line.split(DATA_SEPARATOR);
      
      my_address_list.add(new Address(address[0],
          Integer.parseInt(address[1]), address[2],
          address[3], Integer.parseInt(address[4])));     
    }
  }
  
  private static void loadCardList() {
    
    while (my_card_scanner.hasNextLine()) {
      String line = my_card_scanner.nextLine();
      String[] card = line.split(DATA_SEPARATOR);
      
      String[] exp_string = card[1].split("/");
      Calendar exp_date = Calendar.getInstance();
      exp_date.set(Calendar.MONTH, Integer.parseInt(exp_string[0]));
      exp_date.set(Calendar.YEAR, Integer.parseInt(exp_string[1]));
      
      my_card_list.add(new CreditCard(Long.parseLong(card[0]),
          exp_date, Integer.parseInt(card[2]), card[3], 
          my_address_list.get(Integer.parseInt(card[4])), card[5]));
    }
  }
  
  private static void loadBidList() {
    while (my_bid_scanner.hasNextLine()) {
      String line = my_bid_scanner.nextLine();
      String[] bid = line.split(DATA_SEPARATOR);
      
      String[] bid_string = bid[3].split("/");
      Calendar bid_date = Calendar.getInstance();
      bid_date.set(Integer.parseInt(bid_string[0]),
          Integer.parseInt(bid_string[2]), Integer.parseInt(bid_string[3]),
          Integer.parseInt(bid_string[4]), Integer.parseInt(bid_string[5]));
      
      my_bid_list.add(new Bid(bid[0], Double.parseDouble(bid[1]),
          bid[2], bid_date, my_card_list.get(Integer.parseInt(bid[4]))));
    }
  }
  
  private static void loadItemList() {
    while (my_item_scanner.hasNextLine()) {
      String line = my_item_scanner.nextLine();
      String[] item = line.split(DATA_SEPARATOR);
      
      Item the_item = new Item(Integer.parseInt(item[0]), item[1],
          Integer.parseInt(item[2]), Double.parseDouble(item[3]),
          item[4], item[5], item[6], item[7], item[8], item[9]);
      
      the_item.setSellingPrice(Double.parseDouble(item[10]));
      
      for (int i = 11; i < item.length; i ++) {
        the_item.addBid(my_bid_list.get(Integer.parseInt(item[i])));
      }
      
      my_item_list.add(the_item);   
    }
    
  }
  
  private static void loadAuctionList() {
    while (my_auction_scanner.hasNextLine()) {
      String line = my_auction_scanner.nextLine();
      String[] auction = line.split(DATA_SEPARATOR);
      
      String[] auction_date_string = auction[4].split("/");
      Calendar auction_date = Calendar.getInstance();
      auction_date.set(Calendar.MONTH, Integer.parseInt(auction_date_string[0]));
      auction_date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(auction_date_string[1]));
      auction_date.set(Calendar.YEAR, Integer.parseInt(auction_date_string[2]));
      auction_date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(auction_date_string[3]));
      
      my_auction_list.add(new Auction(auction[0], auction[1],
          auction[2], auction[3], auction_date,
          Integer.parseInt(auction[5]), auction[6]));  
    }
  }
  
  private static void loadUserList() {
    while (my_user_scanner.hasNextLine()) {
      String line = my_user_scanner.nextLine();
      String[] user = line.split(DATA_SEPARATOR);
      String username = user[1];
      String password = user[2];
      String first_name = user[3];
      String last_name = user[4];
      
      if (user[0].equals("Bidder")) {
        Bidder the_bidder = new Bidder(username, password, first_name, 
            last_name, my_card_list.get(Integer.parseInt(user[5])), 
                my_address_list.get(Integer.parseInt(user[6])));
        
        for (int i = 7; i < user.length; i++) {
          the_bidder.addBid(my_bid_list.get(Integer.parseInt(user[i])));
        }
        
      } else if (user[0].equals("AuctionCentralStaff")) {
        
      } else {
        
      }
      
      
      
      
    }
  }
}
