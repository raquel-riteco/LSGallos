import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/***
 *      Esta clase es la interfaz gráfica del programa, a partir de la cual mostraremos por pantalla la información
 *      necesária y se guardaran los datos introducidos por el usuario mediante el teclado.
 */


public class Menu {

    /* DATOS INICIALES */

    /**
     *      Este método muestra por pantalla los datos iniciales de la competición.
     *
     *      @param competicio (Competicio) se le pasa la competición creada por el controller.
     * */

    public void mostrarDades (Competicio competicio) {
        System.out.println("Bienvenido/a a la competición: " + competicio.getNom());
        System.out.println("Comienza el " + competicio.getDataInici());
        System.out.println("Acaba el " + competicio.getDataFinal());
        System.out.println("Fases: " + competicio.getNumFases());
        System.out.println("Actualmente: " + competicio.getFase(0).getNumParticipants() + " " + "participantes");
        System.out.println(" ");
    }

    /* COMPETICION NO EMPEZADA */

    /**
     *      En caso deque la competición no haya empezado se ejecutará este método, donde el usuario seleccionará
     *      una de las opciones del menú introduciendo su selección por el teclado. Se comprueba que sea correcta.
     *
     *      @return int equivalente a la opción seleccionada. (Este return solo devolverá 1 o 2).
     * */

