/* File ISView.java
 * Author: Ilya Efremenko
 * Created on: 26 Apr 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ISView extends JFrame {
    ImageIcon icon;
    JMenuBar bar;
    JMenu fileMenu, itemMenu, viewMenu, helpMenu;
    JMenuItem openMI, saveMI, saveAsMI, addMI, deleteMI, userManualMI, faqMI;
    JPanel pathPanel, searchPanel, treePanel;
    JTextField searchBar;
    JScrollPane pathPanelScroller;

    public ISView(ActionListener listener) {

        // Frame
        super("Inventory System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLayout(new BorderLayout());
        icon = new ImageIcon("ISIcon.png");
        this.setIconImage(icon.getImage());

        // Menu bar
        bar = new JMenuBar();

        bar.add(fileMenu = new JMenu("File"));
        bar.add(itemMenu = new JMenu("Item"));
        bar.add(viewMenu = new JMenu("View"));
        bar.add(helpMenu = new JMenu("Help"));

        fileMenu.add(openMI = new JMenuItem("Open"));
        fileMenu.add(saveMI = new JMenuItem("Save"));
        fileMenu.add(saveAsMI = new JMenuItem("Save as"));

        itemMenu.add(addMI = new JMenuItem("Add"));
        itemMenu.add(deleteMI = new JMenuItem("Delete"));

        helpMenu.add(userManualMI = new JMenuItem("User manual"));
        helpMenu.add(faqMI = new JMenuItem("FAQ"));

        this.setJMenuBar(bar);

        // Path panel
        pathPanel = new JPanel();
        pathPanel.setPreferredSize(new Dimension(200, 500));
        pathPanel.setLayout(new BorderLayout());
        this.add(pathPanel, BorderLayout.WEST);

        searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(200, 20));
        searchPanel.setBackground(new Color(230, 230, 230));
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        searchPanel.setLayout(new BorderLayout());
        pathPanel.add(searchPanel, BorderLayout.NORTH);

        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(200, 480));
        treePanel.setBackground(new Color(212, 212, 212));
        pathPanel.add(treePanel, BorderLayout.CENTER);

        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(200, 20));
        searchPanel.add(searchBar, BorderLayout.NORTH);


        //pathPanelScroller = new JScrollPane(pathPanel);
        //pathPanelScroller.add(new JScrollBar());

        //this.add(pathPanelScroller, BorderLayout.WEST);


        this.setVisible(true);
    }
}
