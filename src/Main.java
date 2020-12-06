import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        CompeticioController controller = new CompeticioController("src/competicio.json", "src/batalles.json");
        Batalla batallaModel = new Batalla();

        System.out.println(controller.getCompeticio().toString());
        System.out.println(batallaModel.getTemas().toString());

        try {

            Rapero rapero = new Rapero("Raquel Riteco", "Reich", new SimpleDateFormat("yyyy-MM-dd").parse("2001-10-01"), "Spain", (long) 2, "photo");

        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
}
