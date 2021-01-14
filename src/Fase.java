import java.util.ArrayList;

/**
 *      Esta clase representa las características básicas de las fases, las cuales serán creadas a partir de esta
 *      plantilla.
 * */

public class Fase {

    private double pressupost;
    private String pais;
    private int numParticipants;
    private final int numFase;
    private String nombreRapero;

    private final ArrayList<Batalla> batallas;
    private ArrayList<Rapero> raperos;
    private final ArrayList<Tema> temas;
    private ArrayList<Rapero> ranking;

    /* CONSTRUCTORES*/

    /**
     *      Este constructor crea los ArrayList necesários para cada uno de los argumentos de la clase.
     *      Además, asigna el valor del parámetro al atributo numFase.
     *
     *      El parámetro es pasado automáticamente por el programa.
     *      @param numFase (int) numero identificador del objeto.
     * */

    public Fase(int numFase){
        raperos = new ArrayList<>();
        temas = new ArrayList<>();
        ranking = new ArrayList<>();
        batallas = new ArrayList<>();
        this.numFase = numFase;
    }

    /**
     *      Este método construye las 2 batallas pertenecientes a la fase y las añade al ArrayList de batallas.
     * */

    public void formarBatallas(){
        Batalla batalla1 = crearBatalla(tipusBatalla());
        batalla1.setTema(setTemaBatalla());
        batalla1.setNumBatalla(1);
        batallas.add(batalla1);
        Batalla batalla2 = crearBatalla(tipusBatalla());
        batalla2.setTema(setTemaBatalla());
        batalla2.setNumBatalla(2);
        batallas.add(batalla2);
    }

    /**
     *      Generador de la batalla en función de tu tipo, el cual está especificado en el String que le llega como
     *      parámetro.
     *
     *      El parámetro es pasado automáticamente por el programa.
     *      @param tipusBatalla (String) que puede ser "Project.AppNegocio.BatallaAcapella", "Project.AppNegocio.BatallaEscrita" o "Project.AppNegocio.BatallaSangre".
     *
     *      @return (Batalla) batalla creada.
     * */

    public Batalla crearBatalla (String tipusBatalla){
        Batalla batalla;
        switch (tipusBatalla){
            case "BatallaAcapella":
                batalla = new BatallaAcapella(raperos, getPosMiRapero("raperos"));
                batalla.setTipusBatalla("BatallaAcapella");
                break;
            case "BatallaSangre":
                batalla = new BatallaSangre(raperos, getPosMiRapero("raperos"));
                batalla.setTipusBatalla("BatallaSangre");
                break;
            case "BatallaEscrita":
                batalla = new BatallaEscrita(raperos, getPosMiRapero("raperos"));
                batalla.setTipusBatalla("BatallaEscrita");
                break;
            default:
                batalla = null;
        }
        return batalla;
    }

    /**
     *      Este método decide aleatóriamente el tipo de batalla a generar, las opciones son: "BatallaAcapella",
     *      "BatallaEscrita" o "BatallaSangre".
     *
     *      @return Srtrig que contiene una de las ociones anteriormente mencionadas.
     * */

    public String tipusBatalla (){
        ArrayList<String> tipusBatalles = new ArrayList<>();
        tipusBatalles.add("BatallaAcapella");
        tipusBatalles.add("BatallaSangre");
        tipusBatalles.add("BatallaEscrita");
        int num = (int)Math.floor(Math.random()*3);
        return tipusBatalles.get(num);
    }


    /* SETTERS*/

    /**
     *      Este método indica el tema a seguir en una batalla, este es generado aleatoriamente a partir de los temas
     *      existentes, los cuales se han guardado a partir del fichero Batalles.
     *
     *      @return String con el nombre del tema.
     * */

    public String setTemaBatalla(){
        return temas.get((int) Math.floor(Math.random()* temas.size())).getNomTema();
    }

    /**
     *      Método para guardar el pressupost de la fase.
     *
     *      @param pressupost (double) presupuesto de la fase.
     * */

    public void setPressupost(double pressupost) {
        this.pressupost = pressupost;
    }

    /**
     *      Método para guardar el pais de la fase.
     *
     *      @param pais (String) nombre del país.
     * */

    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     *      Método para instanciar la cantidad de participantes de la fase, igualando numParticipants al tamaño del
     *      ArrayList raperos.
     * */

    public void setNumParticipants() {
        numParticipants = raperos.size();
    }

    /**
     *      Método para guardar un rapero en el ArrayList raperos. Este método también actualiza el numero de
     *      participantes.
     *
     *      @param rapero (Project.AppNegocio.Rapero) objeto de la clase Project.AppNegocio.Rapero que se quiere guardar.
     * */

    public void setRapero(Rapero rapero) {
        raperos.add(rapero);
        numParticipants = raperos.size();
    }

    /**
     *      Método para guardar el ArrayList raperos.
     *
     *      @param raperos (ArrayList Project.AppNegocio.Rapero) ArrayList de raperos.
     * */

    public void setRaperos (ArrayList<Rapero> raperos){
        this.raperos = raperos;
    }

