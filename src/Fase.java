import java.util.ArrayList;

/**
 *      Esta clase representa las características básicas de las fases, las cuales serán creadas a partir de esta
 *      plantilla.
 * */

public class Fase {

    private double pressupost;
    private String pais;
    private int numParticipants;
    private int posMiRapero;
    private final int numFase;

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
     *      @return (Project.AppNegocio.Batalla) batalla creada.
     * */

    public Batalla crearBatalla (String tipusBatalla){
        Batalla batalla;
        switch (tipusBatalla){
            case "Project.AppNegocio.BatallaAcapella":
                batalla = new BatallaAcapella(raperos, posMiRapero);
                batalla.setTipusBatalla("Project.AppNegocio.BatallaAcapella");
                break;
            case "Project.AppNegocio.BatallaSangre":
                batalla = new BatallaSangre(raperos, posMiRapero);
                batalla.setTipusBatalla("Project.AppNegocio.BatallaSangre");
                break;
            case "Project.AppNegocio.BatallaEscrita":
                batalla = new BatallaEscrita(raperos, posMiRapero);
                batalla.setTipusBatalla("Project.AppNegocio.BatallaEscrita");
                break;
            default:
                batalla = null;
        }
        return batalla;
    }

    /**
     *      Este método decide aleatóriamente el tipo de batalla a generar, las opciones son: "Project.AppNegocio.BatallaAcapella",
     *      "Project.AppNegocio.BatallaEscrita" o "Project.AppNegocio.BatallaSangre".
     *
     *      @return Srtrig que contiene una de las ociones anteriormente mencionadas.
     * */

    public String tipusBatalla (){
        ArrayList<String> tipusBatalles = new ArrayList<>();
        tipusBatalles.add("Project.AppNegocio.BatallaAcapella");
        tipusBatalles.add("Project.AppNegocio.BatallaSangre");
        tipusBatalles.add("Project.AppNegocio.BatallaEscrita");
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
     *      Método para guardar un tema en el ArrayList de temas.
     *
     *      @param tema (Project.AppNegocio.Tema) tema a añadir.
     * */

    public void setTemas(Tema tema) {
        temas.add(tema);
    }

    /**
     *      Método para guardar la posición del rapero en la actual lista de raperos.
     *
     *      @param nomArtistic (String) nombre del rapero.
     * */

    public void setPosMiRapero (String nomArtistic) {
        for (Rapero o : getRaperos()){
            if (o.getNickname().equals(nomArtistic)){
                posMiRapero = getRaperos().indexOf(o);
            }
        }
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
        float puntuacion = (float) batalla.calculoPuntuacion();
        raperos.get(posMiRapero).setPuntuacio(puntuacion);
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
     *      Método para obtener la posicion del rapero en el ArrayList raperos de la fase.
     *
     *      @return (int) posMiRapero.
     * */

    public int getPosMiRapero() {
        return posMiRapero;
    }

    /**
     *      Método para obtener la batalla indicada mediante el parámetro num, la cual se saca del ArrayList batallas.
     *
     *      @param num (int) numero de la batalla deseada.
     *
     *      @return (Project.AppNegocio.Batalla) batalla indicada.
     * */

    public Batalla getBatalla(int num){
        return batallas.get(num);
    }

    /**
     *      Método para obtener el ArrayList temas de la fase.
     *
     *      @return (ArrayList Project.AppNegocio.Tema) temas.
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
     *      @return (ArrayList Project.AppNegocio.Rapero) ranking.
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
