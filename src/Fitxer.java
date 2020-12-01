import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Fitxer {

    public void llegirCompeticio (String nomCompeticio, Competicio competicio) throws FileNotFoundException {
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
                        if (key2.equals("name")) {
                            key2 = parser.getString();
                            competicio.setNom(key2);
                            event = parser.next();
                        }
                        key2 = parser.getString();
                        event = parser.next();
                        if (key2.equals("startDate")) {
                            try {
                                key2 = parser.getString();
                                competicio.setDataInici(new SimpleDateFormat("yyyy-MM-dd").parse(key2));
                                event = parser.next();
                            } catch (ParseException e) {
                                System.out.println("ParseException dataInici: " + e.getMessage());
                            }
                        }
                        key2 = parser.getString();
                        event = parser.next();
                        if (key2.equals("endDate")) {
                            try {
                                key2 = parser.getString();
                                competicio.setDataFinal(new SimpleDateFormat("yyyy-MM-dd").parse(key2));
                            } catch (ParseException e) {
                                System.out.println("ParseException dataFinal: " + e.getMessage());
                            }
                        }
                        key2 = parser.getString();
                        if (key2.equals("phases")) {
                            JsonArray phases = parser.getArray();
                        }
                        break;

                    case "countries":
                       JsonArray countries = parser.getArray();
                       procesarCountries(competicio, countries);
                       System.out.println(competicio.getLlistaPaisos());
                       break;

                    case "rappers":
                        ArrayList<Rapero> raperos = new ArrayList<>();
                        while (parser.hasNext()){
                            event = parser.next();
                            if (event == JsonParser.Event.START_OBJECT){
                                Rapero rapero = new Rapero();
                                event = parser.next();
                                key2 = parser.getString();
                                event = parser.next();
                                if (key2.equals("realName")) {
                                    rapero.setNomComplet(parser.getString());
                                    event = parser.next();
                                }
                                key2 = parser.getString();
                                event = parser.next();
                                if (key2.equals("stageName")) {
                                    rapero.setNomArtistic(parser.getString());
                                    event = parser.next();
                                }
                                key2 = parser.getString();
                                event = parser.next();
                                if (key2.equals("birth")) {
                                    try {
                                        key2 = parser.getString();
                                        rapero.setDataNaixement(new SimpleDateFormat("yyyy-MM-dd").parse(key2));
                                    } catch (ParseException e) {
                                        System.out.println("ParseException dataRapero: " + e.getMessage());
                                    }
                                }
                                event = parser.next();
                                key2 = parser.getString();
                                if (key2.equals("nationality")){
                                    event = parser.next();
                                    rapero.setPaisString(parser.getString());
                                    event = parser.next();
                                }
                                key2 = parser.getString();
                                if (key2.equals("level")){
                                    event = parser.next();
                                    int aux = Integer.parseInt(parser.getString());
                                    rapero.setNivell(aux);
                                    event = parser.next();
                                }
                                key2 = parser.getString();
                                event = parser.next();
                                if (key2.equals("photo")) {
                                    rapero.setFoto(parser.getString());
                                }
                                raperos.add(rapero);
                                System.out.println(rapero.toString());
                            }
                        }
                        competicio.setRaperos(raperos);
                    break;
                }
            }
        }
    }

    protected void procesarCountries (Competicio competicio, JsonArray countries) {
        ArrayList<String> listdata = new ArrayList<>();
        if (countries != null) {
            for (int i = 0; i < countries.size(); i++){
                listdata.add(countries.getString(i));
            }
        }
        competicio.setLlistaPaisos(listdata);
    }

    public void llegirBatalles(String nomBatalles, Batalla batalla) throws FileNotFoundException {
        JsonParser parser = Json.createParser(new FileReader(nomBatalles));
        JsonParser.Event event = parser.next();
        event = parser.next();
        while (parser.hasNext()) {
            event = parser.next();
            if (event == JsonParser.Event.START_OBJECT){
                JsonObject tema = parser.getObject();
                procesarObject(tema, batalla);
            }
        }
    }

    protected void procesarObject(JsonObject tema, Batalla batalla){
        /*
        Tema temaAux = new Tema();

        temaAux.setNomTema(tema.getString("name"));
        JsonArray rima = tema.getJsonArray("rhymes");
        String barra1, barra2;
        for (int nivel = 1; nivel < 3; nivel++) {
            barra1 = rima.getString(0);
            barra2 = rima.getString(1);
            temaAux.setEstrofas(nivel, barra1, barra2);
        }
        batalla.setTemas(temaAux);

         */
    }
}


