package proyecto.aplicacion;

import edu.salleurl.profile.ProfileFactory;
import proyecto.datos.Fitxers;
import proyecto.presentacion.*;

import java.util.Date;

/**
 *      Esta es la clase principal del programa, a partir de esta configuraremos el resto de clases y ejecutaremos
 *      los métodos necesarios para el correcto funcionamiento del programa.
 *
 * */

public class Controller {

    private final Menu menu;
    private final Fitxers fitxers;
    private final String nomCompeticio;

    private Competicio competicio;

    /* CONSTRUCTORES*/

    /**
     *      Crea un objeto de la clase Project.AppNegocio.CompeticioController.
     *      Este constructor es esencial, ya que a partir del objeto creado ejecutaremos el único método necesario
     *      para la ejecución del programa.
     *
     *      @param nomBatalles (String) el "path" del fichero Batalles (del tipo Json) utilizado para la lectura del fichero.
     *      @param nomCompeticio (String) el "path" del fichero Project.AppNegocio.Competicio (del tipo Json) utilizado para la lectura del
     *                           fichero. Este parámetro se guarda como atributo del objeto creado.
     *      En este constructor se crean los objetos del tipo Project.Presentacion.Menu, Fitcher y Project.AppNegocio.Competicio, los cuales son atributos de
     *      esta clase (Project.AppNegocio.CompeticioController). También se ejecutan los métodos setCompeticio() y llegirBatalles().
     * */

    public Controller(String nomCompeticio, String nomBatalles) {
        this.nomCompeticio = nomCompeticio;
        menu = new Menu();
        fitxers = new Fitxers();
        competicio = new Competicio();
        setCompeticio();
        fitxers.llegirBatalles(nomBatalles, competicio);
    }

    /* SETTERS */

    /**
     *      Lectura del fichero Project.AppNegocio.Competicio y guardado de la informacion en competicio, atributo de la clase
     *      Project.AppNegocio.CompeticioController.
     *      Ejecuta el método llegirCompeticio() de la clase Fitxer.
     * */

    public void setCompeticio() {
        competicio = fitxers.llegirCompeticio(nomCompeticio);
    }

    /* COMPROBACIONES */

    /**
     *      Comprobación de la fecha actual y comparación con las fechas de inicio y final de la competición.
     *      @return int -1 en el caso que la fecha sea anterior a la de inicio,
     *                   1 en el caso que la fecha sea posterior a la final,
     *                   0 en el caso que la fecha sea posterior a la de inicio y anterior a la final.
     *      Este return se utilizará para determinar que método del menú se utilizará.
     * */

    public int comprovarData(){
         Date dataActual = new Date();
         if (dataActual.before(competicio.getDataInici())){
             return -1;
         }else if (dataActual.after(competicio.getDataFinal())){
             return 1;
         }else{
             return 0;
         }
    }

    /**
     *      Comprueba si el nombre artístico introducido como login es correcto. Esto se realiza comparando este
     *      nombre con todos los nombres guardados en competición.
     *      @param nomArtistic (String) el nombre introducido por teclado y guardado a través del método login de la clase
     *                         menú.
     *      @return false en caso de que sí se haya encontrado
     *              true en caso de que no se haya encontrado.
     * */

    public boolean comprovarLogin (String nomArtistic) {
        boolean found = false;
        for (int i = 0; i < competicio.getFase(0).getRaperos().size() && !found; i++) {
            if (competicio.getFase(0).getRaperos().get(i).getNickname().equals(nomArtistic)){
                found = true;
            }
        }
        return !found;
    }

    /* MÉTODOS */

    /**
     *      Método principal a partir del cual se ejecutan todos los demás. Es el único método utilizado para iniciar
     *      el programa.
     *      Se ejecuta el método mostrarDades de la clase menú .
     *      Después se ejecuta el método comprovarData .
     *      Entonces tenemos 3 diferentes opciones:
     *          - Competición no empezada: ejecución del método noCompencada de menú.
     *              - En caso de seleccionar la primera opción se registrará un rapero nuevo.
     *              - Si no,se saldrá de la competición.
     *          - Competición empezada: ejecución del método comencada de menú.
     *              - En caso de seleccionar la primera opción se hará el login del rapero.
     *                Una vez comprovado se ejecutará el método mostrarMenu .
     *          - Competición acabada: ejecución del método acabada de menú.
     * */

