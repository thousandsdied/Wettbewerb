import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;

public class MyStyledDocument extends DefaultStyledDocument{
	SimpleAttributeSet sat;
	
	protected void addText (String s) {
		try {
		insertString(getLength(),s,sat);	
		}
		
		catch(BadLocationException e){
			System.out.println("addText: "+e);
		}
	}	
}
