import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    public void mostrarDades (Competicio competicio) {
        System.out.println("Bienvenido/a a la competición: " + competicio.getNom());
        System.out.println("Comienza el " + competicio.getDataInici());
        System.out.println("Acaba el " + competicio.getDataFinal());
        System.out.println("Fases: " + competicio.getNumFases());
        System.out.println("Actualmente: " + competicio.getBatallaModel().getNumParticipants() + " " + "participantes");
        System.out.println(" ");
    }

    public int noComencada () {
        int opcio = 0;
        String entrada;
        boolean correct;
        char aux;
        Scanner sc = new Scanner(System.in);
        System.out.println("La competición no ha empezado todavía. ¿Qué quieres hacer?");
        System.out.println(" ");
        System.out.println("1. Registrarte");
        System.out.println("2. Salir");
        System.out.println(" ");
        System.out.print("Escoge una opción: ");
        do{
            correct = true;
            entrada = sc.nextLine();
            try {
                opcio = Integer.parseInt(entrada);
            }catch (NumberFormatException e){
                correct = false;
            }
            if (opcio != 1 && opcio != 2){
                System.out.println("Introduce una opcion correcta.");
                correct = false;
            }

        }while (!correct);

        return opcio;
    }

    public int comencada () {
        int opcio = 0;
        String entrada;
        boolean correct;
        Scanner sc = new Scanner(System.in);
        System.out.println("Competicion empezada. ¿Qué quieres hacer?");
        System.out.println(" ");
        System.out.println("1. Login");
        System.out.println("2. Salir");
        System.out.println(" ");
        System.out.println("Escoge una opcion: ");
        do{
            correct = true;
            entrada = sc.nextLine();
            try {
                opcio = Integer.parseInt(entrada);
            }catch (NumberFormatException e){

                correct = false;
            }
            if (opcio != 1 && opcio != 2){
                System.out.println("Introduce una opcion correcta.");
                correct = false;
            }

        }while (!correct);

        return opcio;
    }
    public void acabada (){
        System.out.println("ganador");
    }

    public String login () {
        Scanner sc = new Scanner(System.in);
        String entrada;
        System.out.print("Entra tu nombre artistico: ");
        return sc.nextLine();
    }

    public Rapero entradaInformacio (Competicio competicio) {
        Rapero rapero = new Rapero();
        boolean correct;

        String valor;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------");
        System.out.println("Por favor, introduzca su información personal:");

        System.out.println("- Nombre completo: ");
        rapero.setNomComplet(sc.nextLine());

        do {
            correct = true;
            System.out.println("- Nombre artístico: ");
            valor = sc.nextLine();
            for (Rapero o: competicio.getBatallaModel().getRaperos()) {
                if (o.getNomArtistic().equals(valor)){
                    System.out.println("Este nickname ya exsiste!!");
                    correct = false;
                    break;
                }
            }
        }while (!correct);
        rapero.setNomArtistic(valor);

        do {
            correct = true;
            System.out.println("- Fecha de nacimiento (yyyy/MM/dd): ");
            valor = sc.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                sdf.setLenient(false);
                Date data = sdf.parse(valor);
            } catch (ParseException e) {
                System.out.println("Introduce una fecha valida!");
                correct = false;
            }
        }while(!correct);
        rapero.setDataNaixement(valor);


        do {
            correct = false;
            System.out.println("- Pais (en inglés): ");
            valor = sc.nextLine();
            for (String o : competicio.getLlistaPaisos()) {
                if (o.equals(valor)) {
                    correct = true;
                    break;
                }
            }
            if (!correct){
                System.out.println("Este pais no es valido, introduce otro");
            }
        }while(!correct);
        rapero.setPaisRapero(valor);

        do {
            correct = true;
            System.out.println("- Nivel: ");
            valor = sc.nextLine();
            if (Integer.parseInt(valor) != 1 && Integer.parseInt(valor) != 2){
                correct = false;
                System.out.println("El nivel ha de ser 1 o 2!");
            }
        }while (!correct);
        rapero.setNivell(Long.parseLong(valor));

        System.out.println("- Foto: ");
        rapero.setFoto(sc.nextLine());

        System.out.println(" ");
        System.out.println("Registro completo!");
        System.out.println(" ");
        System.out.println("----------------------------------------------------");

        return rapero;
    }

    public int mostraLobby (int numFase, Competicio competicio, int posMiRapero, int numBatalla, String tipusBatalla, String nomRival){
        int opcio = 0;
        String entrada;
        boolean correct;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Fase: " + numFase + " / " + competicio.getNumFases() + " | Puntuación: " + competicio.getBatallaModel().getRaperos().get(posMiRapero).getPuntuacio() + " | Batalla " + numBatalla + " / 2: " + tipusBatalla + " | Rival: " + nomRival);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println(" ");
        System.out.println("1. Empezar la batalla");
        System.out.println("2. Mostrar ranquing");
        System.out.println("3. Crear perfil");
        System.out.println("4. Salir de la competición");
        System.out.println(" ");
        System.out.print("Escoge una opción: ");
        do{
            correct = true;
            entrada = sc.nextLine();
            try {
                opcio = Integer.parseInt(entrada);
            }catch (NumberFormatException e){

                correct = false;
            }
            if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4){
                System.out.println("Introduce una opcion correcta.");
                correct = false;
            }

        }while (!correct);

        return opcio;
    }
    public int faseFinal (int maxFases, float puntuacion) {
        int opcio = 0;
        String entrada;
        boolean correct;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Fase: " + maxFases + " / " + maxFases + " | Puntuación: " + puntuacion );
        System.out.println("----------------------------------------------------------------------------------------");

        System.out.println(" ");
        System.out.println("1. Empezar la batalla (desactivado)");
        System.out.println("2. Mostrar ranquing");
        System.out.println("3. Crear perfil");
        System.out.println("4. Salir de la competición");
        System.out.println(" ");
        System.out.print("Escoge una opción: ");
        do{
            correct = true;
            entrada = sc.nextLine();
            try {
                opcio = Integer.parseInt(entrada);
            }catch (NumberFormatException e){

                correct = false;
            }
            if (opcio != 2 && opcio != 3 && opcio != 4){
                System.out.println("Introduce una opcion correcta.");
                correct = false;
            }else if (opcio == 1){
                System.out.println("La competición ha acabado. ¡No puedes competir con nadie más!");
            }

        }while (!correct);

        return opcio;
    }
    public ArrayList<ArrayList<String>> batalla(String tema, Batalla batalla, int quienEmpieza, int numAparellament, String nomRival){
        Long nivelRival;
        ArrayList<ArrayList<String>> estrofas = new ArrayList<>();
        ArrayList<String> estrofaRapero = null;
        int numBarra = 0;
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Tema: " + tema + "\n");
        System.out.println("Lanzamos la moneda al aire y...");
        if (batalla.getAparellaments().get(numAparellament).get(quienEmpieza).getNomArtistic().equals(nomRival)){
            nivelRival = batalla.getAparellaments().get(numAparellament).get(quienEmpieza).getNivell();
            System.out.println(nomRival + "es tu turno. Se lo damos en 3, 2, 1...\n");
            System.out.println(nomRival + ":\n");
            turnoRival(tema, batalla, nivelRival, numBarra);
            numBarra++;
            System.out.println("\nVeo que tenemos nivel\nEs tu turno!");
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");
                estrofaRapero = new ArrayList<>();
                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
            System.out.println("\nMuy bien! " + nomRival + ", vas de nuevo!\n");
            System.out.println(nomRival + ":\n");
            turnoRival(tema, batalla, nivelRival, numBarra);
            System.out.println("\n\nVuelve a tocarte!");
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");
                estrofaRapero = new ArrayList<>();
                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
        }else{
            if (quienEmpieza == 0){
                nivelRival = batalla.getAparellaments().get(numAparellament).get(1).getNivell();
            }else {
                nivelRival = batalla.getAparellaments().get(numAparellament).get(0).getNivell();
            }
            System.out.println("Empiezas tu! Se lo damos en 3, 2, 1...\n\n");
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");
                estrofaRapero = new ArrayList<>();
                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
            System.out.println(nomRival + "es tu turno!\n");
            turnoRival(tema, batalla, nivelRival, numBarra);
            numBarra++;
            System.out.println("\nVeo que tenemos nivel\nEs tu turno!");
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");
                estrofaRapero = new ArrayList<>();
                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
            System.out.println("\nMuy bien! " + nomRival + ", vas de nuevo!\n");
            System.out.println(nomRival + ":\n");
            turnoRival(tema, batalla, nivelRival, numBarra);
        }
        return estrofas;
    }
    public String tuTurno () {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public void turnoRival (String tema, Batalla batalla, Long nivelRival, int numBarra){
        for (Tema a : batalla.getTemas()) {
            if (a.getNomTema().equals(tema)){
                System.out.println(a.getEstrofaPerNivell(nivelRival, numBarra));
            }
        }
    }

    public String mostrarRanking (int posMiRapero, ArrayList<Rapero> ranking) {
        String toString = "-------------------------------\n" +
                          "  Pos.   |   Name   |   Score  \n" +
                          "-------------------------------\n";
        for (Rapero o : ranking){
            toString = toString.concat("\n" + ranking.indexOf(o) + "  " + o.getNomArtistic() + " - " + o.getPuntuacio());
            if (ranking.indexOf(o) == posMiRapero){
                toString = toString.concat(" <-- Tu");
            }
        }
        return toString;
    }
}
