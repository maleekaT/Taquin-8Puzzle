package finalp1;

import java.util.Random;

public class Parameters {
    static Random random = new Random();
    public static int NUMBER_ITERATIONS=500;
    public static int PARTICLES_NUMBERS=10;
    public static int INITIAL_VELOCITY= 4;
    public static float INITIAL_W= (float) 1;
    public static float INITIAL_R1= (float)(random.nextInt(99)+1) / 100;
    public static float INITIAL_R2= (float)(random.nextInt(99)+1) / 100;
    public static int INITIAL_C1 = 1;
    public static int INITIAL_C2 = 5;



}
