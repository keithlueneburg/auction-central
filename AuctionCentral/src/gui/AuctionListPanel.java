package gui;

import auction.Auction;
import auction.Item;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.LinkedList;

public class AuctionListPanel extends JPanel {

  /**
   * Required for serializable class (extends JPanel).
   */
  private static final long serialVersionUID = -6264694291693109242L;
  
  private JList list;
  
  private List<Auction> my_auction_list;
  private JScrollPane my_scrollpane;
  private int my_index;
  
  private JPanel my_ButtonPanel;
  private JPanel my_LabelPanel;
  private ApplicationFrame my_aFrame;
  
  public AuctionListPanel(ApplicationFrame the_jframe, List<Auction> the_auctions) {
    super();
    this.setPreferredSize(new Dimension(824, 680));
    this.setLayout(new BorderLayout());
    my_aFrame = the_jframe;
    my_auction_list = the_auctions;
    my_index = -1;
    
    
    my_ButtonPanel = new JPanel();
    my_ButtonPanel.setLayout(new FlowLayout());
    
    my_LabelPanel = new JPanel();
    GridLayout gLayout = new GridLayout();
    gLayout.setColumns(2);
    gLayout.setRows(2);
    my_LabelPanel.setLayout(gLayout);
    my_LabelPanel.add(new JLabel(""));
    my_LabelPanel.add(new JLabel(""));
    my_LabelPanel.add(new JLabel("    Auctions:"));
    my_LabelPanel.add(new JLabel(""));
    this.add(new ListPanel(), BorderLayout.CENTER);
    
    
    this.add(my_ButtonPanel, BorderLayout.SOUTH);
    this.add(my_LabelPanel, BorderLayout.NORTH);
    
    JButton editButton = new JButton("Edit");
    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (my_index >= 0) {
          my_aFrame.showAuctionInfo(my_auction_list.get(my_index));
        }
      }
    });
    
    JButton createButton = new JButton("Create");
    createButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        my_aFrame.showAuctionInfo(null);
        
      }
    });
    my_ButtonPanel.add(editButton);
    my_ButtonPanel.add(new JButton("Create"));
  }
  
  private class ListPanel extends JPanel {
    
    public ListPanel() {
      super();
      
      GridBagLayout layout = new GridBagLayout();
      GridBagConstraints layoutConstraints = new GridBagConstraints();
      setLayout(layout);
      list = new JList();
      my_scrollpane = new JScrollPane();
      
      layoutConstraints.gridx = 0;
      layoutConstraints.gridy = 0;
      layoutConstraints.fill = GridBagConstraints.BOTH;
      layoutConstraints.insets = new Insets(20,20,20,20);
      layoutConstraints.anchor = GridBagConstraints.CENTER;
      layoutConstraints.weightx = 1.0;
      layoutConstraints.weighty = 1.0;
      my_scrollpane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
          JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      layout.setConstraints(my_scrollpane, layoutConstraints);
      list.setListData(my_auction_list.toArray());
      
      
      list.setCellRenderer(new MyCellRenderer());
      
      add(my_scrollpane);
      
    }
  }
  
  class MyCellRenderer extends JPanel implements ListCellRenderer {
    JLabel name, checkname, date, numitems;
    
    MyCellRenderer() {
      setLayout(new GridLayout(1, 4));
      name = new JLabel();
      checkname = new JLabel();
      date = new JLabel();
      numitems = new JLabel();
      name.setOpaque(true);
      checkname.setOpaque(true);
      date.setOpaque(true);
      numitems.setOpaque(true);
      add(name);
      add(checkname);
      add(date);
      add(numitems);
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int index, 
        boolean isSelected, boolean cellHasFocus) {
      String _name = ((Auction) value).getAuctionName();
      String _checkname = ((Auction) value).getIntakePerson();
      String _date = ((Auction) value).getAuctionDate().getTime().toString();
      String _num = Integer.toString(((Auction) value).getItemCount());
      
      name.setText(_name);
      checkname.setText(_checkname);
      date.setText(_date);
      numitems.setText(_num);
      
      if (isSelected) {
        name.setBackground(list.getSelectionBackground());
        checkname.setBackground(list.getSelectionBackground());
        date.setBackground(list.getSelectionBackground());
        numitems.setBackground(list.getSelectionBackground());
        
        name.setForeground(list.getSelectionForeground());
        checkname.setForeground(list.getSelectionForeground());
        date.setForeground(list.getSelectionForeground());
        numitems.setForeground(list.getSelectionForeground());
        
        my_index = index;
        
      } else {
        name.setBackground(list.getBackground());
        name.setForeground(list.getForeground());
        checkname.setBackground(list.getBackground());
        checkname.setForeground(list.getForeground());
        date.setBackground(list.getBackground());
        date.setForeground(list.getForeground());
        numitems.setBackground(list.getBackground());
        numitems.setForeground(list.getForeground());
        
      }
      
      setEnabled(list.isEnabled());
      setFont(list.getFont());
      
      return this;
      
    }
  }
}

