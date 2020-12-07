import java.util.ArrayList;
import java.util.Random;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private ArrayList<Tema> temas;
    private ArrayList<Rapero> raperos;
    private int numParticipants;

    protected ArrayList<Rapero> aparellaments;
    //protected String batallesEnCurs[];


    public Batalla () {
        temas = new ArrayList<>();
        raperos = new ArrayList<>();
    }

    public Batalla (String idiomaNatiu, String idiomaAngles) {
        this.idiomaNatiu = idiomaNatiu;
        this.idiomaAngles = idiomaAngles;
        temas = new ArrayList<>();
        raperos = new ArrayList<>();

    }

    public void setTemas(Tema tema) {
        temas.add(tema);
    }

    public void setAparellaments() {
        this.aparellaments = generarAparellaments(raperos);
    }
    public int getNumParticipants(){
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }
    public ArrayList<Rapero> getRaperos() {
        return raperos;
    }

    public void setRaperos(Rapero rapero) {
        raperos.add(rapero);
        numParticipants = raperos.size();
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }
    public String raperosToString () {
        String toString = "";
        for (Rapero o : raperos){
            toString = toString.concat(o.toString());
            toString = toString.concat("\n");
        }
        return toString;
    }

    @Override
    public String toString() {
        return "Batalla{" +
                "\nidiomaNatiu='" + idiomaNatiu + '\'' +
                "\nidiomaAngles='" + idiomaAngles + '\'' +
                "\ntemas=" + temas +
                '}';
    }


    public ArrayList<Rapero> generarAparellaments(ArrayList<Rapero> raperos){

        ArrayList<Rapero> aparellaments = new ArrayList<>();

            Random rand = new Random();

        if((raperos.size()%2)!=0){
            int rapero_out = rand.nextInt(raperos.size() - 1);
            raperos.remove(rapero_out);
        }

        int limit_aparellaments = raperos.size()/2;

        for(int i = 0;i < limit_aparellaments;i++){

            int rand1 = rand.nextInt((raperos.size()/2)-1);

            aparellaments.add(i,raperos.get(raperos.size()-1));
            aparellaments.add(i,raperos.get(rand1));

            raperos.remove(raperos.size()-1);
            raperos.remove(rand1);

        }

        return aparellaments;

    }
    public String escollirTemaEntrada(ArrayList<Tema> temas){
        int num = (int) Math.floor(Math.random()* temas.size());
        return temas.get(num).getNomTema();
    }
    public String tipusBatalla (){
        ArrayList<String> tipusBatalles = new ArrayList<>();
        tipusBatalles.add("BatallaAcapella");
        tipusBatalles.add("BatallaSangre");
        tipusBatalles.add("BatallaEscrita");
        int num = (int)Math.floor(Math.random()*3);
        return tipusBatalles.get(num);
    }

    public void registrar(Rapero rapero){
        raperos.add(rapero);
    }
    /*

    public void simular(){



    }



    public Rapero batallar(String batallesEnCurs[]){



    }

    public float calcularPuntuacio(String frase){



    }

     */
}
