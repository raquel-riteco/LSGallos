import java.util.Date;

public class Rapero {

    private String nomComplet;
    private String nomArtistic;
    private Date dataNaixement;
    private int nivell;
    private String foto;
    private float puntuacio;
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
        //pais = setPais(nomPais);
    }
}
