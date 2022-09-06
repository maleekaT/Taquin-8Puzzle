package finalp1;

public class Population {


    chromosome[] individuals;
    int totalFitness;
   
    
    // Create a population
    public Population(int populationSize, boolean initialise) {
    	System.out.println("ei ei " +GA.etat_initial);
        individuals = new chromosome[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < Indvsize(); i++) {
                chromosome newIndividual = new chromosome();
                newIndividual.creerChromosome();
                while(!checkViable(GA.etat_initial,newIndividual)) {
                    newIndividual.creerChromosome();
                    checkViable(GA.etat_initial,newIndividual);
                }
                saveIndividual(i, newIndividual);
            }
        }
    }
    
    public void calculetf() {   
    	int tf=0;
        for (int i = 0; i < Indvsize(); i++) {
        	tf = tf +(9-getIndividual(i).getFitness());
            }  
        this.totalFitness=tf;
    }
    
    public int getTF() {  
        return this.totalFitness;
    }

    /* Getters */
    public chromosome getIndividual(int index) {
        return individuals[index];
    }

    public chromosome getFittest() {
        chromosome fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < Indvsize(); i++) {
            if (fittest.getFitness() >= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int Indvsize() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, chromosome indiv) {
        individuals[index] = indiv;    
    }
    
    public static boolean checkViable(String cs, chromosome newIndividual){
    	
    	boolean correct = true;
    	
    	if (newIndividual == null) {
    		correct = false;
    		return correct;
    	}    	
    	else {
    	String gene = newIndividual.toString();

    	for (int i=0; i<gene.length(); i=i+2) {
    		String moveChars = Integer.toString(Integer.parseInt(gene.charAt(i) + "" + gene.charAt(i+1)));
    		int position0 = cs.indexOf('0');
    		switch(moveChars) {
    		case "0":
    			//pour verifier qu'on peut faire un deplacement vers le haut
    			if (position0!=0 && position0!=1 && position0!=2){
    				cs= cs.substring(0,position0-3)+"0"+cs.substring(position0-2,position0)+cs.charAt(position0-3)+cs.substring(position0+1);
                	
                } else {
                	correct = false;
                }
                break;
            case "1":
                //pour verifier qu'on peut faire un deplacement vers le bas
            	if (position0 != 6 && position0 != 7 && position0 != 8) {
            		cs = cs.substring(0,position0)+cs.substring(position0+3,position0+4)+cs.substring(position0+1,position0+3)+"0"+cs.substring(position0+4);
                         	
                } else {
                	correct = false;
                }
                break;
    		case "10":
    			//pour verifier au'on peut faire un deplacement vers la droite
                if(position0 != 2 && position0 != 5 && position0 != 8)
                {
                	cs = cs.substring(0,position0)+cs.charAt(position0+1)+"0"+cs.substring(position0+2);
                } else {
                	correct = false;
                }
                break;
    		case "11":
    			//pour verifier au'on peut faire un deplacement vers la gauche
                if(position0 != 0 && position0 != 3 && position0 != 6)
                {
                	cs = cs.substring(0,position0-1)+"0"+cs.charAt(position0-1)+cs.substring(position0+1);
                } else {
                	correct = false;
                } 
                break;
    		}           
    	}
    	}
		return correct;}
}