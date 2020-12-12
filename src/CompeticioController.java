import java.util.Date;

/**
 *      Esta es la clase principal del programa, a partir de esta configuraremos el resto de clases y ejecutaremos
 *      los métodos necesarios para el correcto funcionamiento del programa.
 *
 * */

public class CompeticioController {

    private final Menu menu;
    private final Fitxers fitxers;
    private final String nomCompeticio;

    private Competicio competicio;

    /* CONSTRUCTORES*/

    /**
     *      Crea un objeto de la clase CompeticioController.
     *      Este constructor es esencial, ya que a partir del objeto creado ejecutaremos el único método necesario
     *      para la ejecución del programa.
     *      -- Referncia al método mostrarMenu();
     *
     *      @param nomBatalles el "path" del fichero Batalles (del tipo Json) utilizado para la lectura del fichero.
     *                         --referencia a Fitxer/llegirBatalles
     *      @param nomCompeticio el "path" del fichero Competicio (del tipo Json) utilizado para la lectura del
     *                           fichero. Este parámetro se guarda como atributo del objeto creado.
     *                           -- referencia Fitxer/llegirCompeticio
     *      En este constructor se crean los objetos del tipo Menu, Fitcher y Competicio, los cuales son atributos de
     *      esta clase (CompeticioController). También se ejecutan los métodos setCompeticio() y llegirBatalles()
     *      -- Referencia a los métodos setCompeticio y llegirBatalles
     * */

    public CompeticioController (String nomCompeticio, String nomBatalles) {
        this.nomCompeticio = nomCompeticio;
        menu = new Menu();
        fitxers = new Fitxers();
        competicio = new Competicio();
        setCompeticio();
        fitxers.llegirBatalles(nomBatalles, competicio);
    }

    /* SETTERS */

    /**
     *      Lectura del fichero Competicio y guardado de la informacion en competicio, atributo de la clase
     *      CompeticioController.
     *      Ejecuta el método llegirCompeticio() de la clase Fitxer.
     *      -- Referencia al método llegirCompeticio.
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
     *      @param nomArtistic el nombre introducido por teclado y guardado a través del método login de la clase
     *                         menú.
     *                         -- Referencia al método login de clase menu
     *      @return false en caso de que sí se haya encontrado
     *              true en caso de que no se haya encontrado.
     * */

    public boolean comprovarLogin (String nomArtistic) {
        boolean found = false;
        for (int i = 0; i < competicio.getFase(0).getRaperos().size() && !found; i++) {
            if (competicio.getFase(0).getRaperos().get(i).getNomArtistic().equals(nomArtistic)){
                found = true;
            }
        }
        return !found;
    }

    /* MÉTODOS */

    /**
     *      Método principal a partir del cual se ejecutan todos los demás. Es el único método utilizado para iniciar
     *      el programa.
     * */

    public void mostrarMenu (){
        int opcio;
        menu.mostrarDades(competicio);
        switch (comprovarData()){
            case -1:
                opcio = menu.noComencada();
                if (opcio == 1){
                    boolean ok = false;
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

    public void executar(String nomArtistic) {
        int opcioLobby = 0;
        boolean raperoClasificado = false;
        for (Fase fase : competicio.getFases()) {

            competicio.actualizarListaRaperos(fase);
            for (Rapero rapero : fase.getRaperos()) {
                if (rapero.getNomArtistic().equals(nomArtistic)){
                    raperoClasificado = true;
                    break;
                }
            }
            fase.formarBatallas();
            if (raperoClasificado){
                fase.setPosMiRapero(nomArtistic);
            }else{
                opcioLobby = 4;
            }

            for (Batalla batalla : fase.getBatallas()) {
                while (opcioLobby != 4 && opcioLobby != 1){
                    if (opcioLobby != 4){
                        opcioLobby = menu.mostraLobby(fase.getNumFase(), competicio.getNumFases(), fase.getRaperos().get(fase.getPosMiRapero()).getPuntuacio(), batalla.getNumBatalla(), batalla.getTipusBatalla(), batalla.getNomRival());
                    }
                    switch (opcioLobby){
                        case 1:
                            int quienEmpieza = (int) Math.floor(Math.random()*2);
                            batalla.setEstrofas(menu.batalla(fase, batalla, quienEmpieza, batalla.getNomRival()));
                            fase.setPuntuacionBatalla(fase.getBatallas().indexOf(batalla));
                            fase.simulaciones(fase.getBatallas().indexOf(batalla));
                            break;
                        case 2:
                            menu.mostrarRanking(fase.getPosMiRapero(), fase.getRanking());
                            break;
                        case 3:
                            System.out.println("Esta opcion todavía no se puede ejecutar! Prueba más tarde.");
                            break;
                        case 4:
                            fase.simulaciones(fase.getBatallas().indexOf(batalla));
                            break;
                    }
                }
            }
            fase.setRanking();
            fase.descartarRaperos(competicio.getNumFases());
        }
        if (opcioLobby != 4){
            int opcioFaseFinal;
            do {
                opcioFaseFinal = menu.faseFinal(competicio.getFase(competicio.getNumFases() - 1).getRanking().get(0).getNomArtistic(), competicio.getFase(competicio.getNumFases()).getRanking().get(0).getPuntuacio());
                switch (opcioFaseFinal){
                    case 2:
                        menu.mostrarRanking(competicio.getFase(competicio.getNumFases()).getPosMiRapero(), competicio.getFase(competicio.getNumFases()).getRanking());
                        break;
                    case 3:
                        //fase 4
                        break;
                    default:
                        opcioFaseFinal = 5;
                }
            }while(opcioFaseFinal != 5);
        }
        menu.acabada(competicio.getFase(competicio.getNumFases() - 1).getRanking().get(0));
    }
}
