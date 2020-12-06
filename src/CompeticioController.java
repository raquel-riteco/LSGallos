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
        for (Rapero o : competicio.getRaperos()){
            if (o.getNomArtistic().equals(nomArtistic)){
                return competicio.getRaperos().indexOf(o);
            }
        }
        return -1;
    }

    public boolean comprovarLogin (String nomArtistic) {
        boolean found = false;
        for (int i = 0; i < competicio.getRaperos().size() && !found; i++) {
            if (competicio.getRaperos().get(i).getNomArtistic().equals(nomArtistic)){
                found = true;
            }
        }
        return found;
    }

    public boolean comprovarEntradaInformacio(Rapero rapero){

        for(Rapero o: competicio.getRaperos()){
            if(o.getNomComplet().equals(rapero.getNomComplet())) {

                return false;

            }else if(o.getNomArtistic().equals(rapero.getNomArtistic())){

                return false;

            }else if(o.getFoto().equals(rapero.getFoto())){

                return false;

            }

        }

        if(!competicio.getLlistaPaisos().contains(rapero.getPaisString())){

            return false;

        }

        if(rapero.getNivell()!=1 || rapero.getNivell()!= 2){

            return false;

        }

    }


    public void mostrarMenu (){
        int opcio = 0;
        menu.mostrarDades(competicio);
        switch (comprovarData()){
            case -1:
                opcio = menu.noComencada();
                if (opcio == 1){
                    fitxers.registrarRapero(menu.entradaInformacio(), nomCompeticio);
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

    public String tipusBatalla (){
        ArrayList<String> tipusBatalles = new ArrayList<>();
        tipusBatalles.add("BatallaAcapella");
        tipusBatalles.add("BatallaSangre");
        tipusBatalles.add("BatallaEscrita");
        int num = (int)Math.floor(Math.random()*3);
        return tipusBatalles.get(num);
    }


    public void executar(String nomArtistic){
        int faseActual = 1;
        int numBatalla = 1;
        while (faseActual <= competicio.getFases().size()){
            int opcioLobby = 0;

            //generar aparellaments
            String nomRival = ""; //a generar aparellaments
            //simular batalles

            while (opcioLobby != 5){
                String tipusBatalla = tipusBatalla();
                opcioLobby = menu.mostraLobby(faseActual, competicio, getPosMiRapero(nomArtistic), numBatalla, tipusBatalla, nomRival);
                switch (opcioLobby){
                    case 1:
                        //batallar
                        numBatalla++;
                        if (numBatalla == 2) {
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
