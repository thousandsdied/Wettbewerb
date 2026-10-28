import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;

public class SolveButton extends JButton{
	
	EditorDocument editDoc;
	
	JSpinner limitResultButton ;
	
	JPanel resultOptionsPanel;
	
	JTextPane resultPane;
	

	public SolveButton(EditorDocument editDoc,JSpinner limitResultButton,JPanel resultOptionsPanel,JTextPane resultPane) {
		
		this.editDoc=editDoc;
		this.limitResultButton=limitResultButton;
		this.resultOptionsPanel=resultOptionsPanel;
		this.resultPane=resultPane;
		
		setText("Löse Rätsel");
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				resultOptionsPanel.removeAll();
				
				if((Integer)limitResultButton.getValue()>0) {
				
					Kreuzwortkreuz kwk = new Kreuzwortkreuz(editDoc,(Integer)limitResultButton.getValue());
					
					for(int i = 0 ; i<(Integer) kwk.getPossibleCombinations().size() ; i++) {
						
						String[] result=kwk.getPossibleCombinations().get(i);
						
						JButton resultOptionButton = new JButton(result[0]+", "+result[1]+", "+result[2]+", "+result[3]);
						
						//resultOptionButton.setPreferredSize(new Dimension(100,40));
						
						int currentIndex = i;
						
						resultOptionButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								resultPane.setStyledDocument(new ResultDocument(kwk,currentIndex));
							}
						});
						UniversalFunctions.addToGrid(resultOptionsPanel, resultOptionButton, 0, i, 1, 1, 1, 1); 
						
						resultOptionsPanel.revalidate();
						resultOptionsPanel.repaint();
						resultPane.setStyledDocument(new ResultDocument(kwk,0));
						
					}
					
				}
				
				else {
					UniversalFunctions.addToGrid(resultOptionsPanel, new JLabel("Bitte gebe eine ganze positive Zahl an"), 0, 0, 1, 1, 1, 1); 
					resultOptionsPanel.revalidate();
					resultOptionsPanel.repaint();
				}
			}
		});
		
		
		
		
		
	}
	
	
}
