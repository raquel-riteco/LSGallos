import java.util.Date;

public class CompeticioController {

    private Menu menu;
    private Fitxers fitxers;
    private Competicio competicio;
    private String nomCompeticio;
    private String nomBatalles;
    private int posMiRapero;


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
    public void mostrarMenu (int posMiRapero){
        int opcio = 0;
        menu.mostrarDades(competicio);
        switch (comprovarData()){
            case -1:
                opcio = menu.noComencada();
                if (opcio == 1){
                    Rapero rapero = menu.entradaInformacio();
                    mostrarMenu(posMiRapero);
                }else {
                    System.out.println("Ens veiem aviat!");
                }
                break;
            case 0:
                opcio = menu.comencada();
                if (opcio == 1){
                    //login rapero
                    executar();
                }else {
                    System.out.println("Ens veiem aviat!");
                }
                break;
            case 1:
                menu.acabada();
            break;
        }
    }


    public void executar(){
        int faseActual = 0;
        int opcioLobby = 0;
        //generar aparellaments
        //simular batalles
        //info estat competicio
        //opcioLobby = menu.mostraLobby(faseActual, competicio, posMiRapero, numBatalla, tipusBatalla, nomRival);
        switch (opcioLobby){
            case 1:
                //batallar
                break;
            case 2:
                //mostrar ranquing
                break;
            case 3:
                //crear perfil
                break;
            case 4:
                System.out.println("Ens veiem aviat");
                break;
        }

    }


}
