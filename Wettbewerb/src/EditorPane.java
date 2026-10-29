import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import java.awt.Dimension;


public class EditorPane extends JTextPane{
	EditorDocument styleDoc;

	EditorPane(PreviewPane prevPane){
		setPreferredSize(new Dimension(425,300));
		
		styleDoc = new EditorDocument(prevPane,this);
		
		setStyledDocument(styleDoc);
		
		setBorder(BorderFactory.createTitledBorder("Eingabe"));	
	}
}