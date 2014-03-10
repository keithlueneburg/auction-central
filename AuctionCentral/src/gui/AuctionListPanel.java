package gui;

import auction.Auction;

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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import user.NonProfitUser;
import user.User;

/**
 * 
 * @author Kevin Alexander
 * @version 3/3/2014
 */
public class AuctionListPanel extends JPanel {

  /**
   * Required for serializable class (extends JPanel).
   */
  private static final long serialVersionUID = -6264694291693109242L;
  /**
   * 
   */
  private static final int DEFAULT_WIDTH = 824;
  /**
   * 
   */
  private static final int DEFAULT_HEIGHT = 680;
  /**
   * 
   */
  private static final int INSET_SIZE = 20;
  /**
   * The JList component that holds the auctions.
   */
  private JList my_jlist;
  /**
   * A list of auctions that will be placed in my_jlist.
   */
  private List<Auction> my_auction_list;
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
   * @param the_jframe The main ApplicationFrame.
   * @param the_auctions The list of Auctions.
   */
  public AuctionListPanel(final ApplicationFrame the_jframe, 
      final List<Auction> the_auctions) {
    
    super();
    my_app_frame = the_jframe;
    my_auction_list = the_auctions;
    my_index = -1;
    my_button_panel = new JPanel();
    my_label_panel = new JPanel();
    
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
    final GridLayout g_layout = new GridLayout();
    g_layout.setColumns(2);
    g_layout.setRows(2);
    my_label_panel.setLayout(g_layout);
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel("     Auctions:"));
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
    final JButton edit_button = new JButton("View");
    edit_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        if (my_index >= 0) {
          my_app_frame.showAuctionInfo(my_auction_list.get(my_index), false);
        }
      }
    });
    
    final JButton create_button = new JButton("Create");
    // If too many auctions already or current user is not NPO user,
    //make the new auction tied to the user/organization
    if (my_auction_list.size() >= 25 
        || !(my_app_frame.getSystem().getCurrentUser() instanceof NonProfitUser) ) {
      create_button.setEnabled(false);
    }
    create_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        if (my_auction_list.size() < 25) {
          User current_user = my_app_frame.getSystem().getCurrentUser();
          
          my_app_frame.showAuctionInfo(new Auction((NonProfitUser) current_user), true);
          
        } else {
          create_button.setEnabled(false);
        }
      }
    });
    my_button_panel.add(edit_button);
    my_button_panel.add(create_button);
  }
  
  /**
   * This is the specific panel that will hold the list of Auctions.
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
      my_layout_constraints.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
      my_layout_constraints.anchor = GridBagConstraints.CENTER;
      my_layout_constraints.weightx = 1.0;
      my_layout_constraints.weighty = 1.0;
      my_layout.setConstraints(my_scrollpane, my_layout_constraints);
    }
    /**
     * This method configures the JList by adding the Auctions to it.
     */
    private void configList() {
      
      my_jlist.setListData(my_auction_list.toArray());
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
    private static final int GRID_SIZE = 4;
    /**
     * This is the label for the auction name.
     */
    private final JLabel my_name;
    /**
     * This is the label for the name of the Auction Central staff member that
     * added the auction.
     */
    private final JLabel my_checkname;
    /**
     * This is the label for the date of the auction.
     */
    private final JLabel my_date;
    /**
     * This is the label for the number of items in the auction.
     */
    private final JLabel my_numitems;
    
    /**
     * 
     */
    MyCellRenderer() {
      my_name = new JLabel();
      my_checkname = new JLabel();
      my_date = new JLabel();
      my_numitems = new JLabel();
      configRenderer();
    }
    /**
     * 
     */
    private void configRenderer() {
      setLayout(new GridLayout(1, GRID_SIZE));
      
      my_name.setOpaque(true);
      my_checkname.setOpaque(true);
      my_date.setOpaque(true);
      my_numitems.setOpaque(true);
      add(my_name);
      add(my_checkname);
      add(my_date);
      add(my_numitems);
    }
    /**
     * 
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, 
        boolean isSelected, boolean cellHasFocus) {
      String _name = ((Auction) value).getAuctionName();
      String _checkname = ((Auction) value).getIntakePerson();
      String _date = ((Auction) value).getAuctionDate().getTime().toString();
      String _num = Integer.toString(((Auction) value).getItemCount());
      
      my_name.setText(_name);
      my_checkname.setText(_checkname);
      my_date.setText(_date);
      my_numitems.setText(_num);
      
      if (isSelected) {
        
        my_name.setBackground(list.getSelectionBackground());
        my_checkname.setBackground(list.getSelectionBackground());
        my_date.setBackground(list.getSelectionBackground());
        my_numitems.setBackground(list.getSelectionBackground());
        
        my_name.setForeground(list.getSelectionForeground());
        my_checkname.setForeground(list.getSelectionForeground());
        my_date.setForeground(list.getSelectionForeground());
        my_numitems.setForeground(list.getSelectionForeground());
        
        my_index = index;
        
      } else {
        
        my_name.setBackground(list.getBackground());
        my_name.setForeground(list.getForeground());
        my_checkname.setBackground(list.getBackground());
        my_checkname.setForeground(list.getForeground());
        my_date.setBackground(list.getBackground());
        my_date.setForeground(list.getForeground());
        my_numitems.setBackground(list.getBackground());
        my_numitems.setForeground(list.getForeground());
        
      }
      
      setEnabled(list.isEnabled());
      setFont(list.getFont());
      
      return this;
      
    }
  }
}

