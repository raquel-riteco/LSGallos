import java.util.ArrayList;

public class Tema {

    private String nomTema;
    private ArrayList<Estrofa> estrofas;

    public Tema(){
        estrofas = new ArrayList<>();
    }

    public void setNomTema(String nomTema) {
        this.nomTema = nomTema;
    }

    public void setEstrofas(int nivel, String barra1, String barra2) {
        Estrofa estrofa = new Estrofa(nivel, barra1, barra2);
        estrofas.add(estrofa);
    }

    public class Estrofa {
        private int nivel;
        private String barra1;
        private String barra2;

       public Estrofa(int nivel, String barra1, String barra2) {
           this.nivel = nivel;
           this.barra1 = barra1;
           this.barra2 = barra2;
       }

        @Override
        public String toString() {
            return "Estrofa{" +
                    "\nnivel=" + nivel +
                    "\nbarra1='" + barra1 + '\'' +
                    "\nbarra2='" + barra2 + '\'' +
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
