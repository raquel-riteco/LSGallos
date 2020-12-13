import java.util.*;

/**
 *      Esta clase guarda toda la información referente a la competición.
 * */

public class Competicio {

    private String nom;
    private Date dataInici;
    private Date dataFinal;
    private int numFases;

    private final ArrayList<Fase> fases;
    private final ArrayList<String> llistaPaisos;

    /* CONSTRUCTORES */

    /**
     *      Este constructor crea los 2 arrays necesarios, los cuales son atributos de esta clase.
     * */

    public Competicio(){
        fases = new ArrayList<>();
        llistaPaisos = new ArrayList<>();
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

    public void setFase(Fase fase) {
        fases.add(fase);
    }

    /* METODOS */

    /**
     *      Este método es utlizado para actualizar la lista de raperos de la fase en funcion de si esta es la inicial,
     *      intermédia o final, intercanviando en los casos pertinentes el ArrayList de raperos por el ranking de la
     *      fase anterior, de esta manera se trabaja directamente con los raperos que han pasado de fase.
     *
     *      @param fase (Fase) representa la fase actual de la competición.
     * */

    public void actualizarListaRaperos (Fase fase){
        if (numFases == 2 && fase.getNumFase() == 2){
            fase.setRaperos(fases.get(0).getRaperos());
        }else{
            if (fase.getNumFase() == 2) {
                fase.setRaperos(fases.get(0).getRaperos());
            } else if (fase.getNumFase() == 3) {
                fase.setRaperos(fases.get(1).getRaperos());
            }
        }
    }
}
