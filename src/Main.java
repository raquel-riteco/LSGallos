import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        Fitxers f = new Fitxers();
        Competicio competicio = new Competicio();
        Batalla batallaModel = new Batalla();
        f.llegirCompeticio("src/competicio.json", competicio);
        //f.llegirBatalles("src/batalles.json", batallaModel);
        System.out.println(competicio.toString());
        //System.out.println(batallaModel.getTemas().toString());
        /*
        try {

            Rapero rapero = new Rapero("Raquel Riteco", "Reich", new SimpleDateFormat("yyyy-MM-dd").parse("2001-10-01"), 2, "Spain");
            f.registrarRapero(rapero, "src/competicio_modificat.json");
        } catch (ParseException e) {
            e.printStackTrace();
        }

         */

    }
}
