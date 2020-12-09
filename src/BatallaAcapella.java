import java.util.ArrayList;

public class BatallaAcapella extends Batalla {

    public BatallaAcapella (ArrayList<Rapero> raperos, int posMiRapero){
        super(raperos, posMiRapero);
    }

    @Override
    public float puntuacionSimulaciones(float R) {
        return (Math.sqrt(R) + 5)/3;
    }

    @Override
    public double calculoPuntuacion (){
        double R = calcularRimes();
        return (Math.sqrt(R) + 5)/3;
    }
}


