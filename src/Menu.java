import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/***
 *      Esta clase es la interfaz gráfica del programa, a partir de la cual mostraremos por pantalla la información
 *      necesária y se guardaran los datos introducidos por el usuario mediante el teclado.
 */


public class Menu {

    private static DecimalFormat df = new DecimalFormat("#.####");
    private static Scanner sc = new Scanner(System.in);

    /* DATOS INICIALES */

    /**
     *      Este método muestra por pantalla los datos iniciales de la competición.
     *
     *      @param competicio (Competicio) se le pasa la competición creada por el controller.
     * */

    public void mostrarDades (Competicio competicio) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Bienvenido/a a la competición: " + competicio.getNom());
        System.out.println("Comienza el " + sdf.format(competicio.getDataInici()));
        System.out.println("Acaba el " + sdf.format(competicio.getDataFinal()));
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
        System.out.println("----------------------------------------------------");
        System.out.println("Por favor, introduzca su información personal:");

        System.out.print("- Nombre completo: ");
        rapero.setNomComplet(sc.nextLine());

        do {
            correct = true;
            System.out.print("- Nombre artístico: ");
            valor = sc.nextLine();
            for (Rapero o: competicio.getFase(0).getRaperos()) {
                if (o.getNickname().equals(valor)){
                    System.out.println("Este nickname ya exsiste!!");
                    correct = false;
                    break;
                }
            }
        }while (!correct);
        rapero.setNomArtistic(valor);

