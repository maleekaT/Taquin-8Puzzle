package finalp1;

import finalp1.Particle;
import java.util.*;

import static finalp1.Parameters.*;
public class PSO {
	
	static ArrayList<String> MOVES1 = new ArrayList<String>();
	
    public static ArrayList<String> PSO() {
       String puzzleInit = GUI.EI;
       String puzzleFinal = GUI.EB;
       HashMap<Particle, ArrayList<String>> moves = pso(puzzleInit, puzzleFinal);
        if(!moves.isEmpty()){//if there is a sol the key represent our particle and value represent an array of its moves
            for(Particle key: moves.keySet()) {
                MOVES1.addAll(moves.get(key));
                }
            GUI.textAreaMOVES.setText(MOVES1.toString());
        }else{//if there is no sol found
        	GUI.textAreaMOVES.setText("Solution not found");
        }
		return MOVES1;
    }

   
    public static HashMap<Particle, ArrayList<String>> pso(String puzzleS, String puzzleFinalS){
        HashMap<Particle, ArrayList<String>> result = new HashMap<>();
        int[][] puzzle = new int[3][3];
        int[][] puzzleFinal = new int[3][3];
        int k=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                puzzle[i][j]=Integer.parseInt(String.valueOf(puzzleS.charAt(k)));
                puzzleFinal[i][j]=Integer.parseInt(String.valueOf(puzzleFinalS.charAt(k)));
                k++;
            }
        }
        ArrayList<String> alphaMoves=new ArrayList<>();
        Particle particle = psoSolver(puzzle, puzzleFinal);
        if (particle.fitness == 0) {
            //System.out.println("the result is: " + particle.prev + particle.position + " à l'iteration " + particle.iterationFound);
            alphaMoves = bitsToMoves(particle);
            result.put(particle,alphaMoves);
        }

        return result;
    }
    


    public static ArrayList<String> bitsToMoves(Particle particle){
        ArrayList<String> alphabicMoves = new ArrayList<>();
        String moves = particle.prev+particle.position;
        String currentMove="";
        for(int i=0; i<moves.length(); i=i+2){
            currentMove =currentMove + moves.charAt(i);
            currentMove =currentMove + moves.charAt(i+1);
            switch (Integer.parseInt(currentMove)){
                case 00:
                    alphabicMoves.add("up");
                    break;
                case 01:
                    alphabicMoves.add("down");
                    break;
                case 10:
                    alphabicMoves.add("right");
                    break;
                case 11:
                    alphabicMoves.add("left");
                    break;

            }
            currentMove="";
        }
        return alphabicMoves;
    }

    public static Particle psoSolver(int[][] puzzle, int[][] puzzleFinal) {
        Particle particleWinner;
        //Initialize N particle
        ArrayList<Particle> nParticles = generateNparticle(puzzle, PARTICLES_NUMBERS);


        // caluclate fitness
        for (int i = 0; i < nParticles.size(); i++) {
            nParticles.get(i).fitness = calculateFitness(nParticles.get(i), puzzle, puzzleFinal);
        }
        //calculate gbest
        particleWinner = calculateGbest(nParticles);
        int iterations = 0;
        String newPos = "";
        String position = "";
        String movesToGoal = "";
        int index=-1,iterMove;
        while (particleWinner.fitness != 0 && iterations < NUMBER_ITERATIONS) {
            for (int j = 0; j < nParticles.size(); j++) {
                calculeVelocity(nParticles.get(j), INITIAL_R1, INITIAL_R2);
                nParticles.get(j).prev = nParticles.get(j).prev + nParticles.get(j).position;
                position = calculatePosition(nParticles.get(j));
                nParticles.get(j).position = position;

                newPos = verifyPosition(puzzle, nParticles.get(j));

                index=compareCurrentToNew(puzzle, puzzleFinal, newPos, nParticles.get(j).prev);
                if(index != -1){
                    //iterMove = iterations;
                    nParticles.get(j).position = newPos.substring(0,index+2);
                }else {
                    nParticles.get(j).position = newPos;
                }
                nParticles.get(j).fitness = calculateFitness(nParticles.get(j), puzzle, puzzleFinal);
            }
            iterations++;
            particleWinner = calculateGbest(nParticles);
            particleWinner.iterationFound = iterations;
        }
        return particleWinner;
    }





    public static int compareCurrentToNew(int[][] puzzle,int[][] puzzleFinale, String moves, String prev){
        int[][] currentState;
        int indexK=-1;
        for(int k=0;k<moves.length(); k=k+2) {
            currentState = findNewState(moves.substring(0, k+2), prev,puzzle);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (puzzle[i][j] != currentState[i][j]) {
                        indexK = k;
                    }
                }
            }
        }
        return indexK;
    }


    //to find the X case
    public static ArrayList<Integer> findZeroIndex(int[][] puzzle){
        ArrayList<Integer> indexArr = new ArrayList<>();
        for(int i=0; i<3;i++){
            for(int j=0; j<3; j++){
                if(puzzle[i][j]==0){
                    indexArr.add(i);
                    indexArr.add(j);
                    return indexArr;
                }
            }
        }
        return null;
    }

    //to generate N particles
    public static ArrayList<Particle> generateNparticle(int[][] puzzle, int nbrParitcles){
        //00 haut 01 bas 10 droite 11 gauche
        String generatedMove;
        ArrayList<Particle> particles = new ArrayList<>();
        for(int i=0; i<nbrParitcles; i++) {
            generatedMove = generatePracticle(puzzle);
            Particle particle = new Particle(generatedMove,INITIAL_VELOCITY ,Integer.parseInt(generatedMove,2),
                    Integer.parseInt(generatedMove,2),INITIAL_W,INITIAL_C1,INITIAL_C2);
            particle.prev="";
            particle.pbestFitness = Integer.MAX_VALUE;
            particles.add(particle);
        }
        //particles.get(3).prev="";

        return particles;
        }

    //to generate the particles moves
    public static String generatePracticle(int[][] puzzle){
        //0 haut 1 bas 2 droite 3 gauche
        ArrayList<Integer> zeroIndex = findZeroIndex(puzzle);
        String moves="";
        Random rand = new Random();
        int generatedMove;
        for(int i=0; i<4; i++) {
            generatedMove = rand.nextInt(4);
            switch (generatedMove) {
                //Up
                case 0:
                    if (zeroIndex.get(0) == 0) {
                        moves = moves+"01";
                        //change the row to the bottom
                        zeroIndex.set(0, 1);
                    } else {
                        moves = moves+"00";
                        //change the row Up
                        zeroIndex.set(0, zeroIndex.get(0)-1);
                    }
                    break;
                //Bottom
                case 1:
                    if (zeroIndex.get(0) == 2) {
                        moves = moves+"00" ;
                        zeroIndex.set(0, 1);
                    } else {
                        moves = moves+"01";
                        zeroIndex.set(0, zeroIndex.get(0)+1);
                    }
                    break;
                //Right
                case 2:
                    if (zeroIndex.get(1) == 2) {
                        moves = moves+"11";
                        zeroIndex.set(1, 1);
                    } else {
                        moves =  moves+"10";
                        zeroIndex.set(1, zeroIndex.get(1)+1);
                    }
                    break;
                //Left
                case 3:
                    if (zeroIndex.get(1) == 0) {
                        moves =  moves+"10";
                        zeroIndex.set(1, 1);
                    } else {
                        moves = moves+ "11";
                        zeroIndex.set(1, zeroIndex.get(1)-1);
                    }
                    break;
            }
        }
        return moves;
    }


    //calculate gbest
    public static Particle calculateGbest(ArrayList<Particle> particles){
        int min = Integer.MAX_VALUE;
        int minIndex=0;
        Particle particle=null;
        for(int i=0; i<particles.size();i++){
            if(particles.get(i).pbestFitness < min){
                particles.get(i).gbest = particles.get(minIndex).pbest;
                min = particles.get(i).fitness;
                minIndex = i;
                particle = particles.get(i);
            }
        }

        for(int i=0; i<particles.size();i++){
            particles.get(i).gbest = particle.gbest;
        }

        return particle;
    }

    //calculate fitness function
    public static int calculateFitness(Particle particle, int[][] puzzle,int[][] puzzleFinal){
        int[][] currentState ;
        int fitness;

            currentState = findNewState(particle.position,particle.prev, puzzle);
            fitness = 0;
            for(int i=0; i<3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentState[i][j] != puzzleFinal[i][j] && currentState[i][j] != 0) {
                        fitness++;
                    }
                }
            }
            if(fitness < particle.pbestFitness){
                particle.pbest = Long.parseLong(particle.position,2);
                particle.pbestFitness = fitness;
            }
            return fitness;
        }


    //calculate find new state
    public static int[][] findNewState(String position,String prev, int[][] puzzle){
        String moves = prev + position;
        int[][] currentPuzzle = new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0;j<3;j++){
                currentPuzzle[i][j] = puzzle[i][j];
            }
        }
        ArrayList<Integer> zeroIndex = findZeroIndex(currentPuzzle);
        assert zeroIndex != null: "the zeroIndex is null";
        int temp, temp1;
        String nextMove = "";
        for(int i=0; i<moves.length(); i+=2){
            nextMove =nextMove + moves.charAt(i);
            nextMove =nextMove + moves.charAt(i+1);
            switch(Integer.parseInt(nextMove)){
                case 0:
                    temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                    temp1 = currentPuzzle[zeroIndex.get(0)-1][zeroIndex.get(1)];
                    currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = temp1;
                    currentPuzzle[zeroIndex.get(0)-1][zeroIndex.get(1)] = temp;

                    zeroIndex = findZeroIndex(currentPuzzle);
                    break;
                case 1:
                    temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                    temp1 = currentPuzzle[zeroIndex.get(0) + 1][zeroIndex.get(1)];
                    currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = temp1;
                    currentPuzzle[zeroIndex.get(0) + 1][zeroIndex.get(1)] = temp;
                    //temp2 = zeroIndex.get(0)+1;
                    //zeroIndex.set(0, temp2);
                    zeroIndex = findZeroIndex(currentPuzzle);
                    break;
                case 10:
                    temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                    temp1 = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) + 1];
                    currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = temp1;
                    currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) + 1] = temp;
                    zeroIndex = findZeroIndex(currentPuzzle);
                    break;
                case 11:
                    temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                    temp1 = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) - 1];
                    currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = temp1;
                    currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) - 1] = temp;
                    zeroIndex = findZeroIndex(currentPuzzle);
                    break;
            }
            nextMove = "";
        }
    return currentPuzzle;
    }

    //calculate velocity
    public static void calculeVelocity(Particle particle, float r1, float r2){
        float result=0;

         result += particle.w * particle.velocity;
         result+= particle.c1*r1*(particle.pbest - Long.parseLong(particle.position,2));
         result+= particle.c2*r2*(particle.gbest - Long.parseLong(particle.position,2));

        if(Math.round(result)>4 || Math.round(result) < -4 ){
            particle.velocity = Math.round(result) %4+1;
        }else{
            particle.velocity = Math.round(result);
        }
            }

    //calcule position
    public static String calculatePosition(Particle particle){
        String position = "";
        long res = Long.parseLong(particle.position,2) + particle.velocity;
        position = Long.toBinaryString(res);
        if(res < 0){
            position = Long.toBinaryString(res);
            if(position.charAt(0)=='0'){
                position = "1"+position.substring(1);
            }
        }
        if(position.length() < 9){
            int diff = 8-position.length();
            for (int i=0; i<diff; i++){
                position="0"+position;
            }
        }else if(position.length() % 2 != 0){
            position = "0" + position;
        }
        return position;
    }

   

    public static String verifyPosition(int[][] puzzle, Particle particle){
        //int[][] currentPuzzle = new int[3][3];
        int[][] currentPuzzle = findNewState(particle.prev,"",puzzle);
        ArrayList<Integer> zeroIndex = findZeroIndex(currentPuzzle);
        assert zeroIndex != null: "the zeroIndex is null";
        int temp;
        String nextMove="",newMoves="",moves = particle.position;

        for(int i=0;i<particle.position.length(); i+=2){
             moves = particle.position;
            nextMove =nextMove + moves.charAt(i);
            nextMove =nextMove + moves.charAt(i+1);
            switch(Integer.parseInt(nextMove)) {
                case 0:
                    if(zeroIndex.get(0)==0){
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0) + 1][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0) + 1][zeroIndex.get(1)] = temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves = newMoves+ "01";
                    }else{
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0) - 1][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0) - 1][zeroIndex.get(1)] =  temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves =newMoves+ "00";
                    }

                    break;
                case 1:
                    if(zeroIndex.get(0)==2) {
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0) - 1][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0) - 1][zeroIndex.get(1)] = temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves = newMoves+"00";
                    }else{
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0) + 1][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0) + 1][zeroIndex.get(1)] = temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves =newMoves+ "01";
                    }
                    break;
                case 10:
                    if(zeroIndex.get(1)==2){
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)-1];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) - 1] = temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves=newMoves+"11";
                    }else {
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) + 1];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) + 1] = temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves=newMoves+"10";
                    }
                    break;
                case 11:
                    if(zeroIndex.get(1)==0){
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) + 1];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) + 1] = temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves=newMoves+"10";
                    }else {
                        temp = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)] = currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1)-1];
                        currentPuzzle[zeroIndex.get(0)][zeroIndex.get(1) - 1] = temp;
                        zeroIndex = findZeroIndex(currentPuzzle);
                        newMoves=newMoves+"11";
                    }
                    break;
            }
            nextMove = "";
        }

        return newMoves;
    }

}
