import java.util.ArrayList;

public class Tema {

    private String nomTema;
    private ArrayList<Estrofa> estrofas;

    /* CONSTRUCTORES */

    public Tema(String nomTema){
        estrofas = new ArrayList<>();
        this.nomTema = nomTema;
    }

    /* GETTERS */

    public String getNomTema() {
        return nomTema;
    }


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

    public Estrofa getEstrofa (Long nivel){
        return estrofas.get(Integer.parseInt(Long.toString(nivel - 1)));
    }

    /* SETTERS */
    public void setEstrofas(Estrofa estrofa) {
        estrofa.setPuntuacion();
        estrofas.add(estrofa);
    }

    /*To STRINGS */
    @Override
    public String toString() {
        return "Tema{" +
                "\nnomTema='" + nomTema + '\'' +
                "\nestrofas=" + estrofas.toString() +
                '}';
    }

    /* ESTROFA */

    public static class Estrofa {
        private int nivel;
        private ArrayList<String> barras;
        private ArrayList<Integer> puntuacion;

        /* CONSTRUCTORES */

        public Estrofa(int nivel) {
           this.nivel = nivel;
           barras = new ArrayList<>();
        }

        /* SETTERS */

        public void setBarra(String barra) {
            barras.add(barra);
        }

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

        }

        public static void extractRimes(ArrayList<Integer> sumaNumRimes, ArrayList<String> ultimasLetras) {
            for (int i = 0; i < ultimasLetras.size(); i++) {
                int numRimas = 0;
                String actual = ultimasLetras.get(i);
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


        /* GETTERS */

        public ArrayList<String> getBarras() {
            return barras;
        }

        public ArrayList<Integer> getPuntuacion() {
            return puntuacion;
        }

        /* TO STRINGS */

        @Override
        public String toString() {
            return "Estrofa{" +
                    "nivel=" + nivel +
                    ", barras=" + barrasToString() +
                    '}';
        }

        public String barrasToString (){
            String barrasToString = "";
            for (String s : barras) {
                barrasToString = barrasToString.concat(s);
            }
            return barrasToString;
        }
    }
}
