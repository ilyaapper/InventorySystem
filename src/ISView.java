/* File ISView.java
 * Author: Ilya Efremenko & Daniel Seamus Boots
 * Created on: 26 Apr 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings({"FieldMayBeFinal", "unused"}) // Suppressing the annoying "may be final" warnings
public class ISView extends JFrame {
    private ImageIcon icon;
    private JMenuBar bar;
    private JMenu fileMenu, itemMenu, viewMenu, helpMenu;
    private JMenuItem openMI, saveMI, saveAsMI, addMI, deleteMI, winLafMI, metalLafMI, darkLafMI, userManualMI, faqMI;
    private JPanel pathPanel, searchPanel, treePanel, mainPanel, containerPanel, itemPanel, statsPanel, valuePanel, notePanel;
    private JTextField searchBar;
    private JScrollPane pathPanelScroller;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode treeRoot;
    private JTree tree;
    private JLabel containerName, itemName, itemQuantity, itemPrice;
    private JList<Item> itemList;
    private DefaultListModel<Item> itemListModel;
    private JTextArea itemNotes;
    private JSpinner quantitySpinner, priceSpinner;

    public ISView(ActionListener actList, ListSelectionListener listList) {

        // Base frame
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

        // File tab
        fileMenu.add(openMI = new JMenuItem("Open"));
        openMI.addActionListener(actList);
        fileMenu.add(saveMI = new JMenuItem("Save"));
        fileMenu.add(saveAsMI = new JMenuItem("Save as"));

        // Item tab
        itemMenu.add(addMI = new JMenuItem("Add"));
        itemMenu.add(deleteMI = new JMenuItem("Delete"));
        addMI.addActionListener(actList);

        // View tab
        viewMenu.add(metalLafMI = new JMenuItem("Metal"));
        viewMenu.add(winLafMI = new JMenuItem("Windows"));
        viewMenu.add(darkLafMI = new JMenuItem("Dark"));
        winLafMI.addActionListener(actList);
        metalLafMI.addActionListener(actList);
        darkLafMI.addActionListener(actList);

        // Help tab
        helpMenu.add(userManualMI = new JMenuItem("User manual"));
        helpMenu.add(faqMI = new JMenuItem("FAQ"));
        userManualMI.addActionListener(actList);
        faqMI.addActionListener(actList);
        this.setJMenuBar(bar);

        // Path panel contains search bar, tree, and their respective panels
        pathPanel = new JPanel();
        pathPanel.setPreferredSize(new Dimension(200, 500));
        pathPanel.setLayout(new BorderLayout());
        this.add(pathPanel, BorderLayout.WEST);

        // Search bar and panel
        searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(200, 20));
        searchPanel.setLayout(new BorderLayout());
        pathPanel.add(searchPanel, BorderLayout.NORTH);
        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(200, 20));
        searchPanel.add(searchBar, BorderLayout.NORTH);

        // Scroller, tree, and panel
        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(200, 480));
        treePanel.setLayout(new BorderLayout());
        pathPanel.add(treePanel, BorderLayout.CENTER);
        treeRoot = new DefaultMutableTreeNode("Root");
        treeModel = new DefaultTreeModel(treeRoot);
        treePanel.add(tree = new JTree(treeModel));
        tree.setRootVisible(true);
        pathPanelScroller = new JScrollPane(tree);
        pathPanelScroller.setPreferredSize(new Dimension(200, 700));
        treePanel.add(pathPanelScroller, BorderLayout.NORTH);

        // Not sure?
        tree.getSelectionModel().addTreeSelectionListener(e -> {
            Inventory selInv = (Inventory) tree.getLastSelectedPathComponent();
            itemListModel.clear();
            for (Item item : selInv.getItems()) {
                itemListModel.addElement(item);
            }
        });

        // Middle panel containing list of items and item's attributes
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        this.add(mainPanel, BorderLayout.CENTER);

        // Container panel with list of contained items
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(containerName = new JLabel("Container name", SwingConstants.CENTER), BorderLayout.NORTH);
        containerName.setFont(new Font("Calibri", Font.PLAIN, 20));
        mainPanel.add(containerPanel);
        itemList = new JList<>();
        itemList.setModel(itemListModel = new DefaultListModel<>());
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(listList);
        containerPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);

        // Item info panel
        itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.add(itemName = new JLabel("Item name", SwingConstants.CENTER), BorderLayout.NORTH);
        itemName.setFont(new Font("Calibri", Font.PLAIN, 20));
        mainPanel.add(itemPanel);

        // TODO remove possibly extra panel
        itemPanel.add(statsPanel = new JPanel(), BorderLayout.CENTER);
        statsPanel.setLayout(new GridLayout(2,1));
        statsPanel.add(valuePanel = new JPanel(new GridLayout(2, 2)));
        statsPanel.add(notePanel = new JPanel(new BorderLayout()));

        // Value panel contains price and quantity of a given item
        valuePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        valuePanel.add(itemQuantity = new JLabel("Quantity:"));
        valuePanel.add(quantitySpinner = new JSpinner());
        valuePanel.add(itemPrice = new JLabel("Unit price($):"));
        valuePanel.add(priceSpinner = new JSpinner());
        itemQuantity.setFont(new Font("Calibri", Font.PLAIN, 16));
        quantitySpinner.setFont(new Font("Calibri", Font.PLAIN, 16));
        itemPrice.setFont(new Font("Calibri", Font.PLAIN, 16));
        priceSpinner.setFont(new Font("Calibri", Font.PLAIN, 16));

        // Note panel and text box
        notePanel.add(itemNotes = new JTextArea("Enter notes here"));
        this.setVisible(true);
    }

    // Change LAF (Look And Feel)
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

    // 999 getters ;)
    public ImageIcon getIcon() {
        return icon;
    }

    public JMenuBar getBar() {
        return bar;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JMenu getItemMenu() {
        return itemMenu;
    }

    public JMenu getViewMenu() {
        return viewMenu;
    }

    public JMenu getHelpMenu() {
        return helpMenu;
    }

    public JMenuItem getOpenMI() {
        return openMI;
    }

    public JMenuItem getSaveMI() {
        return saveMI;
    }

    public JMenuItem getSaveAsMI() {
        return saveAsMI;
    }

    public JMenuItem getAddMI() {
        return addMI;
    }

    public JMenuItem getDeleteMI() {
        return deleteMI;
    }

    public JMenuItem getWinLafMI() {
        return winLafMI;
    }

    public JMenuItem getMetalLafMI() {
        return metalLafMI;
    }

    public JMenuItem getDarkLafMI() {
        return darkLafMI;
    }

    public JMenuItem getUserManualMI() {
        return userManualMI;
    }

    public JPanel getPathPanel() {
        return pathPanel;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JPanel getTreePanel() {
        return treePanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }

    public JPanel getItemPanel() {
        return itemPanel;
    }

    public JPanel getStatsPanel() {
        return statsPanel;
    }

    public JPanel getValuePanel() {
        return valuePanel;
    }

    public JPanel getNotePanel() {
        return notePanel;
    }

    public JTextField getSearchBar() {
        return searchBar;
    }

    public JScrollPane getPathPanelScroller() {
        return pathPanelScroller;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public JTree getTree() {
        return tree;
    }

    public JLabel getContainerName() {
        return containerName;
    }

    public JLabel getItemName() {
        return itemName;
    }

    public JLabel getItemQuantity() {
        return itemQuantity;
    }

    public JLabel getItemPrice() {
        return itemPrice;
    }

    public JList<Item> getItemList() {
        return itemList;
    }

    public JTextArea getItemNotes() {
        return itemNotes;
    }

    public JSpinner getQuantitySpinner() {
        return quantitySpinner;
    }

    public JSpinner getPriceSpinner() {
        return priceSpinner;
    }

    public DefaultMutableTreeNode getTreeRoot() {
        return treeRoot;
    }
}
