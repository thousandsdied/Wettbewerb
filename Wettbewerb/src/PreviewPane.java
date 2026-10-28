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
