/* File ISController.java
 * Author: Ilya Efremenko
 * Created on: 26 Apr 2024
 * Purpose:
 * Notes:
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ISController implements ActionListener {

    public ISView view;

    public ISController() {
        view = new ISView(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
