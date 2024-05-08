/* File Test.java
 * Author: Ilya Efremenko & Daniel Seamus Boots
 * Created on: 26 Apr 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;

// For current test runs
public class Test implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Test());
    }
    @Override
    public void run() {
        new ISController();
    }
}
