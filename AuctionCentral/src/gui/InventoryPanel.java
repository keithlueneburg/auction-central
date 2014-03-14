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
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import user.Bidder;
import user.User;

/**
 * A panel to display an auction's inventory.
 * 
 * @author Kaiyuan Shi
 * @version 3/13/2014
 *
 */
@SuppressWarnings("serial")
public class InventoryPanel extends JPanel {

  /**
   * Space character string.
   */
  private static final String SPACE = " ";

  /**
   * The columns for the label grid layout.
   */
  private static final int LABEL_GRID_COLUMNS = 3;
  
  /**
   * The rows for the label grid layout. 
   */
  private static final int LABEL_GRID_ROWS = 4;
  /**
   * Default panel height.
   */
  private static final int DEFAULT_HEIGHT = 680;
  /**
   * Default panel width.
   */
  private static final int DEFAULT_WIDTH = 824;
  /**
   * Layout inset size.
   */
  private static final int INSET_SIZE = 20;
  /**
   * The list of items in the auction's inventory.
   */
  private List<Item> my_item_list;
  /**
   * The JList component that holds the auctions.
   */
  private JList my_jlist;
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
   * The auction to show the inventory for.
   */
  private Auction my_auction;

  /**
   * The user logged into the system.
   */
  private User my_user;

