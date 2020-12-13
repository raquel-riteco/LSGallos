import java.util.Date;

/**
 *      Esta clase servira para guardar la informacion de cada rapero sea obtenido a traves del fichero competicio o entrado por el usuario.
 *
 * */

public class Rapero {

    private String nomComplet;
    private String nomArtistic;
    private String dataNaixement;
    private Long nivell;
    private String foto;
    private float puntuacio;
    private String paisString;

    private Pais pais;

    /* CONSTRUCTORES*/


    /**
     *      Creara una objeto Pais nuevo y le asignara la informacion obtenida por los parametros
     *
     *      @param nomComplet (String) Nombre real del Rapero.
     *      @param nomArtistic (String) Nombre artistico del rapero.
     *      @param dataNaixement (String) Fecha de nacimiento del rapero.
     *      @param paisString (String) String con el nombre del pais de origen.
     *      @param nivell (long) Nivel del rapero.
     *      @param foto (String) URL de la imagen de perfil del rapero.
     *
     *
     * */

    public Rapero(String nomComplet, String nomArtistic, String dataNaixement, String paisString, Long nivell, String foto) {
        this.nomComplet = nomComplet;
        this.nomArtistic = nomArtistic;
        this.dataNaixement = dataNaixement;
        this.nivell = nivell;
        this.foto = foto;
        this.paisString = paisString;
    }

    /*SETTERS*/

    /**
     *      Metodo para guardar el nombre real del rapero.
     *
     *      @param nomComplet (String) Nombre real del rapero.
     */

    public void setNomComplet (String nomComplet) {
        this.nomComplet = nomComplet;
    }

    /**
     *      Metodo para guardar el nombre artistico del rapero.
     *
     *      @param nomArtistic (String) Nombre artistico del rapero.
     */

    public void setNomArtistic (String nomArtistic) {
        this.nomArtistic = nomArtistic;
    }

    /**
     *      Metodo para guardar la fecha de nacimiento del rapero.
     *
     *      @param dataNaixement (String) Fecha de nacimiento del rapero.
     */

    public void setDataNaixement (String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    /**
     *      Metodo para guardar el nivel del rapero.
     *
     *      @param nivell (Long) Nivel del rapero.
     */

    public void setNivell (Long nivell) {
        this.nivell = nivell;
    }

    /**
     *      Metodo para guardar el URL de la imagen de perfil del rapero.
     *
     *      @param foto (String) URL de la imagen de perfil del rapero.
     */

    public void setFoto (String foto) {
        this.foto = foto;
    }

    /**
     *      Metodo para guardar la puntuacion obtenida por el rapero.
     *
     *      @param puntuacio (float) Puntuacion del rapero.
     */

    public void setPuntuacio(float puntuacio) {
        this.puntuacio += puntuacio;
    }

    /**
     *      Metodo para guardar el nombre del pais del rapero.
     *
     *      @param nomPais (String) Pais de origen del rapero.
     */

    public void setPaisRapero(String nomPais) {
        this.pais.setNom(nomPais);
    }

    /**
     *      Metodo para guardar el nombre del pais del rapero.
     *
     *      @param paisString (String) Pais del rapero.
     */

    public void setPaisString(String paisString) {
        this.paisString = paisString;
    }

    /* GETTERS */

    /**
     *      Método para obtener la fecha de nacimiento del rapero.
     *
     *      @return (String) Fecha de nacimiento del rapero.
     * */

    public String getDataNaixement() {
        return dataNaixement;
    }

    /**
     *      Método para obtener la puntuaicon del rapero.
     *
     *      @return (float) Puntuacion del rapero.
     * */

    public float getPuntuacio() {
        return puntuacio;
    }

    /**
     *      Método para obtener el nivel del rapero.
     *
     *      @return (Long) Nivel del rapero.
     * */

    public Long getNivell() {
        return nivell;
    }

    /**
     *      Método para obtener el URL de la imagen de perfil del rapero.
     *
     *      @return (String) URL de la imagen de perfil del rapero.
     * */

    public String getFoto() {
        return foto;
    }

    /**
     *      Método para obtener el nombre artistico del rapero.
     *
     *      @return (String) Nombre artistico del rapero.
     * */

    public String getNomArtistic() {
        return nomArtistic;
    }

    /**
     *      Método para obtener el nombre real del rapero.
     *
     *      @return (String) Nombre real del rapero.
     * */

    public String getNomComplet() {
        return nomComplet;
    }

    /**
     *      Método para obtener el nombre del pais del rapero.
     *
     *      @return (String) Nombre del pais del rapero.
     * */

    public String getPaisString() {
        return paisString;
    }

    /* METODOS */


    /**
     *      Metodo para obtener en forma de String la infromacion del Rapero.
     * */

    @Override
    public String toString() {
        return "Rapero{" +
                "\nnomComplet='" + nomComplet + '\'' +
                "\nnomArtistic='" + nomArtistic + '\'' +
                "\ndataNaixement=" + dataNaixement +
                "\nnivell=" + nivell +
                "\nfoto='" + foto + '\'' +
                "\npuntuacio=" + puntuacio +
                "\npais=" + pais +
                "\npaisString=" + paisString +
                '}';
    }
}
