import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;


public class EditorDocument extends DefaultStyledDocument{

	SimpleAttributeSet sat;
	
	JTextPane previewPane;
	
	int size = 65;
	
	public void setPreviewPane(JTextPane previewPane) {
		this.previewPane = previewPane;
	}

	EditorDocument(){
		
		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");
		
		StyleConstants.setFontSize(sat,size);
		
		addText("#..#\n....\n....\n#..#\n");
		
		addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e){checkIfValidKwk();}

			public void removeUpdate(DocumentEvent e){checkIfValidKwk();}
			
			public void changedUpdate(DocumentEvent e){}
		
		});
		
	}
	
	
	private void checkIfValidKwk() {
		
		String validLetters = "[A-Za-zÖÄÜöäü.]";
			
		Pattern p1 = Pattern.compile(validLetters+validLetters+validLetters+validLetters);
		Pattern p2 = Pattern.compile("#"+validLetters+validLetters+"#");
		try {
		
			if(p2.matcher(getText(0,4)).matches() && p1.matcher(getText(5,4)).matches() && p1.matcher(getText(10,4)).matches() && p2.matcher(getText(15,4)).matches()) {

				previewPane.setStyledDocument(new PreviewDocument(allText()));
				
			}
		}
		//Wirft Exception wenn Datei geladen wird, kann ignoriert werden
		catch(BadLocationException e) {}
	
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
		
		StyleConstants.setFontSize(sat,size);
		
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