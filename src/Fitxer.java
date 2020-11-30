import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.stream.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Fitxer {

    public void llegirCompeticio (String nomCompeticio, Competicio competicio) throws FileNotFoundException, JsonException {
        JsonParser parser = Json.createParser(new FileReader(nomCompeticio));
        while (parser.hasNext()){
            JsonParser.Event event = parser.next(); //{
            if (event == JsonParser.Event.KEY_NAME){
                String key = parser.getString();
                event = parser.next();
                switch (key) {
                    case "competition":
                        event = parser.next();
                        String key2 = parser.getString();
                        event = parser.next();
                        switch (key2) {
                            case "name":
                                event = parser.next();
                                key2 = parser.getString();
                                competicio.setNom(key2);
                                event = parser.next();
                                break;
                            case "startDate":
                                try {
                                    event = parser.next();
                                    key2 = parser.getString();
                                    competicio.setDataInici(new SimpleDateFormat("dd/MM/yyyy").parse(key2));
                                    event = parser.next();
                                } catch (ParseException e) {
                                    System.out.println("ParseException dataInici: " + e.getMessage());
                                }
                                break;
                            case "endDate":
                                try {
                                    event = parser.next();
                                    key2 = parser.getString();
                                    competicio.setDataFinal(new SimpleDateFormat("dd/MM/yyyy").parse(key2));
                                    event = parser.next();
                                } catch (ParseException e) {
                                    System.out.println("ParseException dataFinal: " + e.getMessage());
                                }
                                break;
                            case "phases":
                                JsonArray phases = parser.getArray();
                                break;
                        }
                        break;
                    case "countries":

                        JsonArray countries = parser.getArray();
                        break;
                    case "rappers":

                        JsonArray rappers = parser.getArray();
                        break;
                }
            }
        }
    }

    public void llegirBatalles(String nomBatalles, Batalla batalla) {

    }
}
