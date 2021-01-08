package proyecto.aplicacion;

import java.util.ArrayList;
/**
 *      Esta clase servira para guardar la informacion de cada pais obtenidos del fichero competicio. En la fase 3 se buscara y añadira
 *      la informacion necesarios para el correcto funcionamiento del programa.
 *
 * */
public class Pais {

    private String nom;
    private double numHabitants;
    private String religio;
    private String bandera;
    private ArrayList<String> llengues;


    /* CONSTRUCTORES*/

    /**
     *      Creara una objeto Project.AppNegocio.Pais nuevo y le asignara la informacion obtenida por los parametros
     *
     *      @param pais (String) Nombre del pais.
     * */

    public Pais(String pais){

        this.nom = pais;

    }

    /**
     *      Creara una objeto Project.AppNegocio.Pais nuevo y le asignara la informacion obtenida por los parametros
     *
     *      @param nom (String) Nombre del pais.
     *      @param numHabitants (String) Numero entero de habitantes .
     *      @param religio (String) Religion oficial del pais(si tiene).
     *      @param bandera (String) URL de la imagen de la bandera del pais.
     *      @param llengues (ArrayList String) Lista de lenguas habladas en el pais.
     *
     *
     * */

    public Pais(String nom,double numHabitants,String religio,String bandera,ArrayList<String> llengues){

        this.nom = nom;
        this.numHabitants = numHabitants;
        this.religio = religio;
        this.bandera = bandera;
        this.llengues.addAll(llengues);

    }

    /* SETTERS*/

    /**
     *      Este metodo guarda el nombre del pais.
     *
     *      @param nom (String) Nombere del pais a guardar.
     *
     * */

    public void setNom(String nom){

        this.nom = nom;

    }

    /**
     *      Este metodo guarda el numero de habitantes del pais.
     *
     *      @param numHabitants (int) Numero de habitantes del pais a guardar.
     *
     * */

    public void setNumHabitants(double numHabitants){

        this.numHabitants = numHabitants;

    }

    /**
     *      Este metodo guarda la religion del pais.
     *
     *      @param religio (String) Religion del pais a guardar.
     *
     * */

    public void setReligio(String religio){

        this.religio = religio;

    }

    /**
     *      Este metodo guarda el URL del la imagen de la bandera del pais.
     *
     *      @param bandera (String) Numero de habitantes del pais a guardar.
     *
     * */

    public void setBandera(String bandera){

        this.bandera = bandera;

    }

    /**
     *      Este metodo guarda las lenguas habladas en el pais.
     *
     *      @param llengues (ArrayList String) Lista de lenguas habladas en el pais a guardar.
     *
     * */

    public void setLlengues(ArrayList<String> llengues){

        this.llengues.addAll(llengues);

    }

    /* GETTERS */

    /**
     *      Método para obtener el nombre del pais.
     *
     *      @return (String) nom.
     * */

    public String getNom(){

        return this.nom;

    }

    /**
     *      Método para obtener el numero de habitantes del pais.
     *
     *      @return (int) Numero de habitantes del pais.
     * */

    public double getNumHabitants(){

        return this.numHabitants;

    }

    /**
     *      Método para obtener la religion del pais.
     *
     *      @return (String) Religion del pais.
     * */

    public String getReligio(){

        return this.religio;

    }

    /**
     *      Método para obtener el URL de la imagen de la bandera del pais.
     *
     *      @return (String) nom.
     * */

    public String getBandera(){

        return this.bandera;

    }

    /**
     *      Método para obtener la lista de lenguas habladas del pais.
     *
     *      @return (ArrayList String) Lista de lenguas habladas.
     * */

    public ArrayList<String> getLlengues(){

        return this.llengues;

    }

}
