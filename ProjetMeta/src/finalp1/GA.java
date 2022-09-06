package finalp1;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class GA {
	
	private static List<String> MOVES = new ArrayList<String>(); 

	
	static String etat_initial;       
	static String etat_but;
	private static String currentState;
    static List<Integer> list = new ArrayList<Integer>();
    static List<String> aff = new ArrayList<String>();
    static String s = "";
	static JTextArea textArea = new JTextArea();

	public static <T> List<String> AlgoG() {

		etat_initial =GUI.EI;       
		etat_but =GUI.EB;
		currentState= etat_initial;
        // Set a candidate solution

        // Create an initial population
        Population myPop = new Population(50, true);
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
                while (myPop.getFittest().getFitness() >0 && generationCount<=20000 ) {
			            generationCount++;
			          //  System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
			            myPop = Algorithm.evolvePopulation(myPop);
        }
        
    	
        if (myPop.getFittest().getFitness() == 0) {
            GUI.textAreaMOVES.setText("Solution trouvée ! Generation: " + generationCount);
       
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
        String geneee = myPop.getFittest().toString();
        for(int k = 0; k<myPop.getFittest().ChromosomeSize(); k=k+2) {
        		String moveChars = Integer.toString(Integer.parseInt(geneee.charAt(k) + "" + geneee.charAt(k+1)));
        		int position0 = currentState.indexOf('0');
        		//System.out.println(currentState);

        		switch(moveChars) {
        		case "0":
        			//pour verifier qu'on peut faire un deplacement vers le haut
                    if(position0 > 2){
                    	currentState = currentState.substring(0, position0-3) + '0' + currentState.substring(position0-2, position0)
                                +currentState.charAt(position0-3) + currentState.substring(position0+1);
                    	MOVES.add("up");
                    	}
                    break;
                case "1":
                    //pour verifier qu'on peut faire un deplacement vers le bas
                    if (position0 < 6){
                    	currentState = currentState.substring(0, position0) + currentState.charAt(position0+3)
                                + currentState.substring(position0+1, position0+3) + '0' +currentState.substring(position0+4);
                    	MOVES.add("down");
                    } 
                    break;
        		case "10":
        			//pour verifier au'on peut faire un deplacement vers la droite
                    if(position0 != 2 && position0 != 5 && position0 != 8)
                    {
                    	currentState = currentState.substring(0,position0)+currentState.charAt(position0+1)+"0"+currentState.substring(position0+2);
                    	MOVES.add("right");
                    } 
                    break;
        		case "11":
        			//pour verifier au'on peut faire un deplacement vers la gauche
                    if(position0 != 0 && position0 != 3 && position0 != 6)
                    {
                    	currentState = currentState.substring(0,position0-1)+"0"+currentState.charAt(position0-1)+currentState.substring(position0+1);
                    	MOVES.add("left");
                    } 
                    break;
        		}        
        }
      
        }
        else {
            textArea.append("Solution NOT found! after " + generationCount + " generations");
        }
		return MOVES;

}
	  static void doClear() {
	    	MOVES.clear();
	    	list.clear();
	    	aff.clear();
	    	textArea.setText("");
	    }
}
