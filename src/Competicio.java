import javafx.collections.transformation.SortedList;

import java.util.*;

public class Competicio {

    private String nom;
    private Date dataInici;
    private Date dataFinal;
    private int numFases;
    private int numParticipants;
    private ArrayList<Fase> fases;
    private ArrayList<String> llistaPaisos;
    private ArrayList<Rapero> raperos;
    private ArrayList<Rapero> ranking;

    public Competicio(){
        fases = new ArrayList<>();
        llistaPaisos = new ArrayList<>();
        raperos = new ArrayList<>();
        ranking = new ArrayList<>();
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

    public ArrayList<Rapero> getRanking() {
        setRanking();
        return ranking;
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

    public void setLlistaPaisos(String pais) {
        llistaPaisos.add(pais);
    }

    public void setRaperos(Rapero rapero) {
        raperos.add(rapero);
        numParticipants = raperos.size();
    }

    public void setRanking() {
        ranking = raperos;
        ranking.sort((o1, o2) -> Float.compare(o2.getPuntuacio(), o1.getPuntuacio()));
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
