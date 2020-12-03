import java.util.ArrayList;
import java.util.Date;

public class Competicio {

    private String nom;
    private Date dataInici;
    private Date dataFinal;
    private int numFases;
    private int numParticipants;
    private ArrayList<Fase> fases;
    private ArrayList<String> llistaPaisos;
    private ArrayList<Rapero> raperos;


    public Competicio(){
        fases = new ArrayList<>();
        llistaPaisos = new ArrayList<>();
        raperos = new ArrayList<>();
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

    public Fase getFase (int num) {
        return fases.get(num);
    }

    public ArrayList<String> getLlistaPaisos() {
        return llistaPaisos;
    }

    public ArrayList<Rapero> getRaperos() {
        return raperos;
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

    public void setFases(double pressupost, String pais) {
        fases.add(new Fase(pressupost, pais));
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public void setTemas (Tema tema, Fase fase){
        //Hi ha dues batalles per fase
        fase.setTemasBatalla(tema, fase.getBatalla(0));
        fase.setTemasBatalla(tema, fase.getBatalla(1));
    }

    public void setLlistaPaisos(String pais) {
        llistaPaisos.add(pais);
    }

    public void setRaperos(Rapero rapero) {
        raperos.add(rapero);
    }

    public String fasesToString () {
        String toString = "";
        for (Fase o : fases){
            toString = toString.concat(o.toString());
            toString = toString.concat("\n");
        }
        return toString;
    }

    public String raperosToString () {
        String toString = "";
        for (Rapero o : raperos){
            toString = toString.concat(o.toString());
            toString = toString.concat("\n");
        }
        return toString;
    }

    @Override
    public String toString() {
        return "Competicio{" +
                "\nnom='" + nom + '\'' +
                "\ndataInici=" + dataInici +
                "\ndataFinal=" + dataFinal +
                "\nnumFases=" + numFases +
                "\nnumParticipants=" + numParticipants +
                "\nfases=" + fasesToString() +
                "\nraperos=" + raperosToString() +
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
