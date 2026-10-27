import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;



public class Test {
		
	private static GridBagConstraints gbcon = new GridBagConstraints();
	
	public static void main(String[] args) {
		
		JFrame mainFrame = new JFrame("Frame");
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridBagLayout());

		JTextPane resultPane = new JTextPane(new ResultDocument());
		
		resultPane.setPreferredSize(new Dimension(325,275));
		
		resultPane.setEditable(false);

		
		
		EditorDocument editDoc = new EditorDocument();
		
		PreviewDocument prevDoc = new PreviewDocument(editDoc.allText());
		
		JTextPane previewPane = new JTextPane();
		
		previewPane.setStyledDocument(prevDoc);
		
		previewPane.setEditable(false);
		
		
		
		editDoc.addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e){checkIfValidKwk();}

			public void removeUpdate(DocumentEvent e){checkIfValidKwk();}
			
			public void changedUpdate(DocumentEvent e){checkIfValidKwk();}
			
			private void checkIfValidKwk() {
				
				String validLetters = "[A-ZÖAÜ.]";
					
				Pattern p1 = Pattern.compile(validLetters+validLetters+validLetters+validLetters);
				Pattern p2 = Pattern.compile("#"+validLetters+validLetters+"#");
				try {
				
					if(p2.matcher(editDoc.getText(0,4)).matches() && p1.matcher(editDoc.getText(5,4)).matches() && p1.matcher(editDoc.getText(10,4)).matches() && p2.matcher(editDoc.getText(15,4)).matches()) {

						previewPane.setStyledDocument(new PreviewDocument(editDoc.allText()));
						
					}
				}
				//Wirft Exception wenn Datei geladen wird, kann ignoriert werden
				catch(BadLocationException e) {}
			
			}
		});
		
		JTextPane inputPane = new JTextPane(editDoc);
		
		inputPane.setPreferredSize(new Dimension(150,250));
		
		
		
			
	
		
		

		JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
		
		JButton loadKwkButton = new JButton("Load");
		
		JButton solveKwk = new JButton("Solve");
		
		JLabel limitResultText = new JLabel("Nummer an maximalen Lösungen:");
		
		JSpinner limitResultButton = new JSpinner();
		
		limitResultButton.setValue(10);
		
		JPanel resultOptionsPanel = new JPanel();
		
		resultOptionsPanel.setLayout(new GridBagLayout());
		
		JScrollPane resultOptionsScroller = new JScrollPane(resultOptionsPanel);
		
		resultOptionsScroller.setPreferredSize(new Dimension(125,200));
		
		resultOptionsScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		loadKwkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fc.showOpenDialog(mainPanel)==JFileChooser.APPROVE_OPTION) {
					editDoc.loadFile(fc.getSelectedFile());

				}
			}
		});

		solveKwk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				resultOptionsPanel.removeAll();
				
				Kreuzwortkreuz kwk = new Kreuzwortkreuz(editDoc);
				
				int limit = Math.min((Integer) limitResultButton.getValue(),kwk.getPossibleCombinations().size());
				
				for(int i = 0 ; i<limit ; i++) {
					
					String[] result=kwk.getPossibleCombinations().get(i);
					
					System.out.println(result[0]+", "+result[1]+", "+result[2]+", "+result[3]);
					
					JButton resultOptionButton = new JButton(result[0]+", "+result[1]+", "+result[2]+", "+result[3]);
					
					resultOptionButton.setPreferredSize(new Dimension(100,40));
					
					int currentIndex = i;
					
					resultOptionButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							resultPane.setStyledDocument(new ResultDocument(kwk,currentIndex));
						}
					});
					gbcon.fill = GridBagConstraints.BOTH;
					addToGrid(resultOptionsPanel, resultOptionButton, 0, i, 1, 1, 1, 1); 
					gbcon.fill = GridBagConstraints.BOTH;
					System.out.println(resultOptionsPanel.getPreferredSize());
					resultPane.setStyledDocument(new ResultDocument(kwk,0));
					
					
				
				}
			}
		});
		
		
		
		
		
		
		gbcon.fill = GridBagConstraints.BOTH;
		
		JPanel inputButtonsPanel = new JPanel();
		inputButtonsPanel.setLayout(new GridBagLayout());
		addToGrid(inputButtonsPanel,loadKwkButton,0,0,2,1,1,1);
		addToGrid(inputButtonsPanel,solveKwk,0,1,2,1,1,1);
		addToGrid(inputButtonsPanel,limitResultText,0,3,1,1,1,1);
		addToGrid(inputButtonsPanel,limitResultButton,1,3,1,1,1,1);
		
		
		addToGrid(mainPanel,inputPane,0,0,1,1,1,1);

		addToGrid(mainPanel,inputButtonsPanel,1,0,1,1,1,1);
		
		addToGrid(mainPanel,resultOptionsScroller,0,3,3,1,1,1);


		addToGrid(mainPanel,previewPane,3,0,1,1,1,1);
		
		addToGrid(mainPanel,resultPane,3,3,1,1,1,1);
	
		
	
		mainFrame.add(mainPanel);
		
		mainFrame.setSize(1000, 600);	
		
		mainFrame.setVisible(true);
		
		mainFrame.setResizable(false);
		
	}
	
	private static void addToGrid(JComponent panel, JComponent component, int gridx, int gridy, int width, int height, double weightx, double weighty){

		gbcon.gridx=gridx;
		gbcon.gridy=gridy;
		gbcon.gridwidth=width;
		gbcon.gridheight=height;
		gbcon.weightx=weightx;
		gbcon.weighty=weighty;
		
		panel.add(component,gbcon);
		
	}
	
}
