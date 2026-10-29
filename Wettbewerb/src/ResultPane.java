import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

public class ResultPane extends JTextPane{
	StyledDocument styleDoc;
	
	public ResultPane(){
		setPreferredSize(new Dimension(425,300));
		
		styleDoc = new ResultDocument();
		
		setStyledDocument(styleDoc);
		
		setEditable(false);
	
		setBorder(BorderFactory.createTitledBorder("Visuelle Lösung"));
		
		setOpaque(false);
	}	
}
