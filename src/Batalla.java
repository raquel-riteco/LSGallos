import java.util.ArrayList;
import java.util.Random;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private ArrayList<Tema> temas;

    protected ArrayList<String> aparellaments;
    //protected String batallesEnCurs[];
    //protected Tema temes[];

    public Batalla () {
        temas = new ArrayList<>();
    }

    public Batalla (String idiomaNatiu, String idiomaAngles) {
        this.idiomaNatiu = idiomaNatiu;
        this.idiomaAngles = idiomaAngles;
        temas = new ArrayList<>();

    }

    public void setTemas(Tema tema) {
        temas.add(tema);
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    @Override
    public String toString() {
        return "Batalla{" +
                "\nidiomaNatiu='" + idiomaNatiu + '\'' +
                "\nidiomaAngles='" + idiomaAngles + '\'' +
                "\ntemas=" + temas +
                '}';
    }




    public ArrayList<Rapero> generarAparellaments(ArrayList<Rapero> raperos){

        ArrayList<Rapero> aparellaments = new ArrayList<>();



            Random rand = new Random();

        if((raperos.size()%2)!=0){

            int rapero_out = rand.nextInt(raperos.size() - 1);
            raperos.remove(rapero_out);
        }

        int limit_aparellaments = raperos.size()/2;

        for(int i = 0;i< limit_aparellaments;i++){

            int rand1 = rand.nextInt((raperos.size()/2)-1);

            aparellaments.add(i,raperos.get(raperos.size()-1));
            aparellaments.add(i,raperos.get(rand1));

            raperos.remove(raperos.size()-1);
            raperos.remove(rand1);

        }

        return aparellaments;

    }
/*
    public void simular(){



    }

    public String escollirTemaEntrada(Tema temes[]){



    }

    public Rapero batallar(String batallesEnCurs[]){



    }

    public float calcularPuntuacio(String frase){



    }

     */
}
