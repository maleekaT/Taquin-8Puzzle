package finalp1;

public class Verification {


    static String init,goal;

    // Fonction pour verifier si les états sont corrects
    public static int verification(String etat) 
    {
    	 int len = etat.length();
    	 if (len != 9) {
             return -1;
    	 } 	 

    	 for (int i = 0; i < len; i++) {
 			if (!(etat.matches("[0-8]+"))) {
 		            return -2;   		            
 		     }}    	 
    	 for (int i = 0; i < len; i++) {
    	 for (int j = 0; j < len; j++) {
    		 if(etat.charAt(i)==etat.charAt(j) && i != j) {
    	    	 return -3;
    	    	 }
    		 }} 
    	     	    	 return 1;
     }
    
  
    
    public static void main(String args[]) {
    	
    }
}
