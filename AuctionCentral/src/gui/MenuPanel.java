package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel {
  private static final int DEFAULT_LOGO_WIDTH = 200;
  private static final int DEFAULT_LOGO_HEIGHT = 200;
  private static final float PANEL_BORDER_WIDTH = 10;
  private static final String LOGO_IMAGE_PATH = "images/logo.gif";

  public MenuPanel() {
    // FIXME Placeholder buttons
    setPreferredSize(new Dimension(200, 680));

    JPanel logo_panel = getLogoPanel();
    
    JButton button_1 = new JButton("Button 1");
    JButton button_2 = new JButton("Button 2");

    button_1.setPreferredSize(new Dimension(200, 45));
    button_2.setPreferredSize(new Dimension(200, 45));

    add(button_2, FlowLayout.LEFT);
    add(button_1, FlowLayout.LEFT);
    add(logo_panel, FlowLayout.LEFT);
    
  }

  private JPanel getLogoPanel() {
    JPanel logo_panel = new JPanel();
    logo_panel.setPreferredSize(new Dimension(DEFAULT_LOGO_WIDTH, DEFAULT_LOGO_HEIGHT));
    logo_panel.setLayout(new BorderLayout());
    logo_panel.setBackground(Color.GRAY.darker());
    //logo_panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(
        //PANEL_BORDER_WIDTH), Color.GRAY.brighter()));
    final JLabel logo_image = new JLabel();
    logo_image.setIcon(new ImageIcon(LOGO_IMAGE_PATH));
    logo_image.setVerticalAlignment(SwingConstants.CENTER);
    logo_image.setHorizontalAlignment(SwingConstants.CENTER);
    logo_panel.add(logo_image, BorderLayout.CENTER);
    return logo_panel;
  }

}
