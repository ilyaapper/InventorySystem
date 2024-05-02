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
    JMenuItem openMI, saveMI, saveAsMI, addMI, deleteMI, winLafMI, metalLafMI, darkLafMI, userManualMI, faqMI;
    JPanel pathPanel, searchPanel, treePanel, mainPanel, containerPanel, itemPanel, statsPanel, valuePanel, notePanel;
    JTextField searchBar;
    JScrollPane pathPanelScroller;
    JTree tree;
    JLabel containerName, itemName, itemQuantity, itemPrice;
    JList<Item> itemList;
    DefaultListModel<Item> itemModel;
    JTextArea itemNotes;
    JSpinner quantitySpinner, priceSpinner;

    public ISView(ActionListener listener) {

        // Frame
        super("Inventory System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setMinimumSize(new Dimension(600, 450));
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

        viewMenu.add(metalLafMI = new JMenuItem("Metal"));
        viewMenu.add(winLafMI = new JMenuItem("Windows"));
        viewMenu.add(darkLafMI = new JMenuItem("Dark"));
        winLafMI.addActionListener(listener);
        metalLafMI.addActionListener(listener);
        darkLafMI.addActionListener(listener);

        helpMenu.add(userManualMI = new JMenuItem("User manual"));
        helpMenu.add(faqMI = new JMenuItem("FAQ"));
        userManualMI.addActionListener(listener);
        faqMI.addActionListener(listener);
        this.setJMenuBar(bar);

        // Path panel
        pathPanel = new JPanel();
        pathPanel.setPreferredSize(new Dimension(200, 500));
        pathPanel.setLayout(new BorderLayout());
        this.add(pathPanel, BorderLayout.WEST);

        searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(200, 20));
        searchPanel.setLayout(new BorderLayout());
        pathPanel.add(searchPanel, BorderLayout.NORTH);

        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(200, 480));
        treePanel.setLayout(new BorderLayout());
        pathPanel.add(treePanel, BorderLayout.CENTER);

        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(200, 20));
        searchPanel.add(searchBar, BorderLayout.NORTH);

        treePanel.add(tree = new JTree());
        pathPanelScroller = new JScrollPane(tree);
        pathPanelScroller.setPreferredSize(new Dimension(200, 700));
        treePanel.add(pathPanelScroller, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        this.add(mainPanel, BorderLayout.CENTER);

        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(containerName = new JLabel("Container name", SwingConstants.CENTER), BorderLayout.NORTH);
        containerName.setFont(new Font("Calibri", Font.PLAIN, 20));
        mainPanel.add(containerPanel);

        itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.add(itemName = new JLabel("Item name", SwingConstants.CENTER), BorderLayout.NORTH);
        itemName.setFont(new Font("Calibri", Font.PLAIN, 20));
        mainPanel.add(itemPanel);

        itemList = new JList<>();
        itemList.setModel(itemModel = new DefaultListModel<>());
        itemModel.addElement(new Item("Yo-yo", 200, 1.20));
        itemModel.addElement(new Item("Dice", 3, 0.25));
        itemModel.addElement(new Item("Notebook", 1, 2.00));
        itemModel.addElement(new Item("Pencil", 16, 0.00));
        itemModel.addElement(new Item("Pencil sharpener", 1, 3.40));
        containerPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);

        itemPanel.add(statsPanel = new JPanel(), BorderLayout.CENTER);
        statsPanel.setLayout(new GridLayout(2,1));
        statsPanel.add(valuePanel = new JPanel(new GridLayout(2, 2)));
        statsPanel.add(notePanel = new JPanel(new BorderLayout()));

        valuePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        valuePanel.add(itemQuantity = new JLabel("Quantity:"));
        itemQuantity.setFont(new Font("Calibri", Font.PLAIN, 16));
        valuePanel.add(quantitySpinner = new JSpinner());
        quantitySpinner.setFont(new Font("Calibri", Font.PLAIN, 16));

        valuePanel.add(itemPrice = new JLabel("Unit price($):"));
        itemPrice.setFont(new Font("Calibri", Font.PLAIN, 16));
        valuePanel.add(priceSpinner = new JSpinner());
        priceSpinner.setFont(new Font("Calibri", Font.PLAIN, 16));

        notePanel.add(itemNotes = new JTextArea("Enter notes here"));



        this.setVisible(true);
    }

    public void changeUI(String lafName) {
        try {
            if (lafName.equalsIgnoreCase("Windows")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
            else if (lafName.equalsIgnoreCase("Metal")) {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            }
            else if (lafName.equalsIgnoreCase("Dark")) {
                UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme");
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LAF");
        }
    }
}
