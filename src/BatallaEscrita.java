import java.util.ArrayList;

/**
 *      Esta es una subclase de la clase batalla, utilizada para definir el tipo de batalla a ejecutar.
 * */

public class BatallaEscrita extends Batalla{

    public BatallaEscrita (ArrayList<Rapero> raperos, int posMiRapero){
        super(raperos, posMiRapero);
    }

    /**
     *      Este método calcula la puntuación en función de el número de rimas introducido por parámetro. Esto se
     *      consigue a partir de una fórmula matemática única para este tipo de batalla.
     *
     *      @param R (float) número de rimas.
     *
     *      @return (float) puntuación.
     * */

    @Override
    public float puntuacionSimulaciones(float R) {
        return (1 + R);
    }

    /**
     *      Este método calcula la puntuación en función del cálculo de rimas ejecutado por la función calcularRimes().
     *      Esto se consigue a partir de una fórmula matemática única para este tipo de batalla.
     *
     *      @return (float) puntuación.
     * */

    @Override
    public double calculoPuntuacion (){
        double R = calcularRimes();
        return (1 + R);
    }
}


