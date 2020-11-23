import java.util.ArrayList;

public class Fase {

    private float pressupost;
    private String pais;
    private ArrayList<String> llistaPaisos;
    private ArrayList<Batalla> batallas;

    public Fase(float pressupost, String pais){
        this.pressupost = pressupost;
        this.pais = pais;
    }

    public void setTemasBatalla(String nomTema, int nivel, String barra, Batalla batalla){
        batalla.setTemas(nomTema, nivel, barra);
    }

    public void setPressupost(float pressupost) {
        this.pressupost = pressupost;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Batalla getBatalla(int num){
        return batallas.get(num);
    }

    /*
    private Batalla batalles[2];
    private Rapero raperos[];

    public void descartarRaperos(Rapero raperos[1*]){

        //Funcio

    }

    public void executarFase(){

        //Funcio

    }
    */
}
