import javax.swing.JTextPane;
import javax.swing.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;

public class PreviewDocument extends DefaultStyledDocument{

	SimpleAttributeSet sat;
	
	PreviewDocument(String editor){
		
		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");
		
		try (Scanner scanner = new Scanner(editor)) {

			
			while (scanner.hasNextLine()) {

				StyleConstants.setFontSize(sat,10);
					
				addText("\n  ---------------------------------------------------------------------------------\n");

				StyleConstants.setFontSize(sat,50);
						
				addText("|");
					
				for(char c : scanner.nextLine().toCharArray()) {
						
					addText(" "+c+" |");
						
				}
				
			}
		}
		
		StyleConstants.setFontSize(sat,10);	
		addText("\n  ---------------------------------------------------------------------------------");
			
		
		
	}
		
	private void addText (String s) {
		try {
	
		insertString(getLength(),s,sat);
		
		}
		catch(BadLocationException e){
			System.out.println("test");
		}
	}
	
	public boolean checkValidInput() {
		
		return true;
		
	}
	
}