    public void mostrarMenu (){
        int opcio;
        menu.mostrarDades(competicio);
        switch (comprovarData()){
            case -1:
                opcio = menu.noComencada();
                if (opcio == 1){
                    Rapero nouRapero;
                    nouRapero = menu.entradaInformacio(competicio);
                    competicio.getFase(0).setRapero(nouRapero);
                    fitxers.registrarRapero(nouRapero, nomCompeticio);
                    mostrarMenu();
                }else {
                    System.out.println("Ens veiem aviat!");
                }
                break;
            case 0:
                opcio = menu.comencada();
                if (opcio == 1){
                    String nomArtistic;
                    do {
                        nomArtistic = menu.login();
                        if (comprovarLogin(nomArtistic)){
                            System.out.println("Heeeey! Este rapero no esta registrado!");
                        }
                    }while(comprovarLogin(nomArtistic));
                    executar(nomArtistic);
                }else {
                    System.out.println("Ens veiem aviat!");
                }
                break;
            case 1:
                menu.acabada(competicio.getFase(competicio.getNumFases()).getRanking().get(0));
            break;
        }
    }
    /**
     *      Ejecución de la competición siguiendo el siguiente esquema:
     *          1. Por cada fase de a competición se ejecutrá el método actualitzarListaRaperos y el paso 2.
     *
     *          2.
     *              2.1 Comprovación de que el rapero con el que se ha hecho el login se ha clasificado.
     *              2.2 Ejecución del método formarBatallas() de la clase fase.
     *              2.3 Dependiendo de si el rapero ha clasificado a la siguiente fase o si se ha decidido salir de
     *                  la competición:
     *                  2.3.1 Sí ha clasificado -> buscamos la posición del rapero entre los raperos mediante el
     *                        método setPosMiRapero() de la clase fase.
     *                  2.3.2 Si no o si se ha decidido salir se simularán el resto de batallas.
     *              2.4 Por cada batalla de la fase actual se ejecutará el paso 2.5.
     *              2.5
     *                  2.5.1 Si el rapero ha clasificado se ejecutará el método mostraLobby de la clase menú, y
     *                        dependiendo de la opción seleccionada se ejecutará alguno de los siguientes pasos hasta
     *                        que se decida batallar o salir de la competición.
     *                        2.5.1.1 Opción 1 -> se decide quien empieza y se ejecuta el método batalla de la clase
     *                                menú, guardando las estrofas del rapero mediante el método setEstrofas() de la
     *                                clase batalla.
     *                                Posteriormente se calcula la puntución mediante el método setPuntuacionBatalla()
     *                                de la clase fase y se simular el resto de batallas mediante el método simulaciones
     *                                de la clase fase.
     *                                Después se ejecuta el paso 2.5.3.
     *                        2.5.1.2 Opción 2 -> se muestra el ranquing actual mediante el método mostrarRanking() de la
     *                                clase menú
     *                                Después se vuelve al paso 2.5.1.
     *                        2.5.1.3 Opción 3 -> aun no ha sido desarrollada por lo que se informará al usuario por
     *                                pantalla y se volverá al paso 2.5.1
     *                        2.5.1.4 Opción 4 -> salida de la competición, ejecución del paso 2.5.2.
     *                  2.5.2 Si el rapero no ha clasificado o se ha decidido salir se ejecuta directamente la opción 4
     *                        -> ejecución del método simulaciones de la clase fase
     *                        Después de ejecuta el paso.
     *             2.6 Actualización del ranking de la fase mediante el método setRanking() de la clase fase y se
     *                 descartan los raperos que no han clasificado mediante el método descartarRaperos también de
     *                 la clase fase.
     *
     *          3. Después de la ejecución de cada fase, si el rapero ha llegado hasta la fase final y
     *             no se ha decidido salir se ejecutará el paso 3.1, en caso contrario este se omitirá y se pasará
     *             directamente al paso 4.
     *             3.1 Mientras no se seleccione la opción 4 para salir de la competición se ejecutará el método
     *                 faseFinal() de la clase menú, y dependiendo de la opción seleccionada se ejecutará alguno de los
     *                 siguientes pasos.
     *                 3.1.1 Opción 2 -> mostrar el ranking de la fase final mediante el método mostrarRanking() de la
     *                       clase menú.
     *                       Después se vuelve al paso 3.1.
     *                 3.1.2 Opcion 3 -> aun no ha sido desarrollada por lo que se informará al usuario por
     *                       pantalla y después se vuelve al paso 3.1.
     *                 3.1.3 Opción 4 -> salida de la competición y ejecución del paso 4.
     *
     *          4. Ejecución del método acabada de la clase menu.
     *
     *      @param nomArtistic (String) se pasa directamente desde el método mostrarMenu()
     * */

