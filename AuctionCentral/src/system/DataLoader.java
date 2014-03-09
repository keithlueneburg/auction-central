package system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import bidding.Address;
import bidding.CreditCard;
import user.AbstractUser;
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
  
  public static void loadData(List<AbstractUser> the_user_list,
      List<Auction> the_auction_list) {
    
    try {
      my_user_scanner = new Scanner(new FileInputStream("input/user.txt"));
      my_auction_scanner = new Scanner(new FileInputStream("input/auction.txt"));
      my_item_scanner = new Scanner(new FileInputStream("input/item.txt"));
      my_bid_scanner = new Scanner(new FileInputStream("input/bid.txt"));
      my_address_scanner = new Scanner(new FileInputStream("input/address.txt"));
      my_card_scanner = new Scanner(new FileInputStream("input/card.txt"));
    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    }
    
    loadAddressList();
    loadCardList();
    loadBidList();
    loadItemList();
    loadAuctionList();
    loadUserList();
    
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
    while (my_user_scanner.hasNextLine()) {
      String line = my_user_scanner.nextLine();
      String[] address = line.split(",");
      
      my_address_list.add(new Address(address[0],
          Integer.parseInt(address[1]), address[2],
          address[3], Integer.parseInt(address[4])));     
    }
  }
  
  private static void loadCardList() {
    
    while (my_card_scanner.hasNextLine()) {
      String line = my_card_scanner.nextLine();
      String[] card = line.split(",");
      
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
    
  }
  
  private static void loadItemList() {
    
  }
  
  private static void loadAuctionList() {
    
  }
  
  private static void loadUserList() {
    
  }
}
