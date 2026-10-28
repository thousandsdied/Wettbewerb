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


public class EditorPane extends JTextPane{
	EditorDocument styleDoc;

	EditorPane(PreviewPane prevPane){
		setPreferredSize(new Dimension(425,300));
		
		styleDoc = new EditorDocument(prevPane,this);
		
		setStyledDocument(styleDoc);
		
		setBorder(BorderFactory.createTitledBorder("Eingabe"));	
	}
}