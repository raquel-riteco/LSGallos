import java.util.ArrayList;

public class Tema {

    private String nomTema;
    private ArrayList<Estrofa> estrofas;

    public Tema(String nomTema){
        estrofas = new ArrayList<>();
        this.nomTema = nomTema;
    }

    public void setNomTema(String nomTema) {
        this.nomTema = nomTema;
    }

    public String getNomTema() {
        return nomTema;
    }

    public void setEstrofas(Estrofa estrofa) {
        estrofas.add(estrofa);
    }

    public String getEstrofaPerNivell (Long nivel, int numBarra){
        for (Estrofa a : estrofas) {
            if (Integer.parseInt(String.valueOf(nivel)) == a.nivel){
                return a.getBarras().get(numBarra);
            }
        }
        return "Oh no, te has quedado en blanco!";
    }

    public static class Estrofa {
        private int nivel;
        private ArrayList<String> barras;

       public Estrofa(int nivel) {
           this.nivel = nivel;
           barras = new ArrayList<>();
       }

        public void setBarra(String barra) {
            barras.add(barra);
        }

        public ArrayList<String> getBarras() {
            return barras;
        }

        @Override
        public String toString() {
            return "Estrofa{" +
                    "nivel=" + nivel +
                    ", barras=" + barras +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Tema{" +
                "\nnomTema='" + nomTema + '\'' +
                "\nestrofas=" + estrofas.toString() +
                '}';
    }
}
