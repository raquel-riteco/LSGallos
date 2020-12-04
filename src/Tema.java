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

    public void setEstrofas(Estrofa estrofa) {
        estrofas.add(estrofa);
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
