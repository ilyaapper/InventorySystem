/* File UserManualView.java
 * Author: Ilya Efremenko
 * Created on: 01 May 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings({"FieldMayBeFinal", "unused"}) // Suppressing the annoying "may be final" warnings
public class UserManualView extends JFrame {
    private JLabel pageLabel;
    private JTextArea textArea;
    private JPanel controlPanel;
    private JButton nextButton, previousButton;
    private int page;

    public UserManualView(ActionListener listener) {
        super("Inventory System: User Manual");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setMinimumSize(new Dimension(600, 450));
        this.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("ISIcon.png");
        this.setIconImage(icon.getImage());

        // Page title
        JLabel title;
        this.add(title = new JLabel("User Manual: Getting Started"), BorderLayout.NORTH);
        title.setFont(new Font("Calibri", Font.BOLD, 20));

        // Page text
        this.add(textArea = new JTextArea(), BorderLayout.CENTER);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(new Font("Calibri", Font.PLAIN, 17));
        textArea.setText("      Inventory System is a free inventory management software. After opening the application," +
                " click the \"File\" tab and select \"Open\" from the dropdown menu to load the pre-existing inventory" +
                " save file. (At this stage, our software works with CSV files only!) Once you select the right save" +
                " file, the Inventory System will automatically import all containers and their contents. Now, you are" +
                " ready to track your inventory! In case you did not have a pre-existing save file, feel free to skip" +
                " the instructions above and go straight into action! ");

        // Page number and controls
        this.add(controlPanel = new JPanel(new GridLayout(1 ,3)), BorderLayout.SOUTH);
        controlPanel.add(previousButton = new JButton("Previous"));
        controlPanel.add(pageLabel = new JLabel(Integer.toString(page = 1)));
        controlPanel.add(nextButton = new JButton("Next"));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        previousButton.addActionListener(listener);
        nextButton.addActionListener(listener);


        this.setVisible(true);
    }

    public JLabel getPageLabel() {
        return pageLabel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JPanel getControlPanel() {
        return controlPanel;
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