  /**
   * Create a new InventoryPanel.
   * 
   * @param the_jframe The frame this panel belongs to.
   * @param an_auction The auction to show the inventory for.
   * @param a_user The user logged into the system.
   */
  public InventoryPanel(final ApplicationFrame the_jframe,
      final Auction an_auction, final User a_user) {
    super();

    my_auction = an_auction;
    my_item_list = an_auction.getItems();
    my_app_frame = the_jframe;
    my_button_panel = new JPanel();
    my_label_panel = new JPanel();
    my_user = a_user;
    my_index = -1;
    configPanel();
    configLabels();
    configPanels();
    configButtons();
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
    /*final GridLayout g_layout = new GridLayout();
     g_layout.setColumns(2);
     g_layout.setRows(2);
     my_label_panel.setLayout(g_layout);
     my_label_panel.add(new JLabel(""));
     my_label_panel.add(new JLabel(""));
     my_label_panel.add(new JLabel("     Inventory:"));
     my_label_panel.add(new JLabel(""));*/

    my_label_panel.setLayout(new GridLayout(LABEL_GRID_ROWS, LABEL_GRID_COLUMNS));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel("       Inventory:"));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel("       Item#"));
    my_label_panel.add(new JLabel("    Item"));
    my_label_panel.add(new JLabel(" Quantity"));

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
    final JButton view_button = new JButton("View");

    view_button.setMnemonic(KeyEvent.VK_V);
    view_button.setToolTipText("View this item information");
    view_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        if (my_index >= 0) {

          boolean editable = false;
          String user_name = my_user.getFirstName() + SPACE;
          user_name += my_user.getLastName();

          if (user_name.equals(my_auction.getContactPerson())) {
            editable = true;
          }

          my_app_frame.showItem(my_item_list.get(my_index), my_auction, editable);

        }
      }
    });

    if (my_item_list.size() == 0) {
      view_button.setEnabled(false);
      view_button.setVisible(false);
    }

    final JButton add_button = new JButton("Add");
    add_button.setMnemonic(KeyEvent.VK_A);
    add_button.setToolTipText("Add more items");
    add_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        my_app_frame.showItem(new Item(), my_auction, true);
      }
    });

    final JButton back_button = new JButton("Back");
    back_button.setMnemonic(KeyEvent.VK_B);
    back_button.setToolTipText("Back to the auction");
    back_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        my_app_frame.showAuctionInfo(my_auction, false);
      }
    });

    my_button_panel.add(view_button);

    String user_name = my_user.getFirstName() + SPACE;
    user_name += my_user.getLastName();
    if (user_name.equals(my_auction.getContactPerson())) {
      my_button_panel.add(add_button);
    }
        
    final JButton bid_button = new JButton("Bid");
    bid_button.setMnemonic(KeyEvent.VK_I);
    bid_button.setToolTipText("Bid on an item");
    bid_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        
        if (my_index >= 0) {
          
          boolean has_bid = false;
          final Item temp_item = my_item_list.get(my_index);
          final List<Bid> bid_q = ((Bidder) my_user).getBids();
          for (Bid bid : bid_q) {
            if (temp_item.getItemName().equals(bid.getItemName())) {
              has_bid = true;
              break;
            }
          }
          //System.out.println(has_bid);
          if (has_bid) {
            JOptionPane.showMessageDialog(null, "You already have a bid for that item!", 
                "Bid Already Made", JOptionPane.WARNING_MESSAGE);
          } else {
            
            boolean success_bid = false;
            final Bidder bidder = (Bidder) my_app_frame.getSystem().getCurrentUser();
            double bid_price = 0.0;
            do {
              String bet_string =
                  JOptionPane.showInputDialog(null, "Bid price: ", "$0.00");
              
              try {
                
                if (bet_string.length() != 0 && bet_string.charAt(0) == '$') {
                  bet_string = bet_string.substring(1);
                }
                
                bid_price = Double.parseDouble(bet_string);
                
                final Item theitem = my_item_list.get(my_index);
                if (bid_price >= theitem.getStartingBid()) {
                  success_bid = true;
                  final Bid temp_bid = new Bid(theitem.getItemName(), bid_price, 
                      bidder.getUsername(), Calendar.getInstance(), bidder.getCard());
                  
                  bidder.addBid(temp_bid);
                  theitem.addBid(temp_bid);
                } else if (!success_bid) {
                  JOptionPane.showMessageDialog(null, "Bid Too Low!", "Bid Too Low",
                      JOptionPane.WARNING_MESSAGE);
                }
              } catch (final NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Wrong input!", "ERROR", 
                    JOptionPane.WARNING_MESSAGE);
              } catch (final NullPointerException e) {
              //do nothing
                success_bid = true;
              }
            } while (!success_bid);
          }
        }
      }
    });
    
    if (my_app_frame.getSystem().getCurrentUser() instanceof Bidder) {
      my_button_panel.add(bid_button);
    }
    if (my_item_list.size() == 0) {
      bid_button.setVisible(false);
    }
    my_button_panel.add(back_button);
  }

  /**
   * This is the specific panel that will hold the list of Auctions.
   * @author Kaiyuan Shi
   * @version 3/8/2014
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
      my_jlist = new JList<Item>();
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

      my_jlist.setListData(my_item_list.toArray());
      my_jlist.setCellRenderer(new MyCellRenderer());
    }
  }

  
  /**
   * Cell renderer for the list of items.
   */
  private class MyCellRenderer extends JPanel implements ListCellRenderer {
    /**
     * This is the default grid size for the list.
     */
    private static final int GRID_SIZE = 3;
    /**
     * This is the label for the auction name.
     */
    private final JLabel my_item_number;
    /**
     * This is the label for the name of the Auction Central staff member that
     * added the auction.
     */
    private final JLabel my_item_name;
    /**
     * This is the label for the date of the auction.
     */
    private final JLabel my_item_quantity;

    /**
     * Creates an instance of MyCellRenderer.
     */
    MyCellRenderer() {
      my_item_number = new JLabel();
      my_item_name = new JLabel();
      my_item_quantity = new JLabel();
      configRenderer();
    }
    /**
     * Configure the cell renderer.
     */
    private void configRenderer() {
      setLayout(new GridLayout(1, GRID_SIZE));

      my_item_number.setOpaque(true);
      my_item_name.setOpaque(true);
      my_item_quantity.setOpaque(true);
      add(my_item_number);
      add(my_item_name);
      add(my_item_quantity);
    }


    @Override
    public Component getListCellRendererComponent(final JList a_list, final Object a_value,
        final int the_index, final boolean the_is_selected, final boolean the_cell_has_focus) {
      final int item_number = ((Item) a_value).getItemNumber();
      final String item_name = ((Item) a_value).getItemName();
      final int item_quantity = ((Item) a_value).getItemQuantity();

      my_item_number.setText(Integer.valueOf(item_number).toString());
      my_item_name.setText(item_name);
      my_item_quantity.setText(Integer.valueOf(item_quantity).toString());

      if (the_is_selected) {

        my_item_number.setBackground(a_list.getSelectionBackground());
        my_item_name.setBackground(a_list.getSelectionBackground());
        my_item_quantity.setBackground(a_list.getSelectionBackground());

        my_item_number.setForeground(a_list.getSelectionForeground());
        my_item_name.setForeground(a_list.getSelectionForeground());
        my_item_quantity.setForeground(a_list.getSelectionForeground());

        my_index = the_index;

      } else {

        my_item_number.setBackground(a_list.getBackground());
        my_item_number.setForeground(a_list.getForeground());
        my_item_name.setBackground(a_list.getBackground());
        my_item_name.setForeground(a_list.getForeground());
        my_item_quantity.setBackground(a_list.getBackground());
        my_item_quantity.setForeground(a_list.getForeground());
      }

      setEnabled(a_list.isEnabled());
      setFont(a_list.getFont());

      return this;
    }
  }
}