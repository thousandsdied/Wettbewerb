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


public class EditorDocument extends DefaultStyledDocument{

	SimpleAttributeSet sat;
	
	EditorDocument(){
		
		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");
		
		StyleConstants.setFontSize(sat,70);
		
		addText("#..#\n....\n....\n#..#");
		
	}
	
	public void loadFile(File file){
		
		try {	
			remove(0,getLength());	
		} 
		catch (BadLocationException e) {
			//System.out.println(e);
		}

		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");
		
		StyleConstants.setFontSize(sat,70);
		
		try(Scanner scanner = new Scanner(file)) {
		
			while (scanner.hasNextLine()) {
				
				for(char c : scanner.nextLine().toCharArray()) {
					
					addText(c+"");
					
				}
			addText("\n");
			}
		}
		catch(FileNotFoundException e) {
			//System.out.println(e);
		}
		
	}
		
	private void addText (String s) {
		try {
	
		insertString(getLength(),s,sat);
		
		}
		catch(BadLocationException e){
			//e.printStackTrace();
		}
	}
	
	public String allText(){
		try {
			return getText(0,getLength());
		}
		catch(BadLocationException e ) { 
			//System.out.println(e);
			return "";
		}
	}
	
}