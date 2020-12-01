import javax.json.JsonException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Fitxer f = new Fitxer();
        Competicio competicio = new Competicio();
        Batalla batallaModel = new Batalla();
        try{
            f.llegirCompeticio("src/competicio.json", competicio);
            f.llegirBatalles("src/batalles.json", batallaModel);
        }catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero: " + e.getMessage());
        }
        System.out.println(competicio.toString());
        System.out.println(batallaModel.toString());

    }
}
