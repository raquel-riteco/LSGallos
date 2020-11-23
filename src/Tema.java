import java.util.ArrayList;

public class Tema {

    private String nomTema;
    private ArrayList<Estrofa> estrofas;

    public Tema(String nomTema, int nivel, String barra) {
        this.nomTema = nomTema;
        setEstrofas(nivel, barra);
    }

    public void setEstrofas(int nivel, String barra) {
        Estrofa estrofa = new Estrofa(nivel, barra);
    }

    public class Estrofa {
        private int nivel;
        private String barra;

       public Estrofa(int nivel, String barra) {
           this.nivel = nivel;
           this.barra = barra;
       }
    }


}
