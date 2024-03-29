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
import java.awt.event.KeyEvent;
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
 * A panel containing the list of active auctions in the system.
 * 
 * @author Kevin Alexander
 * @version 3/3/2014
 */
public class AuctionListPanel extends JPanel {

  /**
   *  
   */
  private static final int MAX_AUCTIONS = 25;


  /**
   * Number of grid layout columns for the label panel. 
   */
  private static final int LABEL_GRID_COLUMNS = 4;
    
  /**
   * Number of grid layout rows for the label panel. 
   */
  private static final int LABEL_GRID_ROWS = 4;
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
   * 
   */
  private User my_user;
  /**
   * The constructor takes in two parameters: an ApplicationFrame and a list of Auctions.
   * It then initializes the frame and configures the panels.
   * @param the_jframe The main ApplicationFrame.
   * @param the_auctions The list of Auctions.
   * @param the_user The user that is logged into the system.
   */
  public AuctionListPanel(final ApplicationFrame the_jframe, 
      final List<Auction> the_auctions, final User the_user) {
    
    super();
    my_app_frame = the_jframe;
    my_auction_list = the_auctions;
    my_index = -1;
    my_button_panel = new JPanel();
    my_label_panel = new JPanel();
    my_user = the_user;
    
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

    my_label_panel.setLayout(new GridLayout(LABEL_GRID_ROWS, LABEL_GRID_COLUMNS));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel("        Auctions:"));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel(""));
    my_label_panel.add(new JLabel("        Auction name"));
    my_label_panel.add(new JLabel(" Intake person"));
    my_label_panel.add(new JLabel("               Date"));
    my_label_panel.add(new JLabel("Number of items"));
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
    edit_button.setMnemonic(KeyEvent.VK_V);
    edit_button.setToolTipText("view the auction information");
    edit_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        if (my_index >= 0) {
          my_app_frame.showAuctionInfo(my_auction_list.get(my_index), false);
        }
      }
    });
    
    final JButton create_button = new JButton("Create");
    create_button.setMnemonic(KeyEvent.VK_C);
    create_button.setToolTipText("Create a new auction");
    // If too many auctions already or current user is not NPO user,
    //make the new auction tied to the user/organization
    if (my_auction_list.size() >= MAX_AUCTIONS 
        || !(my_app_frame.getSystem().getCurrentUser() instanceof NonProfitUser)) {
      create_button.setEnabled(false);
    }
    create_button.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent an_event) {
        if (my_auction_list.size() < MAX_AUCTIONS) {
          final User current_user = my_app_frame.getSystem().getCurrentUser();
          
          my_app_frame.showAuctionInfo(new Auction((NonProfitUser) current_user), true);
          
        } else {
          create_button.setEnabled(false);
        }
      }
    });
    my_button_panel.add(edit_button);
    
    if (my_user instanceof NonProfitUser) {
      my_button_panel.add(create_button);
    }
  }
  
  /**
   * This is the specific panel that will hold the list of Auctions.
   * @author Kevin Alexander
   * @version 3/4/2014
   */
  @SuppressWarnings("serial")
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
  @SuppressWarnings("serial")
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
    
    /* (non-Javadoc)
     * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList,
     *  java.lang.Object, int, boolean, boolean)
     */
    public Component getListCellRendererComponent(final JList a_list, final Object a_value,
        final int the_index, final boolean the_is_selected, final boolean the_cell_has_focus) {
      final String name = ((Auction) a_value).getAuctionName();
      final String checkname = ((Auction) a_value).getIntakePerson();
      final String date = ((Auction) a_value).getAuctionDate().getTime().toString();
      String num = "               ";
      num += Integer.toString(((Auction) a_value).getItemCount());
      
      my_name.setText(name);
      my_checkname.setText(checkname);
      my_date.setText(date);
      my_numitems.setText(num);
      
      if (the_is_selected) {
        
        my_name.setBackground(a_list.getSelectionBackground());
        my_checkname.setBackground(a_list.getSelectionBackground());
        my_date.setBackground(a_list.getSelectionBackground());
        my_numitems.setBackground(a_list.getSelectionBackground());
        
        my_name.setForeground(a_list.getSelectionForeground());
        my_checkname.setForeground(a_list.getSelectionForeground());
        my_date.setForeground(a_list.getSelectionForeground());
        my_numitems.setForeground(a_list.getSelectionForeground());
        
        my_index = the_index;
        
      } else {
        
        my_name.setBackground(a_list.getBackground());
        my_name.setForeground(a_list.getForeground());
        my_checkname.setBackground(a_list.getBackground());
        my_checkname.setForeground(a_list.getForeground());
        my_date.setBackground(a_list.getBackground());
        my_date.setForeground(a_list.getForeground());
        my_numitems.setBackground(a_list.getBackground());
        my_numitems.setForeground(a_list.getForeground());
        
      }
      
      setEnabled(a_list.isEnabled());
      setFont(a_list.getFont());
      
      return this;
      
    }
  }
}