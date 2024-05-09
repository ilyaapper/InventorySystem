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
    final private String PLACEHOLD_ITEM_NAME = "[Item Name]";
    final private String PLACEHOLD_CONT_NAME = "[Container Name]";

    private ImageIcon icon;
    private JMenuBar bar;
    private JMenu fileMenu, itemInvMenu, viewMenu, helpMenu;
    private JMenuItem openMI, saveAsMI, addMI, deleteMI, winLafMI, metalLafMI, darkLafMI, userManualMI;
    private JMenuItem confirmMI;
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

    public ISView(ActionListener actListen,
                  TreeSelectionListener treeListen,
                  ListSelectionListener listListen) {

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
        bar.add(itemInvMenu = new JMenu("Item/Inv"));
        bar.add(viewMenu = new JMenu("View"));
        bar.add(helpMenu = new JMenu("Help"));

        // File tab
        fileMenu.add(openMI = new JMenuItem("Open"));
        openMI.addActionListener(actListen);
        fileMenu.add(saveAsMI = new JMenuItem("Save as"));
        saveAsMI.addActionListener(actListen);

        // Item tab
        itemInvMenu.add(addMI = new JMenuItem("Add"));
        itemInvMenu.add(deleteMI = new JMenuItem("Delete"));
        itemInvMenu.add(confirmMI = new JMenuItem("Confirm Fields"));
        addMI.addActionListener(actListen);
        deleteMI.addActionListener(actListen);
        confirmMI.addActionListener(actListen);

        // View tab
        viewMenu.add(metalLafMI = new JMenuItem("Metal"));
        viewMenu.add(winLafMI = new JMenuItem("Windows"));
        viewMenu.add(darkLafMI = new JMenuItem("Dark"));
        winLafMI.addActionListener(actListen);
        metalLafMI.addActionListener(actListen);
        darkLafMI.addActionListener(actListen);

        // Help tab
        helpMenu.add(userManualMI = new JMenuItem("User manual"));
        userManualMI.addActionListener(actListen);
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
        treeRoot = new DefaultMutableTreeNode("ROOT");
        treeModel = new DefaultTreeModel(treeRoot);
        treePanel.add(tree = new JTree(treeModel));
        tree.setRootVisible(false);
        pathPanelScroller = new JScrollPane(tree);
        pathPanelScroller.setPreferredSize(new Dimension(200, 700));
        treePanel.add(pathPanelScroller, BorderLayout.NORTH);

        // allow the tree's nodes to be interactive
        tree.getSelectionModel().addTreeSelectionListener(treeListen);

        // Middle panel containing list of items and item's attributes
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        this.add(mainPanel, BorderLayout.CENTER);

        // Container panel with list of contained items
        containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(containerName = new JLabel(PLACEHOLD_CONT_NAME, SwingConstants.CENTER), BorderLayout.NORTH);
        containerName.setFont(new Font("Calibri", Font.PLAIN, 20));
        mainPanel.add(containerPanel);
        itemList = new JList<>();
        itemList.setModel(itemListModel = new DefaultListModel<>());
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(listListen);
        containerPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);

        // Item info panel
        itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.add(itemName = new JLabel(PLACEHOLD_ITEM_NAME, SwingConstants.CENTER), BorderLayout.NORTH);
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
        quantitySpinner.setModel(new SpinnerNumberModel(0,0,Math.pow(2,32),1));
        valuePanel.add(itemPrice = new JLabel("Unit price($):"));
        valuePanel.add(priceSpinner = new JSpinner());
        priceSpinner.setModel(new SpinnerNumberModel(0f,0f,Math.pow(2,16),0.5));

        itemQuantity.setFont(new Font("Calibri", Font.PLAIN, 16));
        quantitySpinner.setFont(new Font("Calibri", Font.PLAIN, 16));
        itemPrice.setFont(new Font("Calibri", Font.PLAIN, 16));
        priceSpinner.setFont(new Font("Calibri", Font.PLAIN, 16));

        // Note panel and text box
        notePanel.add(itemNotes = new JTextArea("Enter notes here"));
        this.setVisible(true);
        disableItemFields(); // by default, disable the item fields
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

    public void hideTree() {
        this.tree.setRootVisible(false);
    }

    public void showTree() {
        this.tree.setRootVisible(true);
    }

    public void clearCurrentItemFields() {
        this.itemName.setText(PLACEHOLD_ITEM_NAME);
        this.quantitySpinner.setValue(0);
        this.priceSpinner.setValue(0f);
        this.itemNotes.setText("");
    }

    public void disableItemFields() {
        this.quantitySpinner.setEnabled(false);
        this.priceSpinner.setEnabled(false);
        this.itemNotes.setEnabled(false);
    }
    public void enableItemFields() {
        this.quantitySpinner.setEnabled(true);
        this.priceSpinner.setEnabled(true);
        this.itemNotes.setEnabled(true);
    }
    public void setQuantity(int quantity) {
        this.quantitySpinner.setValue(quantity);
    }
    public void setPrice(float price) {
        this.priceSpinner.setValue(price);
    }
    public void setNotes(String notes) {
        this.itemNotes.setText(notes);
    }
    public void clearCurrentItemList() {
        this.itemListModel.clear();
    }
    public void addToCurrentItemList(Item item) {
        this.itemListModel.addElement(item);
    }
    public Inventory getCurrentlySelInv() {
        return (Inventory) tree.getLastSelectedPathComponent();
    }
    public Item getCurrentlySelItem() {
        return this.itemList.getSelectedValue();
    }

    public void delSelItem() {
        // delete the currently selected item inside the currently
        // selected inventory
        this.getCurrentlySelInv().delItem(getCurrentlySelItem());
    }

    public boolean isTreeSelectionEmpty() {
        return this.tree.isSelectionEmpty();
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
        return itemInvMenu;
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

    public JMenuItem getSaveAsMI() {
        return saveAsMI;
    }

    public JMenuItem getAddMI() {
        return addMI;
    }

    public JMenuItem getDeleteMI() {
        return deleteMI;
    }

    public JMenuItem getConfirmMI() {
        return confirmMI;
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

    public void setSelContName(String contName) {
        this.containerName.setText(contName);
    }

    public JLabel getItemName() {
        return itemName;
    }

    public void setSelItemName(String itemName) {
        this.itemName.setText(itemName);
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

    public DefaultListModel<Item> getItemListModel() {
        return itemListModel;
    }

    public JTextArea getItemNotes() {
        return itemNotes;
    }

    public JSpinner getQuantitySpinner() {
        return quantitySpinner;
    }
    public int getQuantity() {
        return (Integer)quantitySpinner.getValue();
    }

    public JSpinner getPriceSpinner() {
        return priceSpinner;
    }
    public float getPrice() {
        return (Float)priceSpinner.getValue();
    }

    public String getNotes() {
        return itemNotes.getText();
    }

    public DefaultMutableTreeNode getTreeRoot() {
        return treeRoot;
    }
}
