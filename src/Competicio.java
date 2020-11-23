import java.util.ArrayList;
import java.util.Date;

public class Competicio {

    private String name;
    private Date startDate;
    private Date endDate;
    private int numFases;
    private ArrayList<Fase> phases;


    public String getNom() {
        return name;
    }

    public void setNom(String nom) {
        this.name = nom;
    }

    public void setDataInici(Date dataInici) {
        this.startDate = dataInici;
    }

    public void setDataFinal(Date dataFinal) {
        this.endDate = dataFinal;
    }

    public void setNumFases(int numFases) {
        this.numFases = numFases;
    }

    public void setFases(float pressupost, String pais) {
        Fase fase = new Fase(pressupost, pais);
        phases.add(fase);
    }

    public void setTemas (String nomTema, int nivel, String barra, Fase fase){
        //Hi ha dues batalles per fase
        fase.setTemasBatalla(nomTema, nivel, barra, fase.getBatalla(0));
        fase.setTemasBatalla(nomTema, nivel, barra, fase.getBatalla(1));
    }





    /*

    private SortedSet<String> ranking;

    public Rapero registrar(Rapero rapero){

        //Funcio

    }

    public Rapero login(Rapero usuari){

        //Funcio

    }

    public void actualitzarPuntuacio(SortedSet<String> ranking){

        //Funcio

    }

     */

}
