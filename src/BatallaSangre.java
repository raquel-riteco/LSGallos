import java.util.ArrayList;

/**
 *      Esta es una subclase de la clase batalla, utilizada para definir el tipo de batalla a ejecutar.
 * */

public class BatallaSangre extends Batalla{


    public BatallaSangre (ArrayList<Rapero> raperos, int posMiRapero){
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
    public double puntuacionSimulaciones(float R) {
        return (Math.PI * Math.pow(R, 2))/4;
    }

    /**
     *      Este método calcula la puntuación en función del cálculo de rimas ejecutado por la función calcularRimes().
     *      Esto se consigue a partir de una fórmula matemática única para este tipo de batalla.
     *
     *      @return (float) puntuación.
     * */

    @Override
    public double calculoPuntuacion (){
        Integer[] R = calcularRimes();
        return ((Math.PI * Math.pow(R[0], 2))/4) + ((Math.PI * Math.pow(R[1], 2))/4);
    }
}

