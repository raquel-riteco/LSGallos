import java.util.ArrayList;

public class Pais {

    private String nom;
    private double numHabitants;
    private String religio;
    private String bandera;
    private ArrayList<String> llengues;

    public Pais(){

    }

    public Pais(String nom,double numHabitants,String religio,String bandera,ArrayList<String> llengues){

        this.nom = nom;
        this.numHabitants = numHabitants;
        this.religio = religio;
        this.bandera = bandera;
        this.llengues.addAll(llengues);

    }

    public void setNom(String nom){

        this.nom = nom;

    }

    public void setNumHabitants(double numHabitants){

        this.numHabitants = numHabitants;

    }
    public void setReligio(String religio){

        this.religio = religio;

    }
    public void setBandera(String bandera){

        this.bandera = bandera;

    }
    public void setLlengues(ArrayList<String> llengues){

        this.llengues.addAll(llengues);

    }

    public String getNom(){

        return this.nom;

    }

    public double getNumHabitants(){

        return this.numHabitants;

    }
    public String getReligio(){

        return this.religio;

    }
    public String getBandera(){

        return this.bandera;

    }
    public ArrayList<String> getLlengues(){

        return this.llengues;

    }

}
