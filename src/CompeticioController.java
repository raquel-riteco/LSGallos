import java.util.ArrayList;
import java.util.Date;

public class CompeticioController {

    private Menu menu;
    private Fitxers fitxers;
    private Competicio competicio;
    private String nomCompeticio;
    private String nomBatalles;

    /* CONSTRUCTORES*/

    public CompeticioController (String nomCompeticio, String nomBatalles) {
        this.nomCompeticio = nomCompeticio;
        this.nomBatalles = nomBatalles;
        menu = new Menu();
        competicio = new Competicio();
        setCompeticio();
        fitxers = new Fitxers();

        fitxers.llegirBatalles(nomBatalles, competicio);
    }


    /* GETTERS */

    public Menu getMenu() {
        return menu;
    }

    public Competicio getCompeticio() {
        return competicio;
    }

    /* SETTERS */

    public void setCompeticio() {
        this.competicio = fitxers.llegirCompeticio(nomCompeticio);
    }

    /* COMPROBACIONES */

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

    public boolean comprovarLogin (String nomArtistic) {
        boolean found = false;
        // fase 0 == fase inicial
        for (int i = 0; i < competicio.getFase(0).getRaperos().size() && !found; i++) {
            if (competicio.getFase(0).getRaperos().get(i).getNomArtistic().equals(nomArtistic)){
                found = true;
            }
        }
        return found;
    }


    /* MÉTODOS */

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
                    competicio.getFase(0).setRaperos(nouRapero);
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
                        if (!comprovarLogin(nomArtistic)){
                            System.out.println("Heeeey! Este rapero no esta registrado!");
                        }
                    }while(!comprovarLogin(nomArtistic));
                    executar(nomArtistic);
                }else {
                    System.out.println("Ens veiem aviat!");
                }
                break;
            case 1:
                menu.acabada();
            break;
        }
    }

    public void executar(String nomArtistic) {
        int opcioLobby = 0;
        for (Fase fase : competicio.getFases()) {
            if (opcioLobby == 5) {
                break;
            }
            for (Batalla batalla : fase.getBatallas()) {
                if (opcioLobby == 5) {
                    break;
                }

                opcioLobby = menu.mostraLobby(fase.getNumFase(), competicio.getNumFases(), fase.getRaperos().get(fase.getPosMiRapero()).getPuntuacio(), batalla.getNumBatalla(), batalla.getTipusBatalla(), batalla.getNomRival());
                switch (opcioLobby){
                    case 1:
                        int quienEmpieza = (int) Math.floor(Math.random()*2);
                        int numAparellament = 0;
                        batalla.setEstrofas(menu.batalla(fase, batalla, quienEmpieza, numAparellament, batalla.getNomRival()));
                        break;
                    case 2:
                        menu.mostrarRanking(fase.getPosMiRapero(), competicio.getRanking());
                        break;
                    case 3:

                        break;
                    case 4:
                        //leave competition
                        //simulate competition
                        opcioLobby = 5;
                        break;
                }

            }

        }
    }

}
