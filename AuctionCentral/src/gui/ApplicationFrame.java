package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import auction.Auction;
import auction.Item;

import javax.swing.JFrame;
import javax.swing.JPanel;

import user.AbstractUser;
import user.User;




public class ApplicationFrame extends JFrame {
  
  private JPanel my_content_panel;
  final private JPanel my_menu_panel;
  
  public ApplicationFrame(final User a_user) {
    my_content_panel = new JPanel();
    my_menu_panel = new MenuPanel();
    
    setup();
  }
    
  private void setup() {
    setSize(1024, 680);
    
    my_menu_panel.setBackground(Color.BLUE);
    add(my_menu_panel, BorderLayout.WEST);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  public void start() {
    setVisible(true);
  }

  public void replaceContentPanel(final JPanel a_panel) {
    my_content_panel = a_panel;
  }

  public void showAuctionInfo(final Auction an_auction) {

  }

  public void showInventory(final Auction an_auction) {

  }

  public void showItem(final Item an_item) {

  }
}