    public void executar(String nomArtistic) {
        int opcioLobby = 0;
        boolean raperoClasificado = false;

        for (Fase fase : competicio.getFases()) {

            competicio.actualizarListaRaperos(fase);
            for (Rapero rapero : fase.getRaperos()) {
                if (rapero.getNickname().equals(nomArtistic)){
                    raperoClasificado = true;
                    break;
                }
            }
            fase.formarBatallas();
            if (raperoClasificado){
                System.out.println("\n\nMuy bien! Estas clasificado para la siguiente fase!\n\n");
                fase.setPosMiRapero(nomArtistic);
            }else{
                System.out.println("\n\nLo siento bro pero no te has clasificado para la siguiente fase, más suerte la próxima vez!\n\n");
                opcioLobby = 4;
            }

            for (Batalla batalla : fase.getBatallas()) {
                do{
                    opcioLobby = menu.mostraLobby(fase.getNumFase(), competicio.getNumFases(), fase.getRaperos().get(fase.getPosMiRapero()).getPuntuacio(), batalla.getNumBatalla(), batalla.getTipusBatalla(), batalla.getNomRival());
                    switch (opcioLobby){
                        case 1:
                            int quienEmpieza = (int) Math.floor(Math.random()*2);
                            batalla.setEstrofas(menu.batalla(fase, batalla, quienEmpieza, batalla.getNomRival()));
                            fase.setPuntuacionBatalla(fase.getBatallas().indexOf(batalla));
                            fase.simulaciones(fase.getBatallas().indexOf(batalla));
                            break;
                        case 2:
                            menu.mostrarRanking(fase.getPosMiRapero(), fase.getRanking());
                            System.out.println("\n\n");
                            break;
                        case 3:
                            Rapero rapero = menu.showProfile(fase);
                            CrearPerfil crearPerfil = new CrearPerfil(ProfileFactory.createProfile("src/proyecto/datos/html", rapero), rapero.getPaisString());
                            crearPerfil.buscarInfoPais();
                            crearPerfil.generarPerfil();
                            break;
                        case 4:
                            fase.simulaciones(fase.getBatallas().indexOf(batalla));
                            break;
                    }
                }while (opcioLobby == 2 || opcioLobby == 3);
            }
            fase.setRanking();
            fase.descartarRaperos(competicio.getNumFases());
        }
        if (opcioLobby != 4){
            int opcioFaseFinal;
            do {
                opcioFaseFinal = menu.faseFinal(competicio.getFase(competicio.getNumFases() - 1).getRanking().get(0).getNickname(), competicio.getFase(competicio.getNumFases()).getRanking().get(0).getPuntuacio());
                switch (opcioFaseFinal){
                    case 2:
                        menu.mostrarRanking(competicio.getFase(competicio.getNumFases()).getPosMiRapero(), competicio.getFase(competicio.getNumFases()).getRanking());
                        break;
                    case 3:
                        Rapero rapero = menu.showProfile(competicio.getFase(competicio.getNumFases()));
                        CrearPerfil crearPerfil = new CrearPerfil(ProfileFactory.createProfile("src/proyecto/datos/html", rapero), rapero.getPaisString());
                        crearPerfil.buscarInfoPais();
                        crearPerfil.generarPerfil();
                        break;
                    default:
                        opcioFaseFinal = 5;
                }
            }while(opcioFaseFinal != 5);
        }
        menu.acabada(competicio.getFase(competicio.getNumFases() - 1).getRanking().get(0));
    }
}
