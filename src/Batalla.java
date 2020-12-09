import java.util.ArrayList;
import java.util.Random;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private String tema;
    private String nomRival;
    private String tipusBatalla;

    private int numBatalla;

    private ArrayList<ArrayList<Rapero>> aparellaments;
    private ArrayList<ArrayList<String>> estrofas;

    /* CONSTRUCTORES */

    public Batalla (ArrayList<Rapero> raperos, int posMiRapero) {
        aparellaments = new ArrayList<>();
        generarAparellaments(raperos, posMiRapero);
    }

    /* SETTERS */

    public void generarAparellaments(ArrayList<Rapero> raperos, int posMiRapero){

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

    public void setNomRival(String nomRival) {
        this.nomRival = nomRival;
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

    /* MÉTODOS */

    public ArrayList<Rapero> actualitzarBatallesOcultes(ArrayList<ArrayList<Rapero>> raperos, int posMiRapero){

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

        return aux;

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

        for (int i = 0; i < ultimasLetras.size(); i++) {
            int numRimas = 0;
            String actual = ultimasLetras.get(i);
            switch (i){
                case 0:
                    if (actual.equals(ultimasLetras.get(1)) || actual.equals(ultimasLetras.get(2)) || actual.equals(ultimasLetras.get(3))){
                        numRimas++;
                    }
                    break;
                case 1:
                    if (actual.equals(ultimasLetras.get(0)) || actual.equals(ultimasLetras.get(2)) || actual.equals(ultimasLetras.get(3))){
                        numRimas++;
                    }
                    break;
                case 2:
                    if (actual.equals(ultimasLetras.get(0)) || actual.equals(ultimasLetras.get(1)) || actual.equals(ultimasLetras.get(3))){
                        numRimas++;
                    }
                    break;
                case 3:
                    if (actual.equals(ultimasLetras.get(0)) || actual.equals(ultimasLetras.get(1)) || actual.equals(ultimasLetras.get(2))){
                        numRimas++;
                    }
                    break;
            }
            sumaNumRimes.add(numRimas);
        }
        return sumaNumRimes;
    }

    public void simular(){



    }


}
