import java.awt.GridBagConstraints;

import javax.swing.JComponent;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class UniversalFunctions {

	public static void addToGrid(JComponent panel, JComponent component, int gridx, int gridy, int width, int height, double weightx, double weighty){
		GridBagConstraints gbcon = new GridBagConstraints();
		
		gbcon.fill = GridBagConstraints.BOTH;
		gbcon.gridx=gridx;
		gbcon.gridy=gridy;
		gbcon.gridwidth=width;
		gbcon.gridheight=height;
		gbcon.weightx=weightx;
		gbcon.weighty=weighty;
		
		panel.add(component,gbcon);	
	}	
}
