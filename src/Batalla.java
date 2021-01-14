import java.util.ArrayList;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private String tema;
    private String nomRival;
    private String tipusBatalla;

    private int numAparellament;
    private int numBatalla;

    private ArrayList<ArrayList<Rapero>> aparellaments;
    private ArrayList<ArrayList<String>> estrofas;

    /* CONSTRUCTORES */

    /**
     *      Este constructor creara una ArrayList donde se guardaran los emparejamientos para las batallas.
     *
     *      @param raperos (ArrayList Project.AppNegocio.Rapero) Arraylist con todos los raperos.
     *      @param posMiRapero (int) Posicion del rapero usuario.
     *
     * */

    public Batalla (ArrayList<Rapero> raperos, int posMiRapero) {
        aparellaments = new ArrayList<>();
        generarAparellaments(raperos, posMiRapero);
    }

    /* SETTERS */

    /**
     *      Metodo para generar los emparajamientos de las batallas.
     *
     *      @param raperos (ArrayList Project.AppNegocio.Rapero) Arraylist con todos los raperos.
     *      @param posMiRapero (int) Posicion del rapero usuario.
     *
     * */

    public void generarAparellaments(ArrayList<Rapero> raperos, int posMiRapero){
        ArrayList<Rapero> aux = new ArrayList<>(raperos);
        int rapero_out;
        if (posMiRapero == -1){
            if ((aux.size() % 2) != 0){
                rapero_out = (int) Math.floor(Math.random()*aux.size());
                aux.remove(rapero_out);
            }

        }else{
            if ((aux.size() % 2) != 0){
                do {
                    rapero_out = (int) Math.floor(Math.random()*aux.size());
                }while(rapero_out == posMiRapero);
                aux.remove(rapero_out);
            }
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
        if (posMiRapero != -1){
            String nomArtistic = raperos.get(posMiRapero).getNickname();
            setNumAparellament(nomArtistic);
            setNomRival(nomArtistic);
        }

    }

    /**
     *      Metodo para guardar el tipo de la batalla.
     *
     *      @param tipusBatalla (String) String con el tipo de batalla.
     *
     * */

    public void setTipusBatalla(String tipusBatalla) {
        this.tipusBatalla = tipusBatalla;
    }

    /**
     *      Metodo para guardar las Estrofas de la batalla.
     *
     *      @param estrofas (ArayList ArrayList String) String con las estrofas de la batalla.
     *
     * */

    public void setEstrofas(ArrayList<ArrayList<String>> estrofas) {
        this.estrofas = estrofas;
    }

    /**
     *      Metodo para guardar el tema de la batalla.
     *
     *      @param tema (String) String con el tema de la batalla.
     *
     * */

    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     *      Metodo para guardar el indice de la lista del emaparejamiento del usuario.
     *
     *      @param nomArtistic (String) String con el nombre artistico del usuario para encontrar su emparejamiento.
     *
     * */

    public void setNumAparellament(String nomArtistic) {
        for (ArrayList<Rapero> parella : aparellaments) {
            if (parella.get(0).getNickname().equals(nomArtistic) || parella.get(1).getNickname().equals(nomArtistic)){
                numAparellament = aparellaments.indexOf(parella);
            }
        }
    }

    /**
     *      Metodo para guardar el nombre del rival de usuario en la batalla.
     *
     *      @param nomArtistic (String) String con el nombre artistico del rival del usuario.
     *
     * */

    public void setNomRival(String nomArtistic) {
        for (ArrayList<Rapero> parella : aparellaments) {
            if (parella.get(0).getNickname().equals(nomArtistic)){
                nomRival = parella.get(1).getNickname();
            }else if (parella.get(1).getNickname().equals(nomArtistic)){
                nomRival = parella.get(0).getNickname();
            }
        }
    }

    /**
     *      Metodo para guardar el numero de la batalla.
     *
     *      @param numBatalla (String) String con el numero perteneciente a la batalla.
     *
     * */

    public void setNumBatalla(int numBatalla) {
        this.numBatalla = numBatalla;
    }

    /* GETTERS */

    /**
     *      Metodo para obtener la ArayList de los emparejamientos de la batalla.
     *
     *      @return (ArrayList ArrayList String) ArrayList con los emparejamientos de la batalla.
     *
     * */

    public ArrayList<ArrayList<Rapero>> getAparellaments() {
        return aparellaments;
    }

    /**
     *      Metodo para obtener el nombre del rival de usuario en la batalla.
     *
     *      @return (String) String con el nombre artistico del rival del usuario.
     *
     * */

    public String getNomRival() {
        return nomRival;
    }

    /**
     *      Metodo para obtener el numero de la batalla.
     *
     *      @return (int) Numero de la batalla.
     *
     * */

    public int getNumBatalla() {
        return numBatalla;
    }

    /**
     *      Metodo para obtener el tipo de la batalla.
     *
     *      @return (String) String con el tipo de la batalla.
     *
     * */

    public String getTipusBatalla() {
        return tipusBatalla;
    }

    /**
     *      Metodo para obtener el tema de la batalla.
     *
     *      @return (String) String con el tema de la batalla.
     *
     * */

    public String getTema() {
        return tema;
    }

    /**
     *      Metodo para obtener el numero de emparejamientos de la batalla.
     *
     *      @return (int) Entero con el numero de emparejamientos de la batalla.
     *
     * */

    public int getNumAparellament() {
        return numAparellament;
    }



    /* MÉTODOS */

    public double calculoPuntuacion(){
        return 0;
    }


    public double puntuacionSimulaciones (float R){
        return 0;
    }

    /**
     *      Metodo para obtener la puntuacion segun el numero de rimas en las estrofas.
     *
     *      @return (Integrer[]) Enteros con el numero de rimas encontradas por estrofa.
     *
     * */

    public Integer[] calcularRimes(){
        Integer[] R = {0, 0};
        ArrayList<ArrayList<Integer>> rima = new ArrayList<>();
        for (ArrayList<String> estrofa: estrofas) {
            ArrayList<String> ultimasLetras = new ArrayList<>();
            for (String o: estrofa) {
                 if (o.length() >= 2 && isLetter(o)){
                     ultimasLetras.add(o.substring(o.length() - 2));
                 }else{
                     ultimasLetras.add("0");
                 }
            }
            rima.add(numRimas(ultimasLetras));
        }
        R[0] = rima.get(0).get(0) + rima.get(0).get(1) + rima.get(0).get(2) + rima.get(0).get(3);
        R[1] = rima.get(1).get(0) + rima.get(1).get(1) + rima.get(1).get(2) + rima.get(1).get(3);

        return R;
    }

    /**
     *      Metodo para obtener el numero de rimas de la estrofa.
     *
     *      @return (ArrayList Integrer) array de 2 posiciones con el numero de rimas en las estrofas.
     *
     * */

    public ArrayList<Integer> numRimas(ArrayList<String> ultimasLetras){
        ArrayList<Integer> sumaNumRimes = new ArrayList<>();

        Tema.Estrofa.extractRimes(sumaNumRimes, ultimasLetras);
        return sumaNumRimes;
    }

    /**
     *      Método para comprovar que los últimos carácteres del verso introducido por el usuario son letras del
     *      abecedario y no otros símbolos.
     *
     *      @param s (String) los últimos carácteres.
     *
     *      @return (boolean) true si es una letra del abecedario.
     *                        false si no lo es.
     * */

    public boolean isLetter (String s){
        boolean[] isLetter = {false, false};
        for (int i = 0; i < 2; i++){
            char letraFinal = s.charAt(i);
            if (letraFinal >= 65 && letraFinal <= 90){
                isLetter[i] = true;
            }else if (letraFinal >= 97 && letraFinal <= 122){
                isLetter[i] = true;
            }
        }
        return isLetter[0] && isLetter[1];
    }

    /**
     *      Metodo que simulara la batalla.
     *
     *      @param temas (ArrayList Project.AppNegocio.Tema) Temas de la batalla.
     *      @param batallaTipusBatalla (Project.AppNegocio.Batalla) Tipo de batalla (Sangre,Acapella,Escrita).
     *      @param nomTema (String) Project.AppNegocio.Tema de la batalla.
     *      @param numeroAparellament (int) Numero de emparejamientos.
     *      @param raperos (ArrayList Project.AppNegocio.Rapero) Raperos que participan en la batalla.
     *
     *
     * */

    public void simularBatalla (ArrayList<Tema> temas,Batalla batallaTipusBatalla, String nomTema, int numeroAparellament, ArrayList<Rapero> raperos) {
        if (numAparellament != numeroAparellament){
            int numRapero1 = -1;
            int numRapero2 = -1;
            for (Rapero o: raperos) {
                if (o.getNickname().equals(aparellaments.get(numeroAparellament).get(0).getNickname())){
                    numRapero1 = raperos.indexOf(o);
                }else if (o.getNickname().equals(aparellaments.get(numeroAparellament).get(1).getNickname())){
                    numRapero2 = raperos.indexOf(o);
                }
                if (numRapero1 != -1 && numRapero2 != -1){
                    break;
                }
            }
            for (Tema tema : temas) {
                if (tema.getNomTema().equals(nomTema)) {
                    //Rapero 1
                    raperos.get(numRapero1).setPuntuacio(batallaTipusBatalla.puntuacionSimulaciones(tema.getEstrofa(raperos.get(numRapero1).getNivell()).getPuntuacion().get(0)));
                    raperos.get(numRapero1).setPuntuacio(batallaTipusBatalla.puntuacionSimulaciones(tema.getEstrofa(raperos.get(numRapero1).getNivell()).getPuntuacion().get(1)));
                    //Rapero 2
                    raperos.get(numRapero2).setPuntuacio(batallaTipusBatalla.puntuacionSimulaciones(tema.getEstrofa(raperos.get(numRapero1).getNivell()).getPuntuacion().get(0)));
                    raperos.get(numRapero2).setPuntuacio(batallaTipusBatalla.puntuacionSimulaciones(tema.getEstrofa(raperos.get(numRapero1).getNivell()).getPuntuacion().get(1)));
                    break;
                }
            }
        }
    }


}
