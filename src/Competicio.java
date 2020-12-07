import java.util.*;

public class Competicio {

    private String nom;
    private Date dataInici;
    private Date dataFinal;
    private Batalla batallaModel;
    private int numFases;

    private ArrayList<Fase> fases;
    private ArrayList<String> llistaPaisos;

    private ArrayList<Rapero> ranking;

    public Competicio(){
        fases = new ArrayList<>();
        llistaPaisos = new ArrayList<>();
        ranking = new ArrayList<>();
        batallaModel = new Batalla();
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



    public ArrayList<Fase> getFases() {
        return fases;
    }

    public Fase getFase (int num) {
        return fases.get(num);
    }

    public ArrayList<String> getLlistaPaisos() {
        return llistaPaisos;
    }



    public ArrayList<Rapero> getRanking() {
        actualitzarPuntuacio();
        return ranking;
    }

    public Batalla getBatallaModel() {
        return batallaModel;
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



    public void setLlistaPaisos(String pais) {
        llistaPaisos.add(pais);
    }



    public void actualitzarPuntuacio() {
        ranking = batallaModel.getRaperos();
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



    @Override
    public String toString() {
        return "Competicio{" +
                "\nnom='" + nom + '\'' +
                "\ndataInici=" + dataInici +
                "\ndataFinal=" + dataFinal +
                "\nnumFases=" + numFases +
                "\nfases=" + fasesToString() +
                '}';
    }


}
