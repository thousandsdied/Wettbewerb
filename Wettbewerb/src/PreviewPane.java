import javax.swing.text.StyledDocument;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;



public class PreviewPane extends JTextPane{
	SimpleAttributeSet sat;
	
	StyledDocument styleDoc;
	
	PreviewPane(){	
		setPreferredSize(new Dimension(225,300));
		
		styleDoc = new PreviewDocument();
		
		setStyledDocument(styleDoc);
		
		setEditable(false);
		
		setBorder(BorderFactory.createTitledBorder("Vorschau"));
		
		setOpaque(false);	
	}
}