    /**
     *      Metodo para guardar el nombre con el que el usuario ha realizado el login.
     *
     *      @param nombreRapero String con el nombre.
     * */

    public void setNombreRapero (String nombreRapero){
        this.nombreRapero = nombreRapero;
    }


    /**
     *      Método para guardar un tema en el ArrayList de temas.
     *
     *      @param tema (Tema) tema a añadir.
     * */

    public void setTemas(Tema tema) {
        temas.add(tema);
    }


    /**
     *      Método para actualizar el ranking de la fase.
     * */

    public void setRanking() {
        ranking = raperos;
        ranking.sort((o1, o2) -> Double.compare(o2.getPuntuacio(), o1.getPuntuacio()));
    }

    /**
     *      Método para actualizar la puntuación de los raperos de la fase.
     *
     *      @param numBatalla (int) número de la batalla de la cual se quiere actualizar sus puntuaciones.
     * */

    public void setPuntuacionBatalla (int numBatalla){
        Batalla batalla = getBatalla(numBatalla);
        double puntuacion = batalla.calculoPuntuacion();
        raperos.get(getPosMiRapero("raperos")).setPuntuacio(puntuacion);
    }
    /* GETTERS */

    /**
     *      Método para obtener el numParticipants de la fase. Antes de devolverlo, actualiza este número para
     *      asegurarse de que es el correcto.
     *
     *      @return (int) numParticipants.
     * */

    public int getNumParticipants(){
        setNumParticipants();
        return numParticipants;
    }

    /**
     *      Método para obtener el ArrayList raperos de la fase.
     *
     *      @return (ArrayList Project.AppNegocio.Rapero) raperos.
     * */

    public ArrayList<Rapero> getRaperos() {
        return raperos;
    }

    /**
     *      Método para obtener el ArrayList batallas de la fase.
     *
     *      @return (ArrayList Project.AppNegocio.Batalla) batallas.
     * */

    public ArrayList<Batalla> getBatallas() {
        return batallas;
    }

    /**
     *      Método para obtener la posicion del rapero en el ArrayList raperos o de ranquing de la fase.
     *
     *      @param deDonde (String) parametro que indica si la busqueda de la posicion se realza en el arraylist de
     *                     raperos o en el del ranquing.
     *
     *      @return (int) posMiRapero.
     * */
    public int getPosMiRapero (String deDonde) {
        if (deDonde.equals("raperos")){
            for (Rapero o : getRaperos()){
                if (o.getNickname().equals(nombreRapero)){
                    return getRaperos().indexOf(o);
                }
            }
        }else{
            for (Rapero o : getRanking()){
                if (o.getNickname().equals(nombreRapero)){
                    return getRanking().indexOf(o);
                }
            }
        }
        return -1;
    }

    /**
     *      Método para obtener la batalla indicada mediante el parámetro num, la cual se saca del ArrayList batallas.
     *
     *      @param num (int) numero de la batalla deseada.
     *
     *      @return (Batalla) batalla indicada.
     * */

    public Batalla getBatalla(int num){
        return batallas.get(num);
    }

    /**
     *      Método para obtener el ArrayList temas de la fase.
     *
     *      @return (ArrayList Tema) temas.
     * */

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    /**
     *      Método para obtener el numero de la fase.
     *
     *      @return (int) numFase.
     * */

    public int getNumFase() {
        return numFase;
    }

    /**
     *      Método para obtener el ArrayList ranking de la fase. Antes de devolverlo, este se actualiza para
     *      garantizar que es el correcto.
     *
     *      @return (Rapero) ranking.
     * */

    public ArrayList<Rapero> getRanking() {
        setRanking();
        return ranking;
    }

    /* MÉTODOS */

    /**
     *      Método para simular las batallas de la batalla actual, en las cuales el rapero seleccionado no participa.
     *      Llama al método simularBatalla de la clase Project.AppNegocio.Batalla.
     *
     *      @param numBatalla  (int) numero de la batalla actual en el ArrayList de batallas.
     * */

    public void simulaciones (int numBatalla){
        Batalla actual = batallas.get(numBatalla);
        for (int i = 0; i < actual.getAparellaments().size(); i++) {
            actual.simularBatalla(temas, crearBatalla(tipusBatalla()), setTemaBatalla(), i, raperos);
        }
    }

    /**
     *      Método para actualizar el ArrayList de raperos de la fase en función del ranking de la fase anterior.
     *
     *      @param maxFases  (int) numero de fases de la competición.
     * */

    public void descartarRaperos(int maxFases){
        if (numFase + 1 == maxFases){
            ArrayList<Rapero> finalistas = new ArrayList<>();
            finalistas.add(ranking.get(0));
            finalistas.add(ranking.get(1));
            raperos = finalistas;
        }else{
            ArrayList<Rapero> faseIntermedia = new ArrayList<>();
            for (int i = 0; i < ranking.size()/2; i++) {
                faseIntermedia.add(ranking.get(i));
            }
            raperos = faseIntermedia;
        }
    }
}
