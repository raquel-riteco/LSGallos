import java.util.ArrayList;

public class Fase {

    private double pressupost;
    private String pais;
    private int numParticipants;
    private int posMiRapero;
    private int numFase;

    private ArrayList<Batalla> batallas;
    private ArrayList<Rapero> raperos;
    private ArrayList<Tema> temas;

    /* CONSTRUCTORES*/

    public Fase(int numFase){
        batallas = new ArrayList<>();
        Batalla batalla1 = crearBatalla(tipusBatalla());
        batalla1.setTema(setTemaBatalla());
        batallas.add(batalla1);
        Batalla batalla2 = crearBatalla(tipusBatalla());
        batalla2.setTema(setTemaBatalla());
        batallas.add(batalla2);

        raperos = new ArrayList<>();
        temas = new ArrayList<>();

        this.numFase = numFase;
    }

    public Batalla crearBatalla (String tipusBatalla){
        Batalla batalla;
        switch (tipusBatalla){
            case "BatallaAcapella":
                batalla = new BatallaAcapella(raperos, posMiRapero);
                break;
            case "BatallaSangre":
                batalla = new BatallaSangre(raperos, posMiRapero);
                break;
            case "BatallaEscrita":
                batalla = new BatallaEscrita(raperos, posMiRapero);
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

    public void setRaperos(Rapero rapero) {
        raperos.add(rapero);
        numParticipants = raperos.size();
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

    /* GETTERS */

    public int getNumParticipants(){
        setNumParticipants();
        return numParticipants;
    }
    public ArrayList<Rapero> getRaperos() {
        return raperos;
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

    public String escollirTemaEntrada(ArrayList<Tema> temas){
        int num = (int) Math.floor(Math.random()* temas.size());
        return temas.get(num).getNomTema();
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
