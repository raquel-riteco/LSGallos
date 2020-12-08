import java.util.ArrayList;

public class BatallaSangre extends Batalla{

    private String productorBase;

    public BatallaSangre (ArrayList<Rapero> raperos, int posMiRapero){
        super(raperos, posMiRapero);
    }

    public double calculoPuntuacion (double R){
        return (Math.PI * Math.pow(R, 2))/4;
    }
}

