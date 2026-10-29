import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class LoadButton extends JButton{
	JPanel mainPanel;
	EditorDocument editDoc;

	LoadButton(JPanel mainPanel, EditorDocument editDoc){
		setText("Lade Textdatei");
		this.mainPanel=mainPanel;
		this.editDoc=editDoc;
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
				
				if(fc.showOpenDialog(mainPanel)==JFileChooser.APPROVE_OPTION) {
					editDoc.loadFile(fc.getSelectedFile());

				}
			}
		});
	}	
}
