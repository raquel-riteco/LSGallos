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
                if (a.getBarras().size() == 1){
                    return "Oh no, te has quedado en blanco!";
                }else{
                    return a.getBarras().get(numBarra);
                }
            }
        }
        return "Oh no, te has quedado en blanco!";
    }

    /* SETTERS */
    public void setEstrofas(Estrofa estrofa) {
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

        /* CONSTRUCTORES */

        public Estrofa(int nivel) {
           this.nivel = nivel;
           barras = new ArrayList<>();
        }

        /* SETTERS */

        public void setBarra(String barra) {
            barras.add(barra);
        }

        /* GETTERS */

        public ArrayList<String> getBarras() {
            return barras;
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
