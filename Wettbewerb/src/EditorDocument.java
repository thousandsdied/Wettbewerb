import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.text.StyleConstants;


public class EditorDocument extends MyStyledDocument{

	JTextPane previewPane;
	
	int size = 65;
	
	boolean programChange;

	EditorPane editPane;

	EditorDocument(JTextPane previewPane, EditorPane editPane){
		
		this.editPane=editPane;
		
		programChange=true;
		
		this.previewPane = previewPane;
		
		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");
		
		StyleConstants.setFontSize(sat,size);
		
		addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e){
				checkIfValidKwk();
				
				
			}
			
			public void removeUpdate(DocumentEvent e) {
				checkIfValidKwk();
			
			}
			public void changedUpdate(DocumentEvent e){}
		});
		
		addText("#..#\n....\n....\n#..#\n");
		
		programChange=false;
	}
	

	//Überprüft, ob das Kwk-Muster stimmt und beschränkt die Eingabe
	private void checkIfValidKwk() {
		 Runnable formatText= new Runnable() {	
			public void run() {
				Scanner scanner = new Scanner(allText());

				String formattedText = "";
			
				for(int i = 0 ; i<4 ; i++) {	
					if (scanner.hasNext()) {
						String line = scanner.nextLine();
						formattedText += line.substring(0,Math.min(4,line.length()));
						if (i<3) {
							formattedText+="\n";	
						}
					}
				}	
				
				int caretPosition=editPane.getCaretPosition();
				programChange=true;
				
				try {
					remove(0,getLength());
					addText(formattedText);
				} 
				
				catch (BadLocationException e) {
					System.out.println("checkIfValidKwk: "+e);
				}
					
				programChange=false;
				editPane.setCaretPosition(Math.min(19, caretPosition));
				scanner.close();
			}	
		};
		if(!programChange) 
		SwingUtilities.invokeLater(formatText);
		
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
		programChange=true;
		
		try {	
			remove(0,getLength());	
		} 
		
		catch (BadLocationException e) {
			System.out.println("load file: "+e);
		}
		programChange=false;
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
		
		catch(Exception e) {
			System.out.println("load file: "+e);
		}
		
		
	}

	
	public String allText(){
		try {
			return getText(0,getLength());
		}
		
		catch(BadLocationException e ) { 
			System.out.println("allText: "+e);
			return "";
		}
	}
}