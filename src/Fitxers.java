import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Fitxers {

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
            System.out.println("Parse Exeprion a llegirCompeticio: " + e.getMessage());;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return competicio;
    }
    public void parseCompeticio (Competicio competicio, JSONObject object) throws java.text.ParseException {

        JSONObject competicioObject = (JSONObject) object.get("competition");
        competicio.setNom((String) competicioObject.get("name"));
        competicio.setDataInici(new SimpleDateFormat("yyyy-MM-dd").parse((String) competicioObject.get("startDate")));
        competicio.setDataFinal(new SimpleDateFormat("yyyy-MM-dd").parse((String) competicioObject.get("endDate")));
        JSONArray fasesJson = (JSONArray) competicioObject.get("phases");
        int fases = 0;
        for (Object o : fasesJson) {
            JSONObject fase = (JSONObject) o;
            competicio.getFase(fases).setPressupost((double) fase.get("budget"));
            competicio.getFase(fases).setPais((String) fase.get("country"));
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
