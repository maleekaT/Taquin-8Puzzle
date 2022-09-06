package finalp1;

public class FitnessCalc {
	static byte[] solution = new byte[64];
    private static String currentState;


    /* Public methods */
    // Set a candidate solution as a byte array
    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    // To make it easier we can use this method to set our candidate solution
    // with string of 0s and 1s
    static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];
        // Loop through each character of our string and save it in our byte
        // array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    static int getFitness(chromosome individual) {    	
        int fitness = 0;
        // Loop through our individuals genes and compare them to our cadidates
        for (int i = 0; i < individual.ChromosomeSize(); i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    } 
    
    public static int hManhattanDistance(chromosome individual){
        int h = 0;
        currentState = DoMoves(individual.toString());
        String posBut =  GA.etat_but;
        for (int i=0; i<currentState.length(); i++){
        	if(currentState.charAt(i) != posBut.charAt(i)){
                h++;
            }
        }
    return h;
}

   
    public static String DoMoves(String string) {
		// TODO Auto-generated method stub
    	String currentState = GA.etat_initial;
    	for (int i=0; i<string.length(); i=i+2) {
    		String moveChars = Integer.toString(Integer.parseInt(string.charAt(i) + "" + string.charAt(i+1)));
    		int position0 = currentState.indexOf('0');
    		switch(moveChars) {
    		case "0":
    			//pour verifier qu'on peut faire un deplacement vers le haut
                if(position0 > 2){
                	currentState = currentState.substring(0, position0-3) + '0' + currentState.substring(position0-2, position0)
                            +currentState.charAt(position0-3) + currentState.substring(position0+1);
                }
                break;
            case "1":
                //pour verifier qu'on peut faire un deplacement vers le bas
                if (position0 < 6){
                	currentState = currentState.substring(0, position0) + currentState.charAt(position0+3)
                            + currentState.substring(position0+1, position0+3) + '0' +currentState.substring(position0+4);
                } 
                break;
    		case "10":
    			//pour verifier au'on peut faire un deplacement vers la droite
                if(position0 != 2 && position0 != 5 && position0 != 8)
                {
                	currentState = currentState.substring(0,position0)+currentState.charAt(position0+1)+"0"+currentState.substring(position0+2);
                } 
                break;
    		case "11":
    			//pour verifier au'on peut faire un deplacement vers la gauche
                if(position0 != 0 && position0 != 3 && position0 != 6)
                {
                	currentState = currentState.substring(0,position0-1)+"0"+currentState.charAt(position0-1)+currentState.substring(position0+1);
                } 
                break;
    		}           
    	}
		return currentState;
	} 

	// Get optimum fitness
    static int getMaxFitness() {
        int maxFitness = solution.length;
        return maxFitness;
    }

	
}
