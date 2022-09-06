package finalp1;

public class chromosome {

	/* 
	 *            HNA 1 GENES = 1 CHROMOSOME = 1 INDIVIDUAL
	 * */
    static int longeur_chromosome = 52;
    private byte[] genes = new byte[longeur_chromosome];
    private int fitness = 0;
    private float rf = 0;
    

    public void creerChromosome() {
        for (int i = 0; i < ChromosomeSize(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    } 
   
    public byte getGene(int index) {
        return genes[index];
    } 
    
    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    } 
    
    public int ChromosomeSize() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.hManhattanDistance(this);
        }
        return fitness;
    }
    
    public float getRF() {       
        return this.rf;
    }
    
    public void setRF(float v) {       
         this.rf = v;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < ChromosomeSize(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    } 
}