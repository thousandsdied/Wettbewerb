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

public class PreviewDocument extends MyStyledDocument{

	final int LETTERSIZE = 50;
	final int DASHSIZE = 10;
	
	
	PreviewDocument(String editor){
		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");
		
		try (Scanner scanner = new Scanner(editor)) {
			
			StyleConstants.setFontSize(sat,DASHSIZE);

			addText("  ---------------------------------------------------------------------------------");
			
			while (scanner.hasNextLine()) {
		
				addText("\n");

				StyleConstants.setFontSize(sat,LETTERSIZE);
						
				addText("|");
					
				for(char c : scanner.nextLine().toCharArray()) {		
					addText(" "+Character.toUpperCase(c)+" |");		
				}
				
				StyleConstants.setFontSize(sat,DASHSIZE);
				
				addText("\n  ---------------------------------------------------------------------------------");	
			}
		}
	}
	
	
	PreviewDocument(){	
	}
}