        do {
            correct = true;
            System.out.print("- Fecha de nacimiento (yyyy/MM/dd): ");
            valor = sc.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                sdf.setLenient(false);
                sdf.parse(valor);
            } catch (ParseException e) {
                System.out.println("Introduce una fecha valida!");
                correct = false;
            }
        }while(!correct);
        rapero.setDataNaixement(valor);


        do {
            correct = false;
            System.out.print("- Pais (en inglés): ");
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
            System.out.print("- Nivel: ");
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

        System.out.print("- Foto: ");
        rapero.setFoto(sc.nextLine());

        System.out.println("\nRegistro completo!\n");
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
        System.out.print("Entra tu nombre artistico: ");
        return sc.nextLine();
    }

    /**
     *      Este método muestra el menú de la batalla y fase actuales, junto con su información y diferentes oipciones
     *      a seleccionar.
     *
     *      Todos los parámetros son pasados automáticamente por el programa.
     *      @param maxFases (int) equivalente al numero máximo de fases de la competición.
     *      @param nomRival (String) equivalente al nombre del oponente en la batalla siguiente.
     *      @param numBatalla (int) si es la primera o segunda batalla de la fase (0 u 1).
     *      @param puntuacion (double) puntuación del rapero hasta el momento.
     *      @param tipusBatalla (String) puede ser "BatallaAcapela", "BatallaSangre" o "BatallaEscrita".
     *      @param numFase (int) numero actual de fase (0, 1 o 2).
     *
     *      @return int opción seleccionada por el usuario (solo puede ser 1, 2, 3 o 4).
     * */

    public int mostraLobby (int numFase, int maxFases, double puntuacion, int numBatalla, String tipusBatalla, String nomRival){

        int opcio = 0;
        String entrada;
        boolean correct;
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Fase: " + numFase + " / " + maxFases + " | Puntuación: " + df.format(puntuacion) + " | Batalla " + numBatalla + " / 2: " + tipusBatalla + " | Rival: " + nomRival);
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

    /**
     *      Este método ejecuta la batalla, mostrando por pantalla los diferentes turnos tanto del rival como del
     *      rapero y mostrando por pantalla las estrofas del rival.
     *
     *      Todos los parámetros son pasados automáticamente por el programa.
     *      @param nomRival (String) equivalente al nombre del oponente en la batalla siguiente.
     *      @param batalla (Batalla) equivalente a la batalla actual.
     *      @param fase (Fase) equivalente a la fase actual.
     *      @param quienEmpieza (int) numero aleatorio para determinar cual de los 2 participantes interviene antes.
     *
     *      @return ArrayList con 2 ArrayLists que representan las estrofas esritas por el rapero (1 por intervención),
     *         cada uno contiene 4 Strings que representan los 4 versos de la estrofa.
     * */

    public ArrayList<ArrayList<String>> batalla (Fase fase, Batalla batalla, int quienEmpieza, String nomRival){
        Long nivelRival;
        ArrayList<ArrayList<String>> estrofas = new ArrayList<>();
        ArrayList<String> estrofaRapero;
        int numBarra = 0;
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Tema: " + batalla.getTema() + "\n");
        System.out.println("Lanzamos la moneda al aire y...");
        if (batalla.getAparellaments().get(batalla.getNumAparellament()).get(quienEmpieza).getNickname().equals(nomRival)){
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
            System.out.println(nomRival + " es tu turno!\n");
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

    /**
     *      Este método devuelve la línea introducida por el usuario por teclado.
     *
     *      @return (String) verso del usuario.
     * */

    public String tuTurno () {
        return sc.nextLine();
    }

    /**
     *      Este método muestra por pantalla la estrofa correspondiente al tema préviamente escogido y de acuerdo con
     *      el nivel del rival.
     *
     *      Todos los parámetros son pasados automáticamente por el programa.
     *      @param fase (String) equivalente al nombre del oponente en la batalla siguiente.
     *      @param batalla (Batalla) equivalente a la batalla actual.
     *      @param nivelRival (Long) entre 1 y 2 representa el nivel del contrincario utilizado para encontrar su
     *                        estrofa correspondiente.
     *      @param numBarra (int) número que determina si es la primera o la segunda intervención del rival en esta
     *                      batalla.
     * */

    public void turnoRival (Fase fase, Batalla batalla, Long nivelRival, int numBarra){
        for (Tema a : fase.getTemas()) {
            if (a.getNomTema().equals(batalla.getTema())){
                System.out.println(a.getEstrofaPerNivell(nivelRival, numBarra));
            }
        }
    }

    /* LOBBY: OPCION 2 */

    /**
     *      Este método muestra por pantalla en forma de lista el ranquing actual de la competición, el cual está
     *      ordenado por puntuación de forma descendiente. También indica cual es el rapero con el que se ha hecho el
     *      login, marcándolo con una flecha.
     *
     *      Todos los parámetros son pasados automáticamente por el programa.
     *      @param posMiRapero (int) representa la posición del rapero en el ranking.
     *      @param ranking (ArrayList) ArrayList generada en competicio con los raperos ya ordenados.
     *
     * */

    public void mostrarRanking (int posMiRapero, ArrayList<Rapero> ranking) {
        System.out.println("--------------------------------------------");
        System.out.printf("%-10s %-20s %-10s\n", "Pos.", "Name", "Score");
        System.out.println("--------------------------------------------\n");

        for (Rapero o : ranking){
            if (ranking.indexOf(o) == posMiRapero){
                System.out.printf("%-10s %-20s %-2s %-10s\n", (ranking.indexOf(o) + 1), o.getNickname(), df.format(o.getPuntuacio()), " <-- Tu");
            }else{
                System.out.printf("%-10s %-20s %-10s\n", (ranking.indexOf(o) + 1), o.getNickname(), df.format(o.getPuntuacio()));
            }
        }
    }

    /* LOBBY OPCION 3 */

    /**
     *      Metodo para la busqueda del Rapero a partir del nombre artistico introducido por teclado.
     *
     *      @param fase Fase actual de la competicion.
     *      @return Rapero correspondiente al nombre introducido.
     * */

    public Rapero showProfile(Fase fase){
        boolean ok = false;
        int i;
        Rapero r = null;
        do{
            System.out.print("\nIntroduce el nombre del rapero: ");
            String name = sc.nextLine();
            for (i = 0; i < fase.getRaperos().size() && !ok; i++) {
                ok = fase.getRaperos().get(i).getNickname().equals(name);
            }
            if (!ok){
                System.out.println("\nEl nombre introducido no existe");
            }else{
               r = fase.getRaperos().get(i - 1);
            }
        }while (!ok);
        System.out.println("\n\nCogiendo información del país de origen (" + r.getPaisString() + ")...");
        System.out.println("\nGenerando fichero HTML.html...\n");
        return r;
    }

    /* MAX FASES HECHAS: */

    /**
     *      Esta función se ejecuta una vez se ha llegado al máximo de batallas en el máximo de fases. Se muestra un
     *      menú en pantalla con diferentes opciones y se recoge la opción escogida por el usuario.
     *
     *      Todos los parámetros son pasados automáticamente por el programa.
     *      @param ganador (String) nombre artístico del rapero con mayor puntuación.
     *      @param puntuacion (int) puntuación del rapero ganador.
     *
     *      @return int devuelve la opción seleccionada (solo puede ser 2, 3 o 4).
     * */

    public int faseFinal (String ganador, double puntuacion) {
        int opcio = 0;
        String entrada;
        boolean correct;
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Ganador: " + ganador + " | Puntuación: " + df.format(puntuacion));
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
            if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4){
                System.out.println("Introduce una opcion correcta.");
                correct = false;
            }else if (opcio == 1){
                System.out.println("La competición ha acabado. ¡No puedes competir con nadie más!");
                correct = false;
            }

        }while (!correct);

        return opcio;
    }

    /* COMPETICION ACABADA */

    /**
     *      En el caso que se haya llegado al final de la competición o la fecha actual determine que está ya ha acabado
     *      se ejecutará este método, el cual muestra por pantalla una medalla y el nombre y puntuación del ganador de
     *      la competición.
     *
     *      El parámetro es pasado automáticamente por el programa.
     *      @param info (String[]) array de String con la informacion necesaria a mostrar porpantalla.
     * */

    public void acabada (String[] info){
        System.out.println("El ganador de esta edicion es...\n");
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

        System.out.println(info[0] + " con " + df.format(Double.valueOf(info[1])) + " puntos!\n");
        System.out.println("Gracias a todos por participar.");
    }
}
