package finalp1;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final boolean elitism = true;

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.Indvsize(), false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.Indvsize(); i++) {
            chromosome indiv1 = RWSelection(pop);
            while(!Population.checkViable(GA.etat_initial, indiv1) ) {
            	indiv1 = RWSelection(pop);
            }
            chromosome indiv2 = RWSelection(pop);
            while(!Population.checkViable(GA.etat_initial, indiv2) ) {
            	indiv2 = RWSelection(pop);
            }
            chromosome newIndiv = crossover(indiv1, indiv2);
            while(!Population.checkViable(GA.etat_initial, newIndiv)) {
            	newIndiv = crossover(indiv1, indiv2);
            }
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.Indvsize(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static chromosome crossover(chromosome indiv1, chromosome indiv2) {
        chromosome newSol = new chromosome();
        // Loop through genes
        for (int i = 0; i < indiv1.ChromosomeSize(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(chromosome indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.ChromosomeSize(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);               
            }
        }
    }

    // Select individuals for crossover
   
    
    private static chromosome RWSelection(Population pop) {
    	pop.calculetf();
    	for (int i=0; i<pop.Indvsize(); i++) {
    		pop.getIndividual(i).setRF((float) (9-pop.getIndividual(i).getFitness())/pop.getTF());
    	}
    	
    	List<Float> probs = new ArrayList<Float>();
    	
    	float s=0;
    	chromosome c = null;
    	for (int i=0; i<pop.Indvsize(); i++) {
    		s =s+pop.getIndividual(i).getRF();
    		probs.add(s);
    	}
        float randomId = (float) Math.random();

        for (int i=0; i<probs.size(); i++) {        
    		if (randomId<= probs.get(i)) {        		
	        	c = pop.getIndividual(i);	
	        	break;
    		}
        }
            
    return c;
    }
}