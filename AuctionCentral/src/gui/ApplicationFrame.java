package gui;

import auction.Auction;
import auction.Item;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class ApplicationFrame extends JFrame {
  
  JPanel myContentPanel;

  public ApplicationFrame() {
    
  }
  
  private void setup(){
    
  }

  public void replaceContentPanel(final JPanel a_panel) {
    myContentPanel = a_panel;
  }

  public void showAuctionInfo(final Auction an_auction) {

  }

  public void showInventory(final Auction an_auction) {

  }

  public void showItem(final Item an_item) {

  }
}
