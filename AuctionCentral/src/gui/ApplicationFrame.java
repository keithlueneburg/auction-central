package gui;

import auction.Auction;
import auction.Item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import system.AuctionCentralSystem;
import user.Bidder;
import user.User;

/**
 * Class: ApplicationFrame
 * 
 * The main JFrame containing the rest of the GUI. A menu is displayed on the
 * left side, and the various content is displayed in the rest of the window.
 * 
 * TCSS 360 - Software Development and Quality Assurance
 * 
 * University of Washington, Tacoma
 * 
 * Winter 2014
 * 
 * Instructor: Dr. Adwoa Donyina
 * 
 * @author Kaiyuan Shi
 * @author Keith Lueneburg
 * 
 * @version 3/8/2014
 */
@SuppressWarnings("serial")
public final class ApplicationFrame extends JFrame {

  /**
   * Default witdth of the program frame.
   */
  private static final int DEFAULT_FRAME_WIDTH = 1024;

  /**
   * Default height of the program frame.
   */
  private static final int DEFAULT_FRAME_HEIGHT = 680;

  /**
   * Default witdth of the content panel.
   */
  private static final int DEFAULT_CONTENT_PANEL_WIDTH = 824;

  /**
   * Back end system for the program.
   */
  private final AuctionCentralSystem my_system;
  
  /**
   * The user currently logged into the system.
   */
  private User my_user;

  /**
   * The panel used for displaying different views (Auction, item, inventory
   * info).
   */
  private JPanel my_content_panel;

  /**
   * Contains a logo and menu buttons for navigating the software.
   */
  private final JPanel my_menu_panel;
  

  /**
   * Instantiate an application frame. Some functionality will depend on the
   * user type logging in.
   * 
   * @param a_user
   *          The type of user that is logging in to the program.
   * @param the_system 
   */
  public ApplicationFrame(final User a_user, final AuctionCentralSystem the_system) {
    super("Auction Central");
    
    my_system = the_system;
    my_user = a_user;
    my_content_panel = new JPanel();
    
    my_menu_panel = new MenuPanel(a_user, this, the_system);
    
    // This WindowListener allows program to save persistent data when
    // the window's 'X' button is clicked.
    addWindowListener(new MyWindowListener());

    setup();
    
    //modify by Kaiyuan
    if (my_user instanceof Bidder && ((Bidder) my_user).isRegistered() == false) {
      JOptionPane.showMessageDialog(null, "Your card has expired, please add a new card!",
          "Expired card", JOptionPane.ERROR_MESSAGE);
      showRegistration();
    }
  }

  /**
   * Set up frame properties and components contained in the frame.
   */
  private void setup() {
    setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
    setResizable(false);

    setLocationByPlatform(true);

    add(my_menu_panel, BorderLayout.WEST);
    replaceContentPanel(my_content_panel);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Displays the frame on screen.
   */
  public void start() {
    setVisible(true);
  }

  /**
   * Get the back end AuctionCentralSystem.
   * 
   * @return The back end system.
   */
  public AuctionCentralSystem getSystem() {
    return my_system;
  }
  
  /**
   * Display the provided JPanel in the content area.
   * 
   * @param a_panel
   *          The panel to display in the content area.
   */
  public void replaceContentPanel(final JPanel a_panel) {
    remove(my_content_panel);
    
    my_content_panel = a_panel;
    my_content_panel.setPreferredSize(new Dimension(DEFAULT_CONTENT_PANEL_WIDTH,
        DEFAULT_FRAME_HEIGHT));
    
    add(my_content_panel, BorderLayout.CENTER);
    pack();
    repaint();
  }

  /**
   * Display an auction list panel in the content area.
   */
  public void showAuctionList() {
    replaceContentPanel(new AuctionListPanel(this, my_system.getAuctionList(),
        my_system.getCurrentUser()));
  }

  /**
   * Display an auction info panel in the content area, for the given auction.
   * 
   * @param an_auction
   *          The auction to display the auction info panel for.
   * @param an_editable Make the panel editable or not. 
   */
  public void showAuctionInfo(final Auction an_auction, final boolean an_editable) {
    replaceContentPanel(new AuctionPanel(an_auction, this, my_system, an_editable));
  }

  /**
   * Display an inventory info panel in the content area, for the given auction.
   * 
   * @param an_auction
   *          The auction to display the inventory for.
   */
  public void showInventory(final Auction an_auction) {
    replaceContentPanel(new InventoryPanel(this, an_auction, my_system.getCurrentUser()));
  }

  /**
   * Display an item info panel in the content area, for the given item.
   * 
   * @param an_item
   *          The item to display the info for.
   * @param an_editable Make the panel editable or not. 
   * @param an_auction The auction the item belongs to.
   */
  public void showItem(final Item an_item, final Auction an_auction, 
      final boolean an_editable) {
    replaceContentPanel(new ItemPanel(an_item, this, an_auction, an_editable,
        my_system.getCurrentUser()));
  }

  /**
   * Display the calendar in the content area.
   */
  public void showCalendar() {
    replaceContentPanel(new CalendarPanel(my_system, this));
  }
  
  /**
   * Show the registration panel.
   */
  public void showRegistration() { //modify by Kaiyuan
    replaceContentPanel(new RegistrationPanel(my_system, this, my_user)); 
  }
  
  
  /**
   * Show the list of a bidder's bids.
   */
  public void showBidList() {
    replaceContentPanel(new BidListPanel(this,
        ((Bidder) my_system.getCurrentUser()).getBids()));
  }

  /**
   * Disable the registration button on the menu panel.
   */
  public void disableRegistration() {
    ((MenuPanel) my_menu_panel).disableRegistration();
  }
  
  /**
   * WindowListener used to ensure data is saved when program window is
   * closed directly.
   * 
   * @author Kaiyuan Shi
   *
   */
  private class MyWindowListener extends WindowAdapter {
    /* (non-Javadoc)
     * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
     */
    public void windowClosing(final WindowEvent an_event) {
      my_system.savingData();
      System.exit(0);
    }
  }
}
