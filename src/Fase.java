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
     *      @param tipusBatalla (String) que puede ser "BatallaAcapella", "BatallaEscrita" o "BatallaSangre".
     *
     *      @return (Batalla) batalla creada.
     * */

    public Batalla crearBatalla (String tipusBatalla){
        Batalla batalla;
        switch (tipusBatalla){
            case "BatallaAcapella":
                batalla = new BatallaAcapella(raperos, posMiRapero);
                batalla.setTipusBatalla("BatallaAcapella");
                break;
            case "BatallaSangre":
                batalla = new BatallaSangre(raperos, posMiRapero);
                batalla.setTipusBatalla("BatallaSangre");
                break;
            case "BatallaEscrita":
                batalla = new BatallaEscrita(raperos, posMiRapero);
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

    public void setPressupost(double pressupost) {
        this.pressupost = pressupost;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setNumParticipants() {
        numParticipants = raperos.size();
    }

    public void setRapero(Rapero rapero) {
        raperos.add(rapero);
        numParticipants = raperos.size();
    }

    public void setRaperos (ArrayList<Rapero> raperos){
        this.raperos = raperos;
    }

    public void setTemas(Tema tema) {
        temas.add(tema);
    }

    public void setPosMiRapero (String nomArtistic) {
        for (Rapero o : getRaperos()){
            if (o.getNomArtistic().equals(nomArtistic)){
                posMiRapero = getRaperos().indexOf(o);
            }
        }
    }

    public void setRanking() {
        ranking = raperos;
        ranking.sort((o1, o2) -> Float.compare(o2.getPuntuacio(), o1.getPuntuacio()));
    }

    public void setPuntuacionBatalla (int numBatalla){
        Batalla batalla = getBatalla(numBatalla);
        float puntuacion = (float) batalla.calculoPuntuacion();
        raperos.get(posMiRapero).setPuntuacio(puntuacion);
    }
    /* GETTERS */

    public int getNumParticipants(){
        setNumParticipants();
        return numParticipants;
    }
    public ArrayList<Rapero> getRaperos() {
        return raperos;
    }

    public ArrayList<Batalla> getBatallas() {
        return batallas;
    }

    public int getPosMiRapero() {
        return posMiRapero;
    }

    public Batalla getBatalla(int num){
        return batallas.get(num);
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    public int getNumFase() {
        return numFase;
    }

    public ArrayList<Rapero> getRanking() {
        setRanking();
        return ranking;
    }

    /* MÉTODOS */

    public void simulaciones (int numBatalla){
        Batalla actual = batallas.get(numBatalla);
        for (int i = 0; i < actual.getAparellaments().size(); i++) {
            actual.simularBatalla(temas, crearBatalla(tipusBatalla()), setTemaBatalla(), i, raperos);
        }
    }

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
