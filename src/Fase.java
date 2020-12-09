import java.util.ArrayList;
import java.util.Random;

public class Fase {

    private double pressupost;
    private String pais;
    private int numParticipants;
    private int posMiRapero;
    private int numFase;

    private ArrayList<Batalla> batallas;
    private ArrayList<Rapero> raperos;
    private ArrayList<Tema> temas;
    private ArrayList<Rapero> ranking;

    /* CONSTRUCTORES*/

    public Fase(int numFase){
        batallas = new ArrayList<>();
        Batalla batalla1 = crearBatalla(tipusBatalla());
        batalla1.setTema(setTemaBatalla());
        batalla1.setNumBatalla(1);
        batallas.add(batalla1);
        Batalla batalla2 = crearBatalla(tipusBatalla());
        batalla2.setTema(setTemaBatalla());
        batalla2.setNumBatalla(2);
        batallas.add(batalla2);

        raperos = new ArrayList<>();
        temas = new ArrayList<>();
        ranking = new ArrayList<>();

        this.numFase = numFase;
    }

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
    public String tipusBatalla (){
        ArrayList<String> tipusBatalles = new ArrayList<>();
        tipusBatalles.add("BatallaAcapella");
        tipusBatalles.add("BatallaSangre");
        tipusBatalles.add("BatallaEscrita");
        int num = (int)Math.floor(Math.random()*3);
        return tipusBatalles.get(num);
    }


    /* SETTERS*/
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

    public void setNumFase(int numFase) {
        this.numFase = numFase;
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
        return ranking;
    }

    /* TO STRINGS */

    @Override
    public String toString() {
        return "Fase{" +
                "pressupost=" + pressupost +
                ", pais='" + pais + '\'' +
                ", raperos=" + raperosToString() +
                ", temas=" + temasToString() +
                '}';
    }

    public String temasToString() {
        String stringTemas = "";
        for (Tema o : temas) {
            stringTemas = stringTemas.concat(o.toString());
        }
        return temasToString();
    }

    public String raperosToString () {
        String toString = "";
        for (Rapero o : raperos){
            toString = toString.concat(o.toString());
            toString = toString.concat("\n");
        }
        return toString;
    }

    /* MÉTODOS */

    public void actualitzarBatallesOcultes (){

        ArrayList<Rapero> aux = new ArrayList<>();

        for(ArrayList<Rapero> o: raperos){

            if(o.get(0).getPuntuacio()>o.get(1).getPuntuacio()){

                aux.add(o.get(0));

            }else if(o.get(0).getPuntuacio()<o.get(1).getPuntuacio()){

                aux.add(o.get(1));

            }else{

                Random a = new Random();

                aux.add(o.get(a.nextInt(1)));
            }
        }
    }

    public void simulacions (){
        for (Batalla batalla : batallas) {
            for (ArrayList<Rapero> parella : batalla.getAparellaments()) {
                String temaBatalla = setTemaBatalla();
                Batalla battle = crearBatalla(tipusBatalla());
                Long nivel1 = parella.get(0).getNivell();
                Long nivel2 = parella.get(1).getNivell();
                for (Tema tema : temas) {
                    if (tema.getNomTema() == temaBatalla){
                        parella.get(0).setPuntuacio(battle.puntuacionSimulaciones(tema.getEstrofa(nivel1).getPuntuacion().get(0)));
                        parella.get(0).setPuntuacio(battle.puntuacionSimulaciones(tema.getEstrofa(nivel1).getPuntuacion().get(1)));
                        parella.get(1).setPuntuacio(battle.puntuacionSimulaciones(tema.getEstrofa(nivel1).getPuntuacion().get(0)));
                        parella.get(1).setPuntuacio(battle.puntuacionSimulaciones(tema.getEstrofa(nivel1).getPuntuacion().get(1)));
                    }
                }
            }
        }
    }

    public double calculoPuntuacionSimulaciones (){

    }




    /*
    private Batalla batalles[2];
    private Rapero raperos[];

    public void descartarRaperos(Rapero raperos[1*]){

        //Funcio

    }

    public void executarFase(){

        //Funcio

    }
    */
}
