import java.util.*;

public class Competicio {

    private String nom;
    private Date dataInici;
    private Date dataFinal;
    private int numFases;

    private ArrayList<Fase> fases;
    private ArrayList<String> llistaPaisos;
    private ArrayList<Rapero> ranking;

    /* CONSTRUCTORES */

    public Competicio(){
        fases = new ArrayList<>();
        llistaPaisos = new ArrayList<>();
        ranking = new ArrayList<>();
        crearFases();
    }

    public void crearFases (){
        Fase faseInicial = new Fase(1);
        fases.add(faseInicial);
        Fase faseFinal = new Fase(2);
        fases.add(faseFinal);
        if (numFases == 3){
            Fase faseIntermedia = new Fase(2);
            fases.add(faseIntermedia);
            faseFinal.setNumFase(3);
        }
    }

    /* GETTERS */

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

    /*
    public ArrayList<Rapero> getRanking() {
        actualitzarPuntuacio();
        return ranking;
    }

     */


    /* SETTERS */
    /* UTILIZADOS EN FICHEROS */

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setNumFases() {
        numFases = fases.size();
    }

    public void setLlistaPaisos(String pais) {
        llistaPaisos.add(pais);
    }

    /*
    public void actualitzarPuntuacio() {
        ranking = batallaModel.getRaperos();
        ranking.sort((o1, o2) -> Float.compare(o2.getPuntuacio(), o1.getPuntuacio()));
    }

     */

    /* TO STRING */

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
    public String fasesToString (){
        String stringFases = "";
        for (Fase o : fases) {
            stringFases = stringFases.concat(o.toString());
        }
        return stringFases;
    }



}
