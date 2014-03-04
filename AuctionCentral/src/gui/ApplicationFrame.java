package gui;

import auction.Auction;
import auction.Item;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
 * @author Keith Lueneburg
 * @version 3/2/2014
 */
public final class ApplicationFrame extends JFrame {

  /**
   * Required for extended Serializable class JFrame.
   */
  private static final long serialVersionUID = -716727811602146864L;

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
   */
  public ApplicationFrame(final User a_user) {
    super("Auction Central");
    my_content_panel = new JPanel();
    my_menu_panel = new MenuPanel(a_user, this);

    setup();
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
    replaceContentPanel(new AuctionListPanel());
  }

  /**
   * Display an auction info panel in the content area, for the given auction.
   * 
   * @param an_auction
   *          The auction to display the auction info panel for.
   */
  public void showAuctionInfo(final Auction an_auction) {
    replaceContentPanel(new AuctionPanel(an_auction));
  }

  /**
   * Display an inventory info panel in the content area, for the given auction.
   * 
   * @param an_auction
   *          The auction to display the inventory for.
   */
  public void showInventory(final Auction an_auction) {
    replaceContentPanel(new InventoryPanel(an_auction));
  }

  /**
   * Display an item info panel in the content area, for the given item.
   * 
   * @param an_item
   *          The item to display the info for.
   */
  public void showItem(final Item an_item) {
    replaceContentPanel(new ItemPanel(an_item));
  }

  /**
   * Display the calendar in the content area.
   */
  public void showCalender() {
    replaceContentPanel(new CalendarPanel());
  }
}
