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

    /**
     *      Método para conseguir el nom de la competición.
     *
     *      @return String equivalente al nom.
     * */

    public String getNom() {
        return nom;
    }

    /**
     *      Método para conseguir la fecha de inicio de la competición.
     *
     *      @return Date equivalente a dataInici.
     * */

    public Date getDataInici() {
        return dataInici;
    }

    /**
     *      Método para conseguir la fecha de finalización de la competición.
     *
     *      @return Date equivalente a la dataFinal.
     * */

    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     *      Método para conseguir el numero de fases de la competición.
     *
     *      @return int equivalente a numFases
     * */

    public int getNumFases() {
        return numFases;
    }

    /**
     *      Método para conseguir el ArrayList de fases de la competición.
     *
     *      @return ArrayList de Project.AppNegocio.Fase equivalente a fases.
     * */

    public ArrayList<Fase> getFases() {
        return fases;
    }

    /**
     *      Método para conseguir una de las fases de la competición.
     *
     *      @param num (int) número de fase deseado.
     *      @return Project.AppNegocio.Fase equivalente a la Project.AppNegocio.Fase deseada.
     * */

    public Fase getFase (int num) {
        return fases.get(num);
    }

    /**
     *      Método para conseguir el ArrayList de paises de la competición.
     *
     *      @return ArrayList de String equivalente a llistaPaisos.
     * */

    public ArrayList<String> getLlistaPaisos() {
        return llistaPaisos;
    }

    /* SETTERS */
    /* UTILIZADOS EN FICHEROS */

    /**
     *      Método para guardar el nom de la competición.
     *
     *      @param nom (String) nombre.
     * */

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *      Método para guardar la dataInici de la competición.
     *
     *      @param dataInici (Date) fecha inicio.
     * */

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    /**
     *      Método para guardar la dataFinal de la competición.
     *
     *      @param dataFinal (Date) fecha finalización.
     * */

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     *      Método para inicializar numFases, el cual se iguala al tamaño del ArrayList defases.
     *
     * */

    public void setNumFases() {
        numFases = fases.size();
    }

    /**
     *      Método para guardar un pais en el ArrayList llistaPaisos.
     *
     *      @param pais (String) nombre del pais.
     * */

    public void setLlistaPaisos(String pais) {
        llistaPaisos.add(pais);
    }

    /**
     *      Método para guardar una fase en el ArrayList fases.
     *
     *      @param fase (Project.AppNegocio.Fase) la fase a añadir.
     * */

    public void setFase(Fase fase) {
        fases.add(fase);
    }

    /* METODOS */

    /**
     *      Este método es utlizado para actualizar la lista de raperos de la fase en funcion de si esta es la inicial,
     *      intermédia o final, intercanviando en los casos pertinentes el ArrayList de raperos por el ranking de la
     *      fase anterior, de esta manera se trabaja directamente con los raperos que han pasado de fase.
     *
     *      @param fase (Project.AppNegocio.Fase) representa la fase actual de la competición.
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
