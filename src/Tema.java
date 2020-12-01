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
    }


}
