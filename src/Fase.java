import java.util.ArrayList;

public class Fase {

    private float pressupost;
    private String pais;
    private ArrayList<Batalla> batallas;

    public Fase(float pressupost, String pais){
        this.pressupost = pressupost;
        this.pais = pais;
    }

    public void setTemasBatalla(Tema tema, Batalla batalla){
        batalla.setTemas(tema);
    }

    public void setPressupost(float pressupost) {
        this.pressupost = pressupost;
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
