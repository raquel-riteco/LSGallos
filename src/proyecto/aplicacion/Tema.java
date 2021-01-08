package proyecto.aplicacion;

import java.util.ArrayList;

public class Tema {

    private final String nomTema;
    private final ArrayList<Estrofa> estrofas;

    /* CONSTRUCTORES */

    /**
     *      Creara una objeto Project.AppNegocio.Tema nuevo y le asignara la informacion obtenida por los parametros.
     *
     *      @param nomTema (String) Nombre del Project.AppNegocio.Tema.
     *
     * */

    public Tema(String nomTema){
        estrofas = new ArrayList<>();
        this.nomTema = nomTema;
    }

    /* GETTERS */

    /**
     *      Método para obtener el nombre del Project.AppNegocio.Tema.
     *
     *      @return (String) Nombre del Project.AppNegocio.Tema.
     * */

    public String getNomTema() {
        return nomTema;
    }


    /**
     *      Este método busca en la estrofa el verso correspondiente al nivel del rapero y al número de barra (1 o 2),
     *      referente a la primera o segunda intervención del rapero.
     *
     *      @param numBarra (int) referente a la 1a o 2a intervención.
     *      @param nivel (Long) referente al nivel del rapero.
     *
     *      @return (String) devuelve el verso indicado.
     * */
    public String getEstrofaPerNivell (Long nivel, int numBarra){
        for (Estrofa a : estrofas) {
            if (Integer.parseInt(String.valueOf(nivel)) == a.nivel){
                if (a.getBarras().size() == 1 && numBarra == 1){
                    return "Oh no, te has quedado en blanco!";
                }else{
                    return a.getBarras().get(numBarra);
                }
            }
        }
        return "Oh no, te has quedado en blanco!";
    }

    /**
     *      Este método busca entre las estrofas la que coincida con el nivel pasado por parámetro.
     *
     *      @param nivel (Long) nivel del rapero.
     *
     *      @return (Estrofa) devuelve la estrofa indicada.
     * */

    public Estrofa getEstrofa (Long nivel){
        return estrofas.get(Integer.parseInt(Long.toString(nivel - 1)));
    }

    /* SETTERS */

    /**
     *      Este método añade la estrofa pasada por parámetro al ArrayList estrofas.
     *
     *      @param estrofa (Estrofa) estrofa a añadir.
     * */

    public void setEstrofas(Estrofa estrofa) {
        estrofa.setPuntuacion();
        estrofas.add(estrofa);
    }

    /* ESTROFA */

    public static class Estrofa {
        private final int nivel;
        private final ArrayList<String> barras;
        private final ArrayList<Integer> puntuacion;

        /* CONSTRUCTORES */

        /**
         *      Constructor de la clase statica Estrofa que crea dos ArrayLists y guarda la informacion del nivel de la estrofa.
         *
         *      @param nivel (int) Nivel de la estrofa
         *
         * */

        public Estrofa(int nivel) {
           this.nivel = nivel;
           barras = new ArrayList<>();
           puntuacion = new ArrayList<>();
        }

        /* SETTERS */

        /**
         *      Metodo para guardar la barra en la ArrayList de la estrofa.
         *
         *      @param barra (String) String con una liena de la estrofa
         *
         * */

        public void setBarra(String barra) {
            barras.add(barra);
        }

        /**
         *      Metodo para calcular la puntuacion de la estrofa y guardarla.
         *
         * */

        public void setPuntuacion (){
            for (String barra : barras) {
                ArrayList<Integer> sumaNumRimes = new ArrayList<>();
                ArrayList<String> ultimasLetras = new ArrayList<>();
                String[] separacion = barra.split("\n");
                for (String s : separacion) {
                    ultimasLetras.add(s.substring(s.length() - 2));
                }
                extractRimes(sumaNumRimes, ultimasLetras);
                int R = 0;
                for (Integer i : sumaNumRimes) {
                    R += i;
                }
                puntuacion.add(R);
            }
            if (barras.size() < 2){
                puntuacion.add(0);
            }
        }

        /**
         *      Metodo para extraer las rimas de la estrofa.
         *
         *      @param sumaNumRimes (int) Numero de rimas en la estrofa.
         *      @param ultimasLetras (String) Letras que forman la rima.
         *
         * */


        public static void extractRimes(ArrayList<Integer> sumaNumRimes, ArrayList<String> ultimasLetras) {
            for (int i = 0; i < ultimasLetras.size(); i++) {
                int numRimas = 0;
                String actual = ultimasLetras.get(i);
                if (actual.equals("0")){
                    sumaNumRimes.add(0);
                }else{
                    switch (i){
                        case 0:
                            if (actual.equals(ultimasLetras.get(1)) || actual.equals(ultimasLetras.get(2)) || actual.equals(ultimasLetras.get(3))){
                                numRimas++;
                            }
                            break;
                        case 1:
                            if (actual.equals(ultimasLetras.get(0)) || actual.equals(ultimasLetras.get(2)) || actual.equals(ultimasLetras.get(3))){
                                numRimas++;
                            }
                            break;
                        case 2:
                            if (actual.equals(ultimasLetras.get(0)) || actual.equals(ultimasLetras.get(1)) || actual.equals(ultimasLetras.get(3))){
                                numRimas++;
                            }
                            break;
                        case 3:
                            if (actual.equals(ultimasLetras.get(0)) || actual.equals(ultimasLetras.get(1)) || actual.equals(ultimasLetras.get(2))){
                                numRimas++;
                            }
                            break;
                    }
                    sumaNumRimes.add(numRimas);
                }
            }
        }


        /* GETTERS */

        /**
         *      Metodo para obtener las barras de la estrofa.
         *
         *      @return (String) ArrayList de String con las barras de la estrofa.
         *
         * */

        public ArrayList<String> getBarras() {
            return barras;
        }

        /**
         *      Método para obtener el ArrayList de puntuación formado por Integrers.
         *
         *      @return (ArrayList de Integrers) ArrayList puntuacion.
         *
         * */

        public ArrayList<Integer> getPuntuacion() {
            return puntuacion;
        }

    }
}
