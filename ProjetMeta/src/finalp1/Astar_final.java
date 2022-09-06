package finalp1;

import java.util.*;




public class Astar_final {
    static HashMap<String, Etat> open = new HashMap<>();
    static HashMap<String, Etat> temp = new HashMap<>();
    static HashMap<String, Etat> close = new HashMap<>();
    static HashMap<String, Integer> profondeur = new HashMap<>();
    
    static String initial;      //Variables a,b and c are meant to store the  
    static String but;
    

    public static ArrayList<String> astar(int choix){
    	initial =GUI.EI;
    	but =GUI.EB;
        String currentState=initial;
        Etat etat = new Etat(0,"", "");
        profondeur.put(initial, 0);
        open.put(currentState,new Etat(0,"", ""));
        while(!currentState.equals(but)){
            temp = selectedState();

            for(String state: temp.keySet()){
                currentState = state;
                etat = temp.get(state);
            }
            close.put(currentState, etat);
            move(currentState, choix);
        }
        close.put(currentState, etat);
        ArrayList<String> actions = findActions(currentState);

        return actions;
    }

    //une fonction qui nous permet de trouver les actions qu'on a fait
    public static ArrayList<String> findActions(String currentState){
        ArrayList<String> actions = new ArrayList<>();
        Etat etat;
        while(!currentState.equals(initial)){
            etat = close.get(currentState);
            actions.add(etat.move);
            currentState = etat.prec;
        }
        Collections.reverse(actions);
        return actions;
    }

    static void doClear() {
    	open.clear();
    	temp.clear();
    	close.clear();
    	profondeur.clear();
    }

    //une fonct qui nous permet d'ajouter des elements à open
    public static String  addStateToOpen(String currentState, String newState, String move, int choix){
        int g = profondeur.get(currentState);
        g++;
        int h=0;
        if(choix==1) {
            h = hMalPlacee(newState);
        }else {
            h = hManhattanDistance(newState);
        }
        int fNew = calculerF(h,g);

        if(close.containsKey(newState)){
            return "";
        }else if(open.containsKey(newState)){
            if (open.get(newState).value <= fNew){
                return "";
            }else{
                open.remove(newState);
                open.put(newState, new Etat(fNew, currentState, move));
                //profondeur.remove(newState);
                profondeur.put(newState, g);
                return newState;
            }

        }else{
            open.put(newState, new Etat(fNew, currentState, move));
            profondeur.put(newState, g);
            return newState;
        }
    }


    //une fonction qui nous permet de faire un deplacement
    public static void move(String currentState, int choix){
        int position0 = currentState.indexOf('0');

        //pour verifier qu'on peut faire un deplacement vers le haut
        if(position0 > 2){
            String newStateUp = currentState.substring(0, position0-3) + '0' + currentState.substring(position0-2, position0)
                    +currentState.charAt(position0-3) + currentState.substring(position0+1);

            addStateToOpen(currentState, newStateUp, "up",choix);

        }
        //pour verifier qu'on peut faire un deplacement vers le bas
        if (position0 < 6){
            String newStateDown = currentState.substring(0, position0) + currentState.charAt(position0+3)
                    + currentState.substring(position0+1, position0+3) + '0' +currentState.substring(position0+4);

            addStateToOpen(currentState, newStateDown, "down", choix);

        }
        //pour verifier au'on peut faire un deplacement vers la droite
        if(position0 != 2 && position0 != 5 && position0 != 8)
        {
            String newStateRight = currentState.substring(0,position0)+currentState.charAt(position0+1)+"0"+currentState.substring(position0+2);
            addStateToOpen(currentState, newStateRight, "right", choix);

        }
        //pour verifier au'on peut faire un deplacement vers la gauche
        if(position0 != 0 && position0 != 3 && position0 != 6)
        {
            String newStateLeft = currentState.substring(0,position0-1)+"0"+currentState.charAt(position0-1)+currentState.substring(position0+1);
            addStateToOpen(currentState, newStateLeft, "left", choix);
        }
    }


    //une fonction qui nous permet de choisir un état parmit les etats qui sont dans la liste ouverte
    public static HashMap<String, Etat> selectedState(){
        int min = Integer.MAX_VALUE;
        HashMap<String, Etat> selected = new HashMap<>();
        Etat chosenState = null;
        String key= "";
        for(String state: open.keySet()){

            if(open.get(state).value <= min){
                chosenState = open.get(state);
                min = open.get(state).value;
                key = state;
            }
        }
        if(chosenState != null) {
            open.remove(key);
            selected.put(key, chosenState);
            //close.put(chosenState, new Etat())
        }

        return selected;
    }

    //une fonction calcule h (les cases mal placees)
    public static int hMalPlacee(String currentState){
        int h = 0;
        for(int i=0 ; i<currentState.length(); i++){
            if(currentState.charAt(i) != but.charAt(i)){
                h++;
            }
        }
        return h;
    }


    public static int hManhattanDistance(String currentState){
        int manhattan = 0;
        int posBut;
        for (int i=0; i<currentState.length(); i++){
            posBut = but.indexOf(currentState.charAt(i));
            manhattan += (Math.abs(posBut-i));
        }
        return manhattan;
    }


    public static int calculerF(int h, int g){
        return h + g;
    }

}
