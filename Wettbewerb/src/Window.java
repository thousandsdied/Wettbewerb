import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;


public class Window extends JFrame{	
	public Window() {
		setTitle("Kreuzwortkreuze");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new GridBagLayout());

		//Zeigt eine mögliche Lösung visuell an
		ResultPane resultPane = new ResultPane();
		
		//Zeigt das Kreuzwortkreuz visuall an
		PreviewPane previewPane = new PreviewPane();
		
		//Bearbeibares Textfeld, mit dem man manuell das Kreuzwortkreuz bearbeiten kann
		EditorPane editorPane = new EditorPane(previewPane);

		//Limitiert die Anzahl an ausgegebenen Lösungen, um Zeit/Ressourcen zu sparen
		JLabel limitResultText = new JLabel("Nummer an maximalen Lösungen:");
		JSpinner limitResultButton = new JSpinner();
		limitResultButton.setValue(10);
		
		//Zeigt mögliche Lösungen als Button mit den vier Lösungswörtern an, raufclicken um visuelle Lösung anzuzeigen
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

		add(mainPanel);
		setSize(1000,600);
		setResizable(false);	
	}
}
