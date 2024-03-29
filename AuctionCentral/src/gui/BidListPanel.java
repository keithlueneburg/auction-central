package gui;

import auction.Auction;
import auction.Bid;
import auction.Item;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;


/**
 * A panel containing a list of bids for the bidder to view.
 * 
 * @author Kevin Alexander
 * @version 3/13/2014
 *
 */
@SuppressWarnings("serial")
public class BidListPanel extends JPanel {
  
  /**
   * Seconds per minute. 
   */
  private static final int SECONDS_PER_MINUTE = 60;
  /**
   * Minutes per hour.
   */
  private static final int MINUTES_PER_HOUR = 60;
  /**
   * Hours per day.
   */
  private static final int HOURS_PER_DAY = 24;
  /**
   * Default panel width. 
   */
  private static final int DEFAULT_WIDTH = 824;
  /**
   * Default panel height.
   */
  private static final int DEFAULT_HEIGHT = 680;
  /**
   * Size of the layout inset. 
   */
  private static final int INSET_SIZE = 20;
  /**
   * The JList component that holds the bids.
   */
  private JList my_jlist;
  /**
   * A list of auctions that will be placed in my_jlist.
   */
  private List<Bid> my_bid_list;
  /**
   * A scrollable view of the list.
   */
  private JScrollPane my_scrollpane;
  /**
   * The index of the auction that is selected.
   */
  private int my_index;
  /**
   * A panel that will hold the buttons.
   */
  private JPanel my_button_panel;
  /**
   * A panel that will hold the labels.
   */
  private JPanel my_label_panel;
  /**
   * A reference to the main application frame.
   */
  private ApplicationFrame my_app_frame;
  /**
   * The constructor takes in two parameters: an ApplicationFrame and a list of Auctions.
   * It then initializes the frame and configures the panels.
   * 
   * @param the_jframe The main ApplicationFrame.
   * @param the_bids The list of bids to display.
   */
  public BidListPanel(final ApplicationFrame the_jframe, 
      final List<Bid> the_bids) {
    
    super();
    my_app_frame = the_jframe;
    my_bid_list = the_bids;
    my_index = -1;
    my_button_panel = new JPanel();
    my_label_panel = new JPanel();
    
    configPanel();
    configLabels();
    configPanels();
    //configButtons();
    
  }
  /**
   * This method configures the AuctionListPanel to its preferred size and sets its
   * layout.
   */
  public void configPanel() {
    this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    this.setLayout(new BorderLayout());
  }
  /**
   * This method configures the JLabels that are positioned above the list of 
   * Auctions.
   */
  public void configLabels() {
    final GridLayout g_layout = new GridLayout();
    g_layout.setColumns(2);
    g_layout.setRows(2);
    my_label_panel.setLayout(g_layout);
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel("     Bids:"));
    my_label_panel.add(new JLabel(""));
  }
  /**
   * This method configures the JPanels to their desired location in the 
   * AuctionListPanel.
   */
  public void configPanels() {
    my_button_panel.setLayout(new FlowLayout());
    
    this.add(new ListPanel(), BorderLayout.CENTER);
    
    this.add(my_button_panel, BorderLayout.SOUTH);
    this.add(my_label_panel, BorderLayout.NORTH);
  }
  /**
   * This method creates the JButtons with their desired names and sets 
   * ActionListeners to them that will correctly do what is wanted.
   */
  
  public void configButtons() {
    /*
    final JButton edit_button = new JButton("View");
    edit_button.setMnemonic(KeyEvent.VK_V);
    edit_button.setToolTipText("view the bid information");
    if (my_app_frame.getSystem().getCurrentUser() instanceof Bidder) {
      edit_button.setVisible(true);
    } else {
      edit_button.setVisible(false);
    }
    edit_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        if (my_index >= 0) {
          //my_app_frame.showAuctionInfo(my_bid_list.get(my_index), false);
        }
      }
    });
    
    final JButton create_button = new JButton("Create");
    create_button.setMnemonic(KeyEvent.VK_C);
    create_button.setToolTipText("Create a new auction");
    // If too many auctions already or current user is not NPO user,
    //make the new auction tied to the user/organization
    if (my_bid_list.size() >= 25 
        || !(my_app_frame.getSystem().getCurrentUser() instanceof NonProfitUser) ) {
      create_button.setEnabled(false);
    }
    create_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        if (my_bid_list.size() < 25) {
          User current_user = my_app_frame.getSystem().getCurrentUser();
          
          my_app_frame.showAuctionInfo(new Auction((NonProfitUser) current_user), true);
          
        } else {
          create_button.setEnabled(false);
        }
      }
    });
    //my_button_panel.add(edit_button);
    //my_button_panel.add(create_button);
    */
    final JButton delete_button = new JButton("Delete");
    delete_button.setMnemonic(KeyEvent.VK_D);
    delete_button.setToolTipText("Delete a bid");
    if (my_bid_list.size() == 0) {
      delete_button.setEnabled(false);
      delete_button.setVisible(false);
    }
    delete_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        final List<Auction> auction_list = my_app_frame.getSystem().getAuctionList();
        boolean found = false;
        final Bid this_bid = my_bid_list.get(my_index);
        Calendar auct_date = new GregorianCalendar();
        
        for (Auction auction : auction_list) {
          final List<Item> item_list = auction.getItems();
          for (Item item : item_list) {
            final List<Bid> bid_queue = item.getBids();
            for (Bid bid : bid_queue) {
              if (bid == this_bid) {
                found = true;
                auct_date = auction.getAuctionDate();
                break;
              }              
            }
            if (found) {
              break;
            }
          }
          if (found) {
            
            break;
          }
        }
        
        if (new GregorianCalendar().getTimeInMillis() - auct_date.getTimeInMillis() 
            >= HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE) {
          // delete bid
        }
      }
    });
  }
  
  
  /**
   * This is the specific panel that will hold the list of Bids.
   * @author Kevin Alexander
   * @version 3/4/2014
   */
  private class ListPanel extends JPanel {
    /**
     * The GridBagLayout that is used for the list.
     */
    private final GridBagLayout my_layout;
    /**
     * The GridBagLayoutConstraints that is used for the layout.
     */
    private final GridBagConstraints my_layout_constraints;
    /**
     * This is the constructor for the ListPanel. It configures the 
     * ListPanel to the desired specifications.
     */
    public ListPanel() {
      
      super();
      
      my_layout = new GridBagLayout();
      my_layout_constraints = new GridBagConstraints();
      setLayout(my_layout);
      my_jlist = new JList();
      my_scrollpane = new JScrollPane();
      my_scrollpane = new JScrollPane(my_jlist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
          JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
      configLayout();
      
      configList();
      
      add(my_scrollpane);
      
    }
    /**
     * This method configures the constraints of the layout and sets them to the layout.
     */
    private void configLayout() {
      
      my_layout_constraints.gridx = 0;
      my_layout_constraints.gridy = 0;
      my_layout_constraints.fill = GridBagConstraints.BOTH;
      my_layout_constraints.insets = new Insets(INSET_SIZE, INSET_SIZE, 
          INSET_SIZE, INSET_SIZE);
      my_layout_constraints.anchor = GridBagConstraints.CENTER;
      my_layout_constraints.weightx = 1.0;
      my_layout_constraints.weighty = 1.0;
      my_layout.setConstraints(my_scrollpane, my_layout_constraints);
    }
    /**
     * This method configures the JList by adding the Auctions to it.
     */
    private void configList() {
      
      my_jlist.setListData(my_bid_list.toArray());
      my_jlist.setCellRenderer(new MyCellRenderer());
    }
  }
  /**
   * This is the ListCellRenderer that makes the list have all the information
   * correctly laid out. It also sets that one line in the list and all the items
   * in it are all selected.
   * 
   * @author Kevin Alexander
   * @version 3/3/2014
   */
  private class MyCellRenderer extends JPanel implements ListCellRenderer {
    /**
     * This is the default grid size for the list.
     */
    private static final int GRID_SIZE = 3;
    /**
     * This is the label for the auction name.
     */
    private final JLabel my_item_name;
    /**
     * This is the label for the name of the Auction Central staff member that
     * added the auction.
     */
    private final JLabel my_bid_price;
    /**
     * This is the label for the date of the auction.
     */
    private final JLabel my_bid_time;
    
    /**
     * This is the label for the number of items in the auction.
     */
    /*
    private final JLabel my_numitems;
    */
    /**
     * 
     */
    MyCellRenderer() {
      my_item_name = new JLabel();
      my_bid_price = new JLabel();
      my_bid_time = new JLabel();
      //my_numitems = new JLabel();
      configRenderer();
    }
    /**
     * 
     */
    private void configRenderer() {
      setLayout(new GridLayout(1, GRID_SIZE));
      
      my_item_name.setOpaque(true);
      my_bid_price.setOpaque(true);
      my_bid_time.setOpaque(true);
      
      add(my_item_name);
      add(my_bid_price);
      add(my_bid_time);
    }
    
    
    /* (non-Javadoc)
     * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList,
     *  java.lang.Object, int, boolean, boolean)
     */
    public Component getListCellRendererComponent(final JList a_list, final Object a_value,
        final int the_index, final boolean the_is_selected, final boolean the_cell_has_focus) {
      final String item_name = ((Bid) a_value).getItemName();
      final NumberFormat nf = NumberFormat.getCurrencyInstance();
      final String bid_price = nf.format(((Bid) a_value).getPrice());
      final String bid_time = ((Bid) a_value).getBidTime().getTime().toString();
      
      my_item_name.setText(item_name);
      my_bid_price.setText(bid_price);
      my_bid_time.setText(bid_time);
      
      if (the_is_selected) {
        
        my_item_name.setBackground(a_list.getSelectionBackground());
        my_bid_price.setBackground(a_list.getSelectionBackground());
        my_bid_time.setBackground(a_list.getSelectionBackground());
        
        
        my_item_name.setForeground(a_list.getSelectionForeground());
        my_bid_price.setForeground(a_list.getSelectionForeground());
        my_bid_time.setForeground(a_list.getSelectionForeground());
        
        my_index = the_index;
        
      } else {
        
        my_item_name.setBackground(a_list.getBackground());
        my_item_name.setForeground(a_list.getForeground());
        my_bid_price.setBackground(a_list.getBackground());
        my_bid_price.setForeground(a_list.getForeground());
        my_bid_time.setBackground(a_list.getBackground());
        my_bid_time.setForeground(a_list.getForeground());
        
      }
      
      setEnabled(a_list.isEnabled());
      setFont(a_list.getFont());
      
      return this;
      
    }
  }
}