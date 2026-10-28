import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;
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
import javax.swing.border.BevelBorder;
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

		
		//Zeigt eine mögliche Lösung visuell an
		ResultPane resultPane = new ResultPane();
		
		//Zeigt das Kwk visuall an
		PreviewPane previewPane = new PreviewPane();
		
		//Bearbeibares Textfeld, mit dem man manuell das Kwk bearbeiten kann
		EditorPane editorPane = new EditorPane(previewPane);

		//Limitiert die Anzahl an ausgegebenen Lösungen, um Zeit/Ressourcen zu sparen
		JLabel limitResultText = new JLabel("Nummer an maximalen Lösungen:");
		JSpinner limitResultButton = new JSpinner();
		limitResultButton.setValue(10);
		
		//Zeigt mögliche Lösungen als 4 Wörter an, raufclicken um visuelle Lösung anzuzeigen
		JPanel resultOptionsPanel = new JPanel();
		resultOptionsPanel.setLayout(new GridBagLayout());
		JScrollPane resultOptionsScroller = new JScrollPane(resultOptionsPanel);
		resultOptionsScroller.setBorder(BorderFactory.createTitledBorder("Lösungsauswahl"));
		resultOptionsScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Lädt txt-Dateien in den Editor ein zum schnellen Wechsel zwischen Kwks
		JButton loadKwkButton = new LoadButton(mainPanel,(EditorDocument) editorPane.getStyledDocument());

		//Löst den Lösungsalgorithmus aus
		JButton solveKwk = new SolveButton((EditorDocument) editorPane.getStyledDocument(), limitResultButton, resultOptionsPanel, resultPane);
		
		JPanel inputButtonsPanel = new JPanel();
		inputButtonsPanel.setLayout(new GridBagLayout());
		
		//Formatierungen für Swing
		UniversalFunctions.addToGrid(inputButtonsPanel,loadKwkButton,0,0,2,1,1,1);
		UniversalFunctions.addToGrid(inputButtonsPanel,solveKwk,0,1,2,1,1,1);
		UniversalFunctions.addToGrid(inputButtonsPanel,limitResultText,0,3,1,1,1,1);
		UniversalFunctions.addToGrid(inputButtonsPanel,limitResultButton,1,3,1,1,1,1);	
		
		UniversalFunctions.addToGrid(mainPanel,editorPane,0,0,1,1,1,1);
		UniversalFunctions.addToGrid(mainPanel,resultOptionsScroller,0,3,3,1,1,1);
		UniversalFunctions.addToGrid(mainPanel,inputButtonsPanel,1,0,1,1,1,1);		
		UniversalFunctions.addToGrid(mainPanel,previewPane,3,0,1,1,1,1);		
		UniversalFunctions.addToGrid(mainPanel,resultPane,3,3,1,1,1,1);
	
		
	
		mainFrame.add(mainPanel);
		mainFrame.setSize(1000, 600);	
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);

		
	}

}
