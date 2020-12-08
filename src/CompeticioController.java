import java.util.ArrayList;
import java.util.Date;

public class CompeticioController {

    private Menu menu;
    private Fitxers fitxers;
    private Competicio competicio;
    private String nomCompeticio;
    private String nomBatalles;


    public CompeticioController (String nomCompeticio, String nomBatalles) {
        this.nomCompeticio = nomCompeticio;
        this.nomBatalles = nomBatalles;
        menu = new Menu();
        competicio = new Competicio();
        fitxers = new Fitxers();
    }

    public Menu getMenu() {
        return menu;
    }

    public Competicio getCompeticio() {
        return competicio;
    }

    public void setCompeticio() {
        this.competicio = fitxers.llegirCompeticio(nomCompeticio);
    }


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
    public int getPosMiRapero (String nomArtistic) {
        for (Rapero o : competicio.getBatallaModel().getRaperos()){
            if (o.getNomArtistic().equals(nomArtistic)){
                return competicio.getBatallaModel().getRaperos().indexOf(o);
            }
        }
        return -1;
    }

    public boolean comprovarLogin (String nomArtistic) {
        boolean found = false;
        for (int i = 0; i < competicio.getBatallaModel().getRaperos().size() && !found; i++) {
            if (competicio.getBatallaModel().getRaperos().get(i).getNomArtistic().equals(nomArtistic)){
                found = true;
            }
        }
        return found;
    }

    public void mostrarMenu (){
        int opcio;
        setCompeticio();
        menu.mostrarDades(competicio);
        switch (comprovarData()){
            case -1:
                opcio = menu.noComencada();
                if (opcio == 1){
                    boolean ok = false;
                    Rapero nouRapero;
                    nouRapero = menu.entradaInformacio(competicio);
                    competicio.getBatallaModel().registrar(nouRapero);
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

    public void executar(String nomArtistic){
        fitxers.llegirBatalles(nomBatalles, competicio.getBatallaModel());
        int faseActual = 0;
        int numBatalla = 1;
        while (faseActual < competicio.getFases().size()){
            competicio.getBatallaModel().getRaperos().get(getPosMiRapero(nomArtistic)).setPuntuacio(0);
            int opcioLobby = 0;
            //simular batalles
            String nomRival = "";
            int numAparellament = 0;
            competicio.getBatallaModel().setAparellaments(getPosMiRapero(nomArtistic));
            for (ArrayList<Rapero> o: competicio.getBatallaModel().getAparellaments()) {
                if (o.get(0).getNomArtistic().equals(nomArtistic)){
                    nomRival = o.get(1).getNomArtistic();
                    numAparellament = competicio.getBatallaModel().getAparellaments().indexOf(o);
                    break;
                }else if(o.get(1).getNomArtistic().equals(nomArtistic)){
                    nomRival = o.get(0).getNomArtistic();
                    numAparellament = competicio.getBatallaModel().getAparellaments().indexOf(o);
                    break;
                }
            }
            while (opcioLobby != 5){
                String tipusBatalla = competicio.getBatallaModel().tipusBatalla();
                opcioLobby = menu.mostraLobby(faseActual, competicio, getPosMiRapero(nomArtistic), numBatalla, tipusBatalla, nomRival);
                switch (opcioLobby){
                    case 1:
                        int quienEmpieza = (int) Math.floor(Math.random()*2);
                        competicio.getBatallaModel().setEstrofas(menu.batalla(competicio.getBatallaModel().escollirTemaEntrada(competicio.getBatallaModel().getTemas()), competicio.getBatallaModel(), quienEmpieza, numAparellament, nomRival));
                        switch (tipusBatalla){
                            case "BatallaAcapela":
                                BatallaAcapella acapella = new BatallaAcapella();
                                competicio.getBatallaModel().getRaperos().get(getPosMiRapero(nomArtistic)).actualitzarPuntuacio((float) acapella.calculoPuntuacion(competicio.getBatallaModel().calcularRimes()));
                                System.out.println(competicio.getBatallaModel().getRaperos().get(getPosMiRapero(nomArtistic)).getPuntuacio());

                                break;
                            case "BatallaEscrita":
                                BatallaEscrita escrita = new BatallaEscrita();
                                competicio.getBatallaModel().getRaperos().get(getPosMiRapero(nomArtistic)).actualitzarPuntuacio((float) escrita.calculoPuntuacion(competicio.getBatallaModel().calcularRimes()));
                                System.out.println(competicio.getBatallaModel().getRaperos().get(getPosMiRapero(nomArtistic)).getPuntuacio());

                                break;
                            case "BatallaSangre":
                                BatallaSangre sangre = new BatallaSangre();
                                competicio.getBatallaModel().getRaperos().get(getPosMiRapero(nomArtistic)).actualitzarPuntuacio((float) sangre.calculoPuntuacion(competicio.getBatallaModel().calcularRimes()));
                                System.out.println(competicio.getBatallaModel().getRaperos().get(getPosMiRapero(nomArtistic)).getPuntuacio());

                                break;
                        }
                        numBatalla++;
                        if (numBatalla > 2) {
                            competicio.actualitzarPuntuacio();
                            //descartar raperos
                            faseActual++;
                            numBatalla = 1;
                            opcioLobby = 5;

                        }
                        break;
                    case 2:
                        menu.mostrarRanking(getPosMiRapero(nomArtistic), competicio.getRanking());
                        break;
                    case 3:

                        break;
                    case 4:
                        //leave competition
                        //simulate competition
                        faseActual = 2;
                        opcioLobby = 5;
                        break;
                }
            }
        }
    }

}
