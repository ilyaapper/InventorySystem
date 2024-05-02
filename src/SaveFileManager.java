/* File: SystemFile.java
 * Author: Seamus Daniel Boots
 * Created On: 26 Apr 2024
 * Copyright: Copyright 2024 Seamus Daniel Boots
 * Purpose:
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFileManager {
	
	final private String INV = "INV";
	final private String ITEM = "ITEM";
	final private String FIELD = "FIELD";
	
	private JFileChooser fileChooser;
	private Scanner scanner;
	
	public SaveFileManager() {
		this.fileChooser = new JFileChooser();
	}
	
	public void openSaveFile(ISController mainController) {
		/* returns true if the user selects a file, and
		 *   the file is a valid CSV,
		 *   contains the standard header,
		 *   and each line is a valid entry.
		 */
		
		this.fileChooser.showOpenDialog(null);
		
		File saveFile = this.fileChooser.getSelectedFile();
		
		try {
			
			this.scanner = new Scanner(saveFile);
			
			// a valid save must have the standard header
			if (scanner.hasNextLine()) {
				
				String header = scanner.nextLine();
				
				if (header.equals("type,level,name,value")) {
					
					// temp pointers from last iteration
					int lastLevel = -1;
					ArrayList<Inventory> invList = new ArrayList<>();
					Item lastItm = null;
					
					while (scanner.hasNextLine()) {
						
						// split the CSV line by comma
						String[] contents = scanner.nextLine().split(",", 5);
						
						String type = contents[0];
						String levelS = contents[1];
						int level = lastLevel;
						try {
							if (!levelS.isEmpty()) {
								level = Integer.parseInt(contents[1]);
							}
						} catch (NumberFormatException e) {
							return;
						}
						String name = contents[2];
						String valueStr = contents[3]; // will be parsed
						
						// Either a float, integer, bool, or string.
						ItemFieldType valueType = null;
						Object value = null;
						
						// value is only used when parsing fields
						
						if (type.equals(FIELD)) {
							// decimal (float) type
							try {
								value = Float.parseFloat(valueStr);
								valueType = ItemFieldType.DEC;
							} catch (NumberFormatException ignored) {}
							
							// integer type
							try {
								value = Integer.parseInt(valueStr);
								valueType = ItemFieldType.INT;
							} catch (NumberFormatException ignored) {}
							
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
							case FIELD -> lastItm.addField(new ItemField(name, valueType, value));
						}
						
					}
					
				}
				
			}
			
		} catch (FileNotFoundException |
		         NullPointerException |
		         IndexOutOfBoundsException e) {
			System.out.println(e.toString());
		}
		
	}
	
	private ItemField parseItemField(String[] contents) {
		return null;
	}
	
}
