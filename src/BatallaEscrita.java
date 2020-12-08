import java.util.ArrayList;

public class BatallaEscrita extends Batalla{

    private int maximSegons;

    public BatallaEscrita (ArrayList<Rapero> raperos, int posMiRapero){
        super(raperos, posMiRapero);
    }

    public double calculoPuntuacion (double R){
        return (1 + R);
    }
}


