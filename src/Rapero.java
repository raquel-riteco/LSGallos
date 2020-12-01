import java.util.Date;

public class Rapero {

    private String nomComplet;
    private String nomArtistic;
    private Date dataNaixement;
    private int nivell;
    private String foto;
    private float puntuacio;
    private String paisString;
    private Pais pais;

    public void setNomComplet (String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public void setNomArtistic (String nomArtistic) {
        this.nomArtistic = nomArtistic;
    }
    public void setDataNaixement (Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }
    public void setNivell (int nivell) {
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

    @Override
    public String toString() {
        return "Rapero{" +
                "nomComplet='" + nomComplet + '\'' +
                ", nomArtistic='" + nomArtistic + '\'' +
                ", dataNaixement=" + dataNaixement +
                ", nivell=" + nivell +
                ", foto='" + foto + '\'' +
                ", puntuacio=" + puntuacio +
                ", pais=" + pais +
                ", paisString=" + paisString +
                '}';
    }
}
