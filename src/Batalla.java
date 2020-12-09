import java.util.ArrayList;
import java.util.Random;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private String tema;
    private String nomRival;
    private String tipusBatalla;

    private int numAparellament;
    private int numBatalla;
    private int posMiRapero;

    private ArrayList<ArrayList<Rapero>> aparellaments;
    private ArrayList<ArrayList<String>> estrofas;

    /* CONSTRUCTORES */

    public Batalla (ArrayList<Rapero> raperos, int posMiRapero) {
        aparellaments = new ArrayList<>();
        generarAparellaments(raperos, posMiRapero);
    }

    /* SETTERS */

    public void generarAparellaments(ArrayList<Rapero> raperos, int posMiRapero){
        String nomArtistic = raperos.get(posMiRapero).getNomArtistic();
        ArrayList<Rapero> aux = new ArrayList<>(raperos);
        if ((aux.size() % 2) != 0){
            int rapero_out;
            do {
                rapero_out = (int) Math.floor(Math.random()*aux.size());
            }while(rapero_out == posMiRapero);
            aux.remove(rapero_out);
        }

        for(int i = 0; i < raperos.size() / 2 ; i++){
            ArrayList<Rapero> parella = new ArrayList<>();
            int num1, num2;
            do {
                num1 = (int) Math.floor(Math.random()* aux.size());
                num2 = (int) Math.floor(Math.random()* aux.size());
            }while (num1 == num2);

            Rapero rapero1 = aux.get(num1);
            Rapero rapero2 = aux.get(num2);
            parella.add(rapero1);
            parella.add(rapero2);

            aparellaments.add(parella);
            if (num1 < num2){
                aux.remove(num1);
                aux.remove(num2 - 1);
            }else {
                aux.remove(num2);
                aux.remove(num1 - 1);
            }
        }
        setNumAparellament(nomArtistic);
        setNomRival(nomArtistic);
    }

    public void setTipusBatalla(String tipusBatalla) {
        this.tipusBatalla = tipusBatalla;
    }

    public void setEstrofas(ArrayList<ArrayList<String>> estrofas) {
        this.estrofas = estrofas;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setNumAparellament(String nomArtistic) {
        for (ArrayList<Rapero> parella : aparellaments) {
            if (parella.get(0).getNomArtistic().equals(nomArtistic) || parella.get(1).getNomArtistic().equals(nomArtistic)){
                numAparellament = aparellaments.indexOf(parella);
            }
        }
    }

    public void setNomRival(String nomArtistic) {
        for (ArrayList<Rapero> parella : aparellaments) {
            if (parella.get(0).getNomArtistic().equals(nomArtistic)){
                nomRival = parella.get(1).getNomArtistic();
            }else if (parella.get(1).getNomArtistic().equals(nomArtistic)){
                nomRival = parella.get(0).getNomArtistic();
            }
        }
    }

    public void setNumBatalla(int numBatalla) {
        this.numBatalla = numBatalla;
    }

    /* GETTERS */

    public ArrayList<ArrayList<Rapero>> getAparellaments() {
        return aparellaments;
    }

    public String getNomRival() {
        return nomRival;
    }

    public int getNumBatalla() {
        return numBatalla;
    }

    public String getTipusBatalla() {
        return tipusBatalla;
    }

    public String getTema() {
        return tema;
    }

    public int getNumAparellament() {
        return numAparellament;
    }



    /* MÉTODOS */

    public double calculoPuntuacion(){
        return 0;
    }

    public float puntuacionSimulaciones (float R){
        return 0;
    }

    public int calcularRimes(){
        int R = 0;
        ArrayList<ArrayList<Integer>> rima = new ArrayList<>();
        for (ArrayList<String> estrofa: estrofas) {
            ArrayList<String> ultimasLetras = new ArrayList<>();
            for (String o: estrofa) {
                 ultimasLetras.add(o.substring(o.length() - 2));
            }
            rima.add(numRimas(ultimasLetras));
        }
        for (ArrayList<Integer> rimasPorEstrofa : rima) {
            for (Integer rimasPorVerso : rimasPorEstrofa) {
                R += rimasPorVerso;
            }
        }
        return R;
    }

    public ArrayList<Integer> numRimas(ArrayList<String> ultimasLetras){
        ArrayList<Integer> sumaNumRimes = new ArrayList<>();

        Tema.Estrofa.extractRimes(sumaNumRimes, ultimasLetras);
        return sumaNumRimes;
    }

}
