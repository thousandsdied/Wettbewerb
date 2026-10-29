import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Kreuzwortkreuz {
	private EditorDocument kreuzEditor;
	
	private String[] readIn = new String[4];
	
	private String[] patterns = new String[4];
	
	private ArrayList<String[]> possibleCombinations = new ArrayList<String[]>();
	
	private int limit;
	
	public Kreuzwortkreuz(EditorDocument kreuzEditor, int limit) {
		this.kreuzEditor=kreuzEditor;
		this.limit=limit;
		read();
		createPatterns();
		createPossibleWordLists();
		findWords();
	}	
	
	public ArrayList<String[]> getPossibleCombinations(){return possibleCombinations;}
	
	public String[] getPatterns() {return patterns;}
	
	//Einlesen der gegebenen Datei. Gibt die Zeilen der Datei als String-Array wieder.
	
	public void read() {	
		String[] readIn = new String[4];
		
		try (Scanner scanner = new Scanner(kreuzEditor.allText())) {
			int i = 0;
				
			while (scanner.hasNextLine()) {
				readIn[i] = scanner.nextLine().toUpperCase();
				i++;
			}
		}	
		
		this.readIn=readIn;
	}
	
	//Erstellt aus dem Array, der aus der Datei gemacht wurde, patterns, die man zum späteren matchen mit der Wortliste benutzen kann.
	
	private void createPatterns() {
		String pattern1 = String.valueOf(readIn[0].charAt(1))+String.valueOf(readIn[1].charAt(1))+String.valueOf(readIn[2].charAt(1))+String.valueOf(readIn[3].charAt(1));
		String pattern2 = String.valueOf(readIn[0].charAt(2))+String.valueOf(readIn[1].charAt(2))+String.valueOf(readIn[2].charAt(2))+String.valueOf(readIn[3].charAt(2));
		String pattern3 = String.valueOf(readIn[1].charAt(0))+String.valueOf(readIn[1].charAt(1))+String.valueOf(readIn[1].charAt(2))+String.valueOf(readIn[1].charAt(3));
		String pattern4 = String.valueOf(readIn[2].charAt(0))+String.valueOf(readIn[2].charAt(1))+String.valueOf(readIn[2].charAt(2))+String.valueOf(readIn[2].charAt(3));
		
		String[] patternArray = {pattern1,pattern2,pattern3,pattern4};
		
		patterns=patternArray;
	}
	
	//Erstellt für jedes Wort eine ArrayList mit möglichen Wörtern. Dabei können Wörter, bei denen Anfangs- und Endbuchstaben nicht passen, direkt aussortiert werden.
	
	private ArrayList<ArrayList<String>> createPossibleWordLists(){	
		ArrayList<ArrayList<String>> wordLists = new ArrayList<ArrayList<String>>();
		
		for (int i = 0 ; i<4 ; i++) {	
			try(Scanner scanner = new Scanner(new File("wortliste.txt"))) {	
				wordLists.add(new ArrayList<String>());
				
				while (scanner.hasNextLine()) {		
					String scanned = scanner.nextLine();
					
					if(!(patterns[i].charAt(0)!='.' && patterns[i].charAt(0)!=scanned.charAt(0) || patterns[i].charAt(3)!='.' && patterns[i].charAt(3)!=scanned.charAt(3))) {
						wordLists.get(i).add(scanned);	
					}
				}
			}
			
			catch(FileNotFoundException e) {
				System.out.println(e);	
			}
		}
		
		return wordLists;
	}
	
	//Mögliche Wörter werden nach und nach probiert. Dabei werden erst die beiden senkrechten Wörter benutzt damit dann die patterns der 
	//waagerechten Wörter angepassen werden können. Wenn alle 4 Wörter zu den 4 momentanen Pattern passen, werden diese als moegliche Loesung
	//in die ArrayList mit Loesungen gespeichert.
	
	private void findWords() {		
		ArrayList<ArrayList<String>> wordLists = createPossibleWordLists();
		
		String p1 = patterns[0];
		String p2 = patterns[1];
		String p3 = patterns[2];
		String p4 = patterns[3];

		String cTemp=p3;
		String dTemp=p4;
		
		int counter = 0;
		
		for(String a : wordLists.get(0)) {
			if (p1.charAt(1)==a.charAt(1)) {cTemp=cTemp.substring(0,1)+"."+cTemp.substring(2,4); }
			
			else if (p1.charAt(1)=='.') {cTemp=cTemp.substring(0,1)+a.charAt(1)+cTemp.substring(2,4);}
			
			else {cTemp=cTemp.substring(0,1)+p1.charAt(1)+cTemp.substring(2,4);}
			
			if (p1.charAt(2)=='.') {dTemp=dTemp.substring(0,1)+a.charAt(2)+dTemp.substring(2,4);}
			
			else if (p1.charAt(2)==a.charAt(2)) {dTemp=dTemp.substring(0,1)+"."+dTemp.substring(2,4);}
			
			else {dTemp=dTemp.substring(0,1)+p1.charAt(2)+dTemp.substring(2,4);}
			
			String cTemp2=cTemp;
			String dTemp2=dTemp;
			
			for(String b : wordLists.get(1)) {
				if (p2.charAt(1)=='.') {cTemp2=cTemp2.substring(0,2)+b.charAt(1)+cTemp2.substring(3,4); }
				
				else if (p2.charAt(1)==b.charAt(1)) {cTemp2=cTemp2.substring(0,2)+"."+cTemp2.substring(3,4);}
				
				else {cTemp2=cTemp2.substring(0,2)+p2.charAt(1)+cTemp2.substring(3,4);}

				if (p2.charAt(2)=='.') {dTemp2=dTemp2.substring(0,2)+b.charAt(2)+dTemp2.substring(3,4);}
				
				else if (p2.charAt(2)==b.charAt(2)) {dTemp2=dTemp2.substring(0,2)+"."+dTemp2.substring(3,4);}
				
				else {dTemp2=dTemp2.substring(0,2)+p2.charAt(2)+dTemp2.substring(3,4);}
				
				Pattern patternC = Pattern.compile(cTemp2);
				
				for(String c : wordLists.get(2)) {	
					Matcher matcherC = patternC.matcher(c);
					
					if(matcherC.find()) {												
						Pattern patternD = Pattern.compile(dTemp2);
									
						for(String d : wordLists.get(3)) {			
							Matcher matcherD = patternD.matcher(d);
									
							if(matcherD.find()) {		
								String[] result = {a,b,c,d};
								
								possibleCombinations.add(result);
								
								counter++;
								
								if(counter==limit) {	
									return;							
								}
							}					
						}																		
					}																						
				}	
				
				cTemp2=cTemp;
				dTemp2=dTemp;					
			}	
			
			cTemp=p3;
			dTemp=p4;		
		}					
	}	
}