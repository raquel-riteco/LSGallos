import java.util.ArrayList;
import java.util.Date;

public class Competicio {

    private String nom;
    private Date dataInici;
    private Date dataFinal;
    private int numFases;
    private int numParticipants;
    private ArrayList<Fase> fases;

    public Competicio(){
        this.fases = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public Date getDataInici() {
        return dataInici;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public int getNumFases() {
        return numFases;
    }

    public int getNumParticipants(){
        return numParticipants;
    }

    public ArrayList<Fase> getFases() {
        return fases;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setNumFases(int numFases) {
        this.numFases = numFases;
    }

    public void setFases(ArrayList<Fase> fases) {
        this.fases = fases;
    }

    public void setTemas (String nomTema, int nivel, String barra, Fase fase){
        //Hi ha dues batalles per fase
        fase.setTemasBatalla(nomTema, nivel, barra, fase.getBatalla(0));
        fase.setTemasBatalla(nomTema, nivel, barra, fase.getBatalla(1));
    }

    @Override
    public String toString() {
        return "Competicio{" +
                "nom='" + nom + '\'' +
                ", dataInici=" + dataInici +
                ", dataFinal=" + dataFinal +
                ", numFases=" + numFases +
                ", numParticipants=" + numParticipants +
                ", fases=" + fases +
                '}';
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
