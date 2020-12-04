import java.util.Date;

public class Rapero {

    private String nomComplet;
    private String nomArtistic;
    private Date dataNaixement;
    private Long nivell;
    private String foto;
    private float puntuacio;
    private String paisString;
    private Pais pais;

    public Rapero (){

    }
    public Rapero(String nomComplet, String nomArtistic, Date dataNaixement, String paisString, Long nivell, String foto) {
        this.nomComplet = nomComplet;
        this.nomArtistic = nomArtistic;
        this.dataNaixement = dataNaixement;
        this.nivell = nivell;
        this.foto = foto;
        this.paisString = paisString;
    }

    public void setNomComplet (String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public void setNomArtistic (String nomArtistic) {
        this.nomArtistic = nomArtistic;
    }
    public void setDataNaixement (Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }
    public void setNivell (Long nivell) {
        this.nivell = nivell;
    }
    public void setFoto (String foto) {
        this.foto = foto;
    }
    public void setPuntuacio(float puntuacio) {
        this.puntuacio = puntuacio;
    }
    public void setPaisRapero(String nomPais) {

    }
    public void setPaisString(String paisString) {
        this.paisString = paisString;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public float getPuntuacio() {
        return puntuacio;
    }

    public Long getNivell() {
        return nivell;
    }

    public String getFoto() {
        return foto;
    }

    public String getNomArtistic() {
        return nomArtistic;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getPaisString() {
        return paisString;
    }


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
