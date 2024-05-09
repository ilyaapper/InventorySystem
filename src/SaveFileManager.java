/* File: SystemFile.java
 * Author: Seamus Daniel Boots & Ilya Efremenko
 * Created On: 26 Apr 2024
 * Copyright: Copyright 2024 Seamus Daniel Boots
 * Purpose:
 */

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFileManager {

    final private String HEADER = "type,level,name,value";

    final private String INV = "INV";
    final private String ITEM = "ITEM";
    final private String FIELD = "FIELD";

    private ISController mainController;

    private JFileChooser fileChooser;
    private Scanner scanner;

    public SaveFileManager(ISController mainController) {
        this.mainController = mainController;
        this.fileChooser = new JFileChooser();
    }

    public void openSaveFile() {
        /* successfully opens a save file if the user selects a file, and
         *   the file is a valid CSV,
         *   contains the standard header,
         *   and each line is a valid entry.
         */

        this.fileChooser.showOpenDialog(null);

        File saveFile = this.fileChooser.getSelectedFile();

        // the user pressed 'cancel'
        if (saveFile == null) {
            return;
        }

        try {

            this.scanner = new Scanner(saveFile);

            // a valid save must have the standard header
            if (scanner.hasNextLine()) {

                String header = scanner.nextLine();

                if (header.equals(HEADER)) {

                    // temp pointers from last iteration
                    int lastLevel = -1;
                    ArrayList<Inventory> invList = new ArrayList<>();
                    Item lastItm = null;

                    while (scanner.hasNextLine()) {

                        // split the CSV line by comma
                        String[] contents = scanner.nextLine().split(",", 5);

                        String type = contents[0];
                        String levelStr = contents[1]; // will be parsed properly
                        String name = contents[2];
                        String valueStr = contents[3]; // will be parsed properly

                        int level = lastLevel;
                        if (!levelStr.isEmpty()) { // different level than before
                            try {
                                level = Integer.parseInt(contents[1]);
                            } catch (NumberFormatException e) { return; }
                        }

                        // parse the actual entries line-by-line
                        // first piece of content should be the type
                        switch (type) {
                            case INV -> {
                                Inventory inv = new Inventory(name);
                                if (lastLevel == -1 && level == 0) { // root
                                    mainController.addInventory(inv);
                                    invList.add(inv);
                                    lastLevel++;
                                } else if (lastLevel + 1 == level) { // next level
                                    invList.get(lastLevel).addInv(inv);
                                    invList.add(inv);
                                    lastLevel++;
                                } else if (lastLevel == level) { // on the same level
                                    if (lastLevel - 1 == -1) { // root level
                                        mainController.addInventory(inv);
                                    } else { // non-root
                                        invList.get(lastLevel - 1).addInv(inv);
                                    }
                                    invList.remove(lastLevel);
                                    invList.add(inv);
                                } else { // lastLevel > level
                                    for (; lastLevel >= level; lastLevel--) {
                                        invList.remove(lastLevel);
                                    }
                                    if (lastLevel == -1 && level == 0) { // check for the root
                                        mainController.addInventory(inv);
                                    } else {
                                        invList.get(lastLevel).addInv(inv);
                                    }
                                    invList.add(inv);
                                    lastLevel++;
                                }
                            }
                            case ITEM -> {
                                lastItm = new Item(name);
                                invList.get(lastLevel).addItem(lastItm);
                            }
                            case FIELD -> {
                                ItemField itemField = parseItemField(name, valueStr);
                                lastItm.addField(itemField);
                            }
                        }

                    }

                }

            }

        } catch (FileNotFoundException |
                 NullPointerException |
                 IndexOutOfBoundsException e) {
            return;
        }

    }

    private ItemField parseItemField(String name, String valueStr) {
        Object value = null;
        ItemFieldType valueType = null;
        boolean valueIsANum = false;

        // decimal (float) type
        try {
            value = Float.parseFloat(valueStr);
            valueType = ItemFieldType.DEC;
            valueIsANum = true;
        } catch (NumberFormatException ignored) {}

        // integer type
        try {
            value = Integer.parseInt(valueStr);
            valueType = ItemFieldType.INT;
            valueIsANum = true;
        } catch (NumberFormatException ignored) {}

        if (!valueIsANum) {
            // boolean type
            switch (valueStr) {
                case "true" -> {
                    value = true;
                    valueType = ItemFieldType.BOOL;
                }
                case "false" -> {
                    value = false;
                    valueType = ItemFieldType.BOOL;
                }
                // string type
                default -> {
                    value = valueStr;
                    valueType = ItemFieldType.STRING;
                }
            }
        }
        return new ItemField(name, valueType, value);
    }

    public void saveSaveFile() {
        this.fileChooser.showSaveDialog(null);

        File saveFile = this.fileChooser.getSelectedFile();

        // the user pressed 'cancel', and so no file was chosen
        if (saveFile == null) {
            return;
        }

        // the output for the file writing
        PrintStream fileOut;
        try{
            fileOut = new PrintStream(saveFile);
        } catch (FileNotFoundException e) { return; }

        // print header
        fileOut.println(HEADER);

        // Start printing out the root inventories, and
        // recurse inside in order to write all the inventories
        writeInventories(fileOut, mainController.getInvList(), 0);
    }

    private void writeInventories(PrintStream fileOut,
                                  ArrayList<Inventory> invs,
                                  int level) {

        // write each inventory
        for (Inventory inv : invs) {
            // print the inventory entry
            fileOut.printf("%s,%d,%s,%n", INV, level, inv.getName());

            // print the item entries
            for (Item item : inv.getItems()) {
                fileOut.printf("%s,,%s,%n", ITEM, item.getName());

                // print the item field entries
                for (ItemField field : item.getFields()) {
                    fileOut.printf("%s,,%s,%s%n", FIELD, field.getName(), field.getValue());
                }

            }

            // print sub-inventory entries, if applicable
            writeInventories(fileOut, inv.getInventories(), level+1);

        }

    }

}
