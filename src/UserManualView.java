/* File UserManualView.java
 * Author: Ilya Efremenko & Seamus Boots
 * Created on: 01 May 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings({"FieldMayBeFinal", "unused"}) // Suppressing the annoying "may be final" warnings
public class UserManualView extends JFrame {
    private JLabel pageLabel, title;
    private JTextArea textArea;
    private JPanel controlPanel;
    private JButton nextButton, previousButton;
    private int page;

    // Constructor
    public UserManualView(ActionListener listener) {
        super("Inventory System: User Manual");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setMinimumSize(new Dimension(600, 450));
        this.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("ISIcon.png");
        this.setIconImage(icon.getImage());
        this.page = 1;

        // Page title
        this.add(title = new JLabel(), BorderLayout.NORTH);
        title.setFont(new Font("Calibri", Font.BOLD, 20));

        // Page text
        this.add(textArea = new JTextArea(), BorderLayout.CENTER);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(new Font("Calibri", Font.PLAIN, 17));

        // Page number and controls
        this.add(controlPanel = new JPanel(new GridLayout(1 ,3)), BorderLayout.SOUTH);
        controlPanel.add(previousButton = new JButton("Previous"));
        controlPanel.add(pageLabel = new JLabel());
        controlPanel.add(nextButton = new JButton("Next"));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Inserting page 1 values
        openPage1();

        // Listeners
        previousButton.addActionListener(listener);
        nextButton.addActionListener(listener);
        this.setVisible(true);
    }

    // Page 1 values
    private void openPage1() {
        title.setText("User Manual: Getting Started");
        textArea.setText("Inventory System is a free inventory management software. After opening the application," +
                " click the \"File\" tab and select \"Open\" from the dropdown menu to load the pre-existing inventory" +
                " save file. (At this stage, our software works with CSV files only!) Once you select the right save" +
                " file, the Inventory System will automatically import all containers and their contents. Now, you are" +
                " ready to track your inventory! In case you did not have a pre-existing save file, feel free to skip" +
                " the instructions above and go straight into action!");
        pageLabel.setText(Integer.toString(page));
    }

    // Page 2 values
    private void openPage2() {
        title.setText("User Manual: Navigation");
        textArea.setText("To navigate the program, use the Navigation Tree on the left side of the screen. The" +
                " navigation tree is very similar to your computer's file system. There are folders and files. Folders" +
                " are real-life containers, and files are real-life items. For example, if you have a toolbox with a" +
                " wrench and a screwdriver in it, then inside our program, you may find the \"toolbox\" folder, which" +
                " will contain \"wrench\" and \"screwdriver\" files. Double-click on any folder of interest to see its" +
                " contents. Additionally, containers can have sub-containers in them. For example, your toolbox can" +
                " have a bag (of screws) in it. Thus \"toolbox\" folder can have a \"bag\" sub-folder in it along with" +
                " other items. You may also notice that after opening a container with items, a list of those items" +
                " will be displayed in the middle panel of the program. This list will only contain items (not" +
                " containers). Upon clicking on one of the items in the list, you will see the item's price, quantity," +
                " and notes below the list panel.");
        pageLabel.setText(Integer.toString(page));
    }

    // Page 3 values
    private void openPage3() {
        title.setText("User Manual: Item Management");
        textArea.setText("To add a new item, click on the \"Item\" tab. In the drop-down menu, select \"Add.\" You" +
                " will be prompted to select an object type (item or a container). If you choose the latter, enter" +
                " just the container name. If you choose the former, enter the item name, price, and quantity. The" +
                " newly created item/container will appear inside the selected folder in the tree. Another option" +
                " under the Item tab is \"Delete,\" which will delete the selected item/container. If you would like" +
                " to edit an existing item, select it in the item list. Below the list, you can edit the chosen" +
                " item's quantity, price, and notes. To change any of those fields, delete old values and type in new" +
                " ones. Alternatively, you may use little arrow buttons next to values to increment or decrease price" +
                " and quantity. If you make a mistake, click the \"File\" tab and open the same save file you loaded" +
                " originally. The Inventory System does not save automatically. You will lose any changes unless you" +
                " click \"Save\" in the \"File\" tab.");
        pageLabel.setText(Integer.toString(page));
    }

    // Page 4 values
    private void openPage4() {
        title.setText("User Manual: Miscellaneous");
        textArea.setText("You may change how our program looks by clicking the \"View\" tab and selecting one of three" +
                " standard themes. The Metal theme is a default design that emulates a metallic appearance. The Windows" +
                " theme mimics the appearance of a standard Windows Operating System design. The Dark theme produces a" +
                " modern dark appearance that may reduce eye strain from using the program. Additionally, you may click" +
                " on the \"Help\" tab and choose to read this manual or go through the frequently asked questions.");
        pageLabel.setText(Integer.toString(page));
    }

    // Increment page
    public void flipPage() {
        if (page < 4) {
            page++;
            choosePage();
        }
    }
    // Decrement page
    public void unflipPage() {
        if (page > 1) {
            page--;
            choosePage();
        }
    }

    // Choose and update page
    private void choosePage() {
        switch (page) {
            case 1:
                openPage1();
                break;
            case 2:
                openPage2();
                break;
            case 3:
                openPage3();
                break;
            case 4:
                openPage4();
                break;
        }
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPreviousButton() {
        return previousButton;
    }

    public int getPage() {
        return page;
    }
}
