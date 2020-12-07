import java.util.ArrayList;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private ArrayList<Tema> temas;
    private ArrayList<Rapero> raperos;
    private int numParticipants;
    private ArrayList<ArrayList<String>> estrofas;

    private ArrayList<ArrayList<Rapero>> aparellaments;
    //protected String batallesEnCurs[];


    public Batalla () {
        temas = new ArrayList<>();
        raperos = new ArrayList<>();
        aparellaments = new ArrayList<>();
    }

    public Batalla (String idiomaNatiu, String idiomaAngles) {
        this.idiomaNatiu = idiomaNatiu;
        this.idiomaAngles = idiomaAngles;
        temas = new ArrayList<>();
        raperos = new ArrayList<>();
        aparellaments = new ArrayList<>();

    }

    public void setTemas(Tema tema) {
        temas.add(tema);
    }

    public void setEstrofas(ArrayList<ArrayList<String>> estrofas) {
        this.estrofas = estrofas;
    }

    public void setAparellaments(int posMiRapero) {
        this.aparellaments = generarAparellaments(raperos, posMiRapero);

    }

    public ArrayList<ArrayList<Rapero>> getAparellaments() {
        return aparellaments;
    }

    public int getNumParticipants(){
        setNumParticipants();
        return numParticipants;
    }

    public void setNumParticipants() {
        numParticipants = raperos.size();
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


    public ArrayList<ArrayList<Rapero>> generarAparellaments(ArrayList<Rapero> raperos, int posMiRapero){

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

    public String temaBatalla(){
        return temas.get((int) Math.floor(Math.random()* temas.size())).getNomTema();
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
    /*

    public void simular(){



    }



     */
}