    public int noComencada () {
        int opcio = 0;
        String entrada;
        boolean correct;
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

    /**
     *      Este método se utiliza para guardar los datos que el usuario introduce para registrar un nuevo rapero.
     *      Después de preguntar cada dato, este se comprueba antes de guardarlo, y en caso de no ser correcto se
     *      volverá a pedir.
     *
     *      @param competicio (Competicio) se le pasa la competición creada por el controller.
     *
     *      @return Rapero equivalente al rapero creado a partir de los datos introducidos.
     * */

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
            for (Rapero o: competicio.getFase(0).getRaperos()) {
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
            try {
                if (Integer.parseInt(valor) != 1 && Integer.parseInt(valor) != 2) {
                    correct = false;
                    System.out.println("El nivel ha de ser 1 o 2!");
                }
            }catch(NumberFormatException e){
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

    /* COMPETICION EMPEZADA */

    /**
     *      En el caso de que la competición ya haya empezado se ejecutará este método, el cual muestra un menú y
     *      comprueva que la opción introducida por el usuario sea correcta.
     *
     *      @return int equivalente a la opción escogida (solo devolverá 1 o 2).
     * */

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
        System.out.print("Escoge una opcion: ");
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

    /**
     *      Este método pregunta al usuario por su nombre artístico.
     *
     *      @return (String) nombre introducido por el usuario.
     * */

    public String login () {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entra tu nombre artistico: ");
        return sc.nextLine();
    }

    /**
     *      Este método muestra el menú de la batalla y fase actuales, junto con su información y diferentes oipciones
     *      a seleccionar.
     *
     *      Todos loa parámetros son pasados automáticamente por el programa.
     *      @param maxFases (int) equivalente al numero máximo de fases de la competición.
     *      @param nomRival (String) equivalente al nombre del oponente en la batalla siguiente.
     *      @param numBatalla (int) si es la primera o segunda batalla de la fase (0 u 1).
     *      @param puntuacion (double) puntuación del rapero hasta el momento.
     *      @param tipusBatalla (String) puede ser "BatallaAcapela", "BatallaSangre" o "BatallaEscrita".
     *      @param numFase (int) numero actual de fase (0, 1 o 2).
     * */

    public int mostraLobby (int numFase, int maxFases, double puntuacion, int numBatalla, String tipusBatalla, String nomRival){

        int opcio = 0;
        String entrada;
        boolean correct;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Fase: " + numFase + " / " + maxFases + " | Puntuación: " + puntuacion + " | Batalla " + numBatalla + " / 2: " + tipusBatalla + " | Rival: " + nomRival);
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

    /* LOBBY: OPCION 1 */

    public ArrayList<ArrayList<String>> batalla (Fase fase, Batalla batalla, int quienEmpieza, String nomRival){
        Long nivelRival;
        ArrayList<ArrayList<String>> estrofas = new ArrayList<>();
        ArrayList<String> estrofaRapero = null;
        int numBarra = 0;
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Tema: " + batalla.getTema() + "\n");
        System.out.println("Lanzamos la moneda al aire y...");
        if (batalla.getAparellaments().get(batalla.getNumAparellament()).get(quienEmpieza).getNomArtistic().equals(nomRival)){
            nivelRival = batalla.getAparellaments().get(batalla.getNumAparellament()).get(quienEmpieza).getNivell();
            System.out.println(nomRival + " es tu turno. Se lo damos en 3, 2, 1...\n");
            System.out.println(nomRival + ":\n");
            turnoRival(fase, batalla, nivelRival, numBarra);
            numBarra++;
            System.out.println("\nVeo que tenemos nivel\nEs tu turno!");
            estrofaRapero = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");
                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
            System.out.println("\nMuy bien! " + nomRival + ", vas de nuevo!\n");
            System.out.println(nomRival + ":\n");
            turnoRival(fase, batalla, nivelRival, numBarra);
            System.out.println("\n\nVuelve a tocarte!");
            estrofaRapero = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");
                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
        }else{
            if (quienEmpieza == 0){
                nivelRival = batalla.getAparellaments().get(batalla.getNumAparellament()).get(1).getNivell();
            }else {
                nivelRival = batalla.getAparellaments().get(batalla.getNumAparellament()).get(0).getNivell();
            }
            System.out.println("Empiezas tu! Se lo damos en 3, 2, 1...\n\n");
            estrofaRapero = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");

                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
            System.out.println(nomRival + "es tu turno!\n");
            turnoRival(fase, batalla, nivelRival, numBarra);
            numBarra++;
            System.out.println("\nVeo que tenemos nivel\nEs tu turno!");
            estrofaRapero = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.println("Introduce tu verso: ");
                estrofaRapero.add(tuTurno());
            }
            estrofas.add(estrofaRapero);
            System.out.println("\nMuy bien! " + nomRival + ", vas de nuevo!\n");
            System.out.println(nomRival + ":\n");
            turnoRival(fase, batalla, nivelRival, numBarra);
        }
        return estrofas;
    }
    public String tuTurno () {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void turnoRival (Fase fase, Batalla batalla, Long nivelRival, int numBarra){
        for (Tema a : fase.getTemas()) {
            if (a.getNomTema().equals(batalla.getTema())){
                System.out.println(a.getEstrofaPerNivell(nivelRival, numBarra));
            }
        }
    }

    /* LOBBY: OPCION 2 */

    public void mostrarRanking (int posMiRapero, ArrayList<Rapero> ranking) {
        System.out.println("--------------------------------------------\n" +
                           "  Pos.   |       Name      |   Score  \n" +
                           "--------------------------------------------\n");
        for (Rapero o : ranking){
            System.out.print(ranking.indexOf(o) + "  " + o.getNomArtistic() + " - " + o.getPuntuacio());
            if (ranking.indexOf(o) == posMiRapero){
                System.out.println(" <-- Tu");
            }else{
                System.out.println("");
            }
        }
    }

    /* MAX FASES HECHAS: */

    public int faseFinal (String ganador, float puntuacion) {
        int opcio = 0;
        String entrada;
        boolean correct;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Ganador: " + ganador + " | Puntuación: " + puntuacion );
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

    /* COMPETICION ACABADA */

    public void acabada (Rapero rapero){
        System.out.println("El ganador de esta edicion es...\n\n");
        System.out.println("----------------------------------\n" +
                           "----------------------------------\n" +
                           "----                          ----\n" +
                           "  ----                      ----  \n" +
                           "    ----                  ----    \n" +
                           "      ----             -----      \n" +
                           "        ----          ----        \n" +
                           "          ----      ----          \n" +
                           "            ----  ----            \n" +
                           "            ----------            \n" +
                           "           ------------           \n" +
                           "           ------------           \n" +
                           "            ----------            \n" +
                           "               ----               \n" );

        System.out.println(rapero.getNomArtistic() + " con " + rapero.getPuntuacio() + "puntos!\n\n");
        System.out.println("Gracias a todos por participar.");
    }
}
