import java.util.ArrayList;

public class BatallaAcapella extends Batalla {

    public BatallaAcapella (ArrayList<Rapero> raperos, int posMiRapero){
        super(raperos, posMiRapero);
    }

    public double calculoPuntuacion (double R){
        return (Math.sqrt(R) + 5)/3;
    }
}


