package finalp1;



public class Etat {
    int value;
    String prec;
    String move;
    public Etat(int value, String prec, String move){
        this.value = value;
        this.prec = prec;
        this.move = move;
    }


    @Override
    public String toString() {
        return prec+" "+move;
    }
}
