import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Menu {

    public void mostraMenu () {

    }

    public void mostrarDades (Competicio competicio) {
        System.out.println("Bienvenido/a a la competición: " + competicio.getNom());
        System.out.println("Comienza el " + competicio.getDataInici());
        System.out.println("Acaba el " + competicio.getDataFinal());
        System.out.println("Fases: " + competicio.getNumFases());
        System.out.println("Actualmente: " + competicio.getNumParticipants() + " " + "participantes");
        System.out.println(" ");
    }

    public int noComencada () {
        int opcio;
        String entrada;
        char aux;
        Scanner sc = new Scanner(System.in);
        System.out.println("La competición no ha empezado todavía. ¿Qué quieres hacer?");
        System.out.println(" ");
        System.out.println("1. Registrarte");
        System.out.println("2. Salir");
        System.out.println(" ");
        System.out.print("Escoge una opción: ");
        do{
            entrada = sc.nextLine();
            opcio = Integer.parseInt(entrada);

        }while (opcio != 1 && opcio != 2);

        return opcio;
    }

    public int comencada () {
        int opcio;
        String entrada;
        char aux;
        Scanner sc = new Scanner(System.in);
        System.out.println("Competicion empezada. ¿Qué quieres hacer?");
        System.out.println(" ");
        System.out.println("1. Login");
        System.out.println("2. Salir");
        System.out.println(" ");
        System.out.println("Escoge una opcion: ");
        do{
            entrada = sc.nextLine();
            opcio = Integer.parseInt(entrada);

        }while (opcio != 1 && opcio != 2);

        return opcio;
    }

    public void entradaInformacio (Rapero rapero) {
        char aux;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------");
        System.out.println("Por favor, introduzca su información personal:");
        System.out.println("- Nombre completo: ");

        rapero.setNomComplet(sc.nextLine());
        System.out.println("- Nombre artístico: ");

        rapero.setNomArtistic(sc.nextLine());
        System.out.println("- Bith date (dd/MM/YYY): ");

        String fechaComoTexto = sc.nextLine();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            rapero.setDataNaixement(sdf.parse(fechaComoTexto));
        }catch (ParseException e){
            System.out.println("Parse Exeption: " + e.getMessage());
        }
        System.out.println("- Pais: ");

        rapero.setPaisRapero(sc.nextLine());
        System.out.println("- Nivel: ");

        rapero.setNivell(sc.nextLong());
        System.out.println("- Foto: ");

        rapero.setFoto(sc.nextLine());
        //aqui supongo que deberíamos de comprobar que todo sea correcto
        System.out.println(" ");
        System.out.println("Registro completo!");
        System.out.println(" ");
        System.out.println("----------------------------------------------------");

    }
    public int mostraLobby (int numFase, int maxFases, int puntuacion, int batalla, String tipusBatalla, String nomRival){
        int opcio = 0;
        String entrada;
        char aux;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Fase: " + numFase + " / " + maxFases + " | Puntuación: " + puntuacion + " | Batalla " + batalla + " / 2: " + tipusBatalla + " | Rival: " + nomRival);
        System.out.println("----------------------------------------------------------------------------------------");

        System.out.println(" ");
        System.out.println("1. Empezar la batalla");
        System.out.println("2. Mostrar ranquing");
        System.out.println("3. Crear perfil");
        System.out.println("4. Salir de la competición");
        System.out.println(" ");
        System.out.println("Escoge una opción: ");
        do{
            if (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4) {
                System.out.println("Error, escoge una opcion válida.");
            }
            entrada = sc.nextLine();
            opcio = Integer.parseInt(entrada);

        }while (opcio != 1 && opcio != 2 && opcio != 3 && opcio != 4);

        return opcio;
    }
    public int faseFinal (int maxFases, int puntuacion) {
        int opcio = 0;
        String entrada;
        char aux;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Fase: " + maxFases + " / " + maxFases + " | Puntuación: " + puntuacion + " | Lo siento tio, has perdido, seguro que lo hacer mejor a la próxima...");
        System.out.println("----------------------------------------------------------------------------------------");

        System.out.println(" ");
        System.out.println("1. Empezar la batalla (desactivado)");
        System.out.println("2. Mostrar ranquing");
        System.out.println("3. Crear perfil");
        System.out.println("4. Salir de la competición");
        System.out.println(" ");
        System.out.println("Escoge una opción: ");
        do{
            if (opcio == 1) {
                System.out.println("La competición ha acabado. ¡No puedes competir con nadie más!");
            }else if(opcio != 2 && opcio != 3 && opcio != 4){
                System.out.println("Error, escoge una opcion válida.");
            }

            entrada = sc.nextLine();
            opcio = Integer.parseInt(entrada);

        }while (opcio != 2 && opcio != 3 && opcio != 4);

        return opcio;
    }

}
