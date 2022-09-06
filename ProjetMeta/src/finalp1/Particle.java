package finalp1;

public class Particle {
   public String position;
   public int velocity;
   public long pbest;
   public long pbestFitness;
   public long gbest;
   public float w,c1,c2;
   public int fitness;
   public String prev;
   public int iterationFound=0;
    public Particle(String position, int velocity, int pbest, int gbest, float w, float c1, float c2){
        this.position = position;
        this.velocity = velocity;
        this.pbest = pbest;
        this.gbest = gbest;
        this.w = w;
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public String toString() {
        return "pos "+this.position + " pbest " + this.pbest+" fitness "+this.fitness+" pbestFitness "+this.pbestFitness+" gbest "+this.gbest;
    }

}
