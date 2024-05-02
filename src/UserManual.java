/* File UserManual.java
 * Author: Ilya Efremenko
 * Created on: 01 May 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;
import java.awt.*;

public class UserManual extends JFrame {
    ImageIcon icon;
    JLabel title;
    JTextArea textArea;

    public UserManual() {
        super("Inventory System: User Manual");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setMinimumSize(new Dimension(600, 450));
        this.setLayout(new BorderLayout());
        icon = new ImageIcon("ISIcon.png");
        this.setIconImage(icon.getImage());

        this.add(title = new JLabel("User Manual"), BorderLayout.NORTH);
        title.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(textArea = new JTextArea(), BorderLayout.CENTER);
        textArea.setEditable(false);
        textArea.setText("");

        this.setVisible(true);

    }
}
