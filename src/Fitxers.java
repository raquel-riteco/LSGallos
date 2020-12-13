import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *      Esta clase es la responsable de leer los 2 ficheros JSON que contienen toda la información relacionada con
 *      la competición, necesaria para su ejecución.
 * */

public class Fitxers {

    /**
     *      Este método lee la información del fichero Competicio y la devuelve como objeto de la clase Competicio.
     *
     *      @param nomCompeticio (String) path del fichero Competicio.
     *
     *      @return Competicio competición con toda la información ya guardada.
     * */

    public Competicio llegirCompeticio (String nomCompeticio){
        Competicio competicio = new Competicio();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(nomCompeticio)){
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            parseCompeticio(competicio, obj);

        }catch (FileNotFoundException e){
            System.out.println("File Not Found a llegirCompeticio: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO Exception a llegirCompeticio: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Parse Exeprion a llegirCompeticio: " + e.getMessage());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return competicio;
    }

    /**
     *      Este método es el responsable de emparejar la lectura de cada objeto JSON con el atributo correspondiente
     *      de la clase Competicio.
     *
     *      Todos los parámetros de este método son pasados automáticamente por el programa.
     *      @param competicio (Competicio) competición en la que se quiere guardar la información.
     *      @param object (JSONObject) objecto extraido del fichero JSON el cual se quiere guardar en competicio.
     */

    public void parseCompeticio (Competicio competicio, JSONObject object) throws java.text.ParseException {

        JSONObject competicioObject = (JSONObject) object.get("competition");
        competicio.setNom((String) competicioObject.get("name"));
        competicio.setDataInici(new SimpleDateFormat("yyyy-MM-dd").parse((String) competicioObject.get("startDate")));
        competicio.setDataFinal(new SimpleDateFormat("yyyy-MM-dd").parse((String) competicioObject.get("endDate")));
        JSONArray fasesJson = (JSONArray) competicioObject.get("phases");
        int fases = 0;
        for (Object o : fasesJson) {
            JSONObject fase = (JSONObject) o;
            Fase fase1 = new Fase(fases + 1);
            fase1.setPressupost((double) fase.get("budget"));
            fase1.setPais((String) fase.get("country"));
            competicio.setFase(fase1);
            fase1.setPais((String) fase.get("country"));
            fases++;
        }
        competicio.setNumFases();
        JSONArray countries = (JSONArray) object.get("countries");
        for (Object country : countries) {
            competicio.setLlistaPaisos((String) country);
        }
        JSONArray rappers = (JSONArray) object.get("rappers");
        for (Object o : rappers){
            JSONObject rapper = (JSONObject) o;
            Rapero rapero = new Rapero(
                    (String) rapper.get("realName"),
                    (String) rapper.get("stageName"),
                    (String) rapper.get("birth"),
                    (String) rapper.get("nationality"),
                    ((Long) rapper.get("level")),
                    (String) rapper.get("photo"));
            competicio.getFase(0).setRapero(rapero);
        }
    }

    /**
     *      Este método lee la información del fichero Competicio y la devuelve como objeto de la clase Competicio.
     *
     *      @param nomBatalles (String) path del fichero Batalles.
     *      @param competicio (Competicio) competición en la cual se quiere guardar la información.
     * */

    public void llegirBatalles (String nomBatalles, Competicio competicio) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(nomBatalles)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONArray array = (JSONArray) obj.get("themes");
            for (Object o : array) {
                JSONObject object = (JSONObject) o;
                parseBatalla(competicio, object);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found a llegirBatalles: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException a llegirBatalles: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Parse Exception a llegirBatalles: " + e.getMessage());
        }
    }

    /**
     *      Este método es el responsable de guardar cada tema del fichero Batalles en su correspondiente hueco en la
     *      ArrayList temes de la clase Fase, en cada una de las fases de la competición.
     *
     *      Todos los parámetros de este método son pasados automáticamente por el programa.
     *      @param competicio (Competicio) competición en la que se quiere guardar la información.
     *      @param object (JSONObject) objecto extraido del fichero JSON el cual se quiere guardar en competicio.
     */

    public void parseBatalla (Competicio competicio, JSONObject object) {
        Tema tema = new Tema((String) object.get("name"));
        JSONObject rimas = (JSONObject) ((JSONArray) object.get("rhymes")).get(0);
        for (int nivel = 1; nivel <= 2; nivel++) {
            JSONArray arrayRimas = (JSONArray) rimas.get(String.valueOf(nivel));
            Tema.Estrofa estrofa = new Tema.Estrofa(nivel);
            for (Object o : arrayRimas) {
                estrofa.setBarra((String) o);
            }
            tema.setEstrofas(estrofa);
        }
        for (Fase f : competicio.getFases()) {
            f.setTemas(tema);
        }
    }

    /**
     *      Método para añadir un rapero al JSONArray de rapper del fichero Competicio.
     *
     *      Todos los parámetros de este método son pasados automáticamente por el programa.
     *      @param rapero (Rapero) rapero a añadir.
     *      @param nomCometicio (String) path del fichero Competicio.
     * */

    public void registrarRapero (Rapero rapero, String nomCometicio) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(nomCometicio)){
            JSONObject rapper = new JSONObject();
            rapper.put("realName", rapero.getNomComplet());
            rapper.put("stageName", rapero.getNomArtistic());
            rapper.put("birth", rapero.getDataNaixement());
            rapper.put("nationality", rapero.getPaisString());
            rapper.put("level", rapero.getNivell());
            rapper.put("photo", rapero.getFoto());
            JSONObject competitionFile = (JSONObject) parser.parse(reader);
            JSONArray arrayRappers = (JSONArray) competitionFile.get("rappers");
            arrayRappers.add(rapper);
            competitionFile.replace("rappers", arrayRappers);

            try (FileWriter writer = new FileWriter(nomCometicio)){
                //ObjectMapper mapper = new ObjectMapper();
                //Object json = mapper.readValue(competitionFile.toJSONString(), Object.class);
                //String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
                writer.write(competitionFile.toJSONString());
                writer.flush();

            }catch (IOException e){
                System.out.println("IO Exeption a al registrar rapero al fitcher: " + e.getMessage());
            }
        } catch (IOException | ParseException e){
            System.out.println(e.getMessage());
        }
    }
}
