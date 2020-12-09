import java.util.ArrayList;

public class BatallaEscrita extends Batalla{

    private int maximSegons;

    public BatallaEscrita (ArrayList<Rapero> raperos, int posMiRapero){
        super(raperos, posMiRapero);
    }

    @Override
    public float puntuacionSimulaciones(float R) {
        return (1 + R);
    }

    @Override
    public double calculoPuntuacion (){
        double R = calcularRimes();
        return (1 + R);
    }
}


