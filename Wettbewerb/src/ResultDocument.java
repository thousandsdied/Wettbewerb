import javax.swing.JTextPane;
import javax.swing.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;

public class ResultDocument extends DefaultStyledDocument{
	
	SimpleAttributeSet sat;
	
	String word1,word2,word3,word4,pattern1,pattern2;
	
	String singleLetterFormat= " %s ";
	
	Color c1,c2,c3,c4,commonC;
	
	Color[] colours = {c1,c2,c3,c4};
	
	ResultDocument(){
		
		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");
		
		c1=c2=c3=c4=commonC=Color.BLACK;
		
		word1=word2=word3=word4=pattern1=pattern2="    ";

		fillResultPanel();
	}
	
	ResultDocument(Kreuzwortkreuz kwk, int index){
		
		String[] kwkResult = kwk.getPossibleCombinations().get(index);
		
		sat = new SimpleAttributeSet();
		
		StyleConstants.setFontFamily(sat, "Lucida Console");

		word1=kwkResult[0];
		word2=kwkResult[1];
		word3=kwkResult[2];
		word4=kwkResult[3];
		pattern1=kwk.getPatterns()[0];
		pattern2=kwk.getPatterns()[1];
		c1=Color.RED;
		c2=Color.BLUE;
		c3=Color.MAGENTA;
		c4=Color.GREEN;
		commonC=Color.BLACK;
		
		fillResultPanel();

	}
	
	private void fillResultPanel() {
		
		
		
		try{
			
			StyleConstants.setFontSize(sat,10);

			
			insertString(getLength(), "  ---------------------------------------------------------------------------------\n", sat);
			
			StyleConstants.setFontSize(sat,50);

			
			addText("#",commonC,true);
			
			addText(word1.substring(0,1),c1,true);
			
			addText(word2.substring(0,1),c2,true);
			
			addText("#",commonC,true);
			
			addText("|", commonC,false);
			
			StyleConstants.setFontSize(sat,10);
			
			insertString(getLength(), "\n  ---------------------------------------------------------------------------------\n", sat);
			
			StyleConstants.setFontSize(sat,50);
			
			addText(word3.substring(0,1),c3,true);
			
			if(pattern1.charAt(1)=='.') {
				addText(word1.substring(1,2),commonC,true);
			}
			else if (pattern1.charAt(1)==word1.charAt(1)) {
				addText("|", commonC,false);
				addText(word1.substring(1,2),c1,false);
				addText("/",commonC,false);
				addText(word3.substring(1,2),c3,false);
			}
			else {
				addText("|", commonC,false);
				addText(word3.substring(1,2),c3,false);
				addText("/",commonC,false);
				addText(word1.substring(1,2),c1,false);
			}
			
			
			if(pattern2.charAt(1)=='.') {
				addText(word2.substring(1,2),commonC,true);
			}
			else if (pattern2.charAt(1)==word2.charAt(1)) {
				addText("|", commonC,false);
				addText(word2.substring(1,2),c2,false);
				addText("/",commonC,false);
				addText(word3.substring(2,3),c3,false);
			}	
			else {
				addText("|", commonC,false);
				addText(word3.substring(2,3),c3,false);
				addText("/",commonC,false);
				addText(word2.substring(1,2),c2,false);
			}
			
			addText(word3.substring(3,4),c3,true);
			
			addText("|", commonC,false);
			
			StyleConstants.setFontSize(sat,10);

			addText("\n  ---------------------------------------------------------------------------------\n", commonC,false);
			
			StyleConstants.setFontSize(sat,50);
			
			addText(word4.substring(0,1),c4,true);
			
			if(pattern1.charAt(2)=='.') {
				addText(word1.substring(2,3),commonC,true);
			}
			else if (pattern1.charAt(2)==word1.charAt(2)) {
				addText("|", commonC,false);
				addText(word1.substring(2,3),c2,false);
				addText("/",commonC,false);
				addText(word4.substring(1,2),c4,false);
			}
			else {
				addText("|", commonC,false);
				addText(word4.substring(1,2),c4,false);
				addText("/",commonC,false);
				addText(word1.substring(2,3),c1,false);
			}
			
			if(pattern2.charAt(2)=='.') {
				addText(word2.substring(2,3),commonC,true);
			}
			else if (pattern2.charAt(1)==word2.charAt(1)) {
				addText("|", commonC,false);
				addText(word2.substring(2,3),c2,false);
				addText("/",commonC,false);
				addText(word4.substring(2,3),c4,false);
			}
			else {
				addText("|", commonC,false);
				addText(word4.substring(2,3),c4,false);
				addText("/",commonC,false);
				addText(word2.substring(2,3),c2,false);
			}
			
			addText(word4.substring(3,4),c4,true);
			
			addText("|", commonC,false);	
			
			StyleConstants.setFontSize(sat,10);
			
			addText("\n  ---------------------------------------------------------------------------------\n", commonC,false);
			
			StyleConstants.setFontSize(sat,50);
			
			addText("#",commonC,true);
			
			addText(word1.substring(3,4),c1,true);
			
			addText(word2.substring(3,4),c2,true);
			
			addText("#",commonC,true);
			
			addText("|", commonC,false);	
			
			StyleConstants.setFontSize(sat,10);
			
			addText("\n  ---------------------------------------------------------------------------------\n", commonC,false);
			
		}
		catch(BadLocationException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	private void addText (String s, Color c, boolean singleChar) {
		
		try {
			
			if(singleChar){
				StyleConstants.setForeground(sat, commonC);
				
				insertString(getLength(),"|", sat);
			}
			
			StyleConstants.setForeground(sat, c);
			
			if(singleChar) {
				insertString(getLength(),String.format(singleLetterFormat,s), sat);
			}
			else {insertString(getLength(),s, sat);
				
			}
			
			StyleConstants.setForeground(sat, commonC);
		}
		
		catch(BadLocationException e){
			e.printStackTrace();
		}

		
	}
	
}
