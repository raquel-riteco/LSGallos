import java.util.ArrayList;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private ArrayList<Tema> temas;

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

    @Override
    public String toString() {
        return "Batalla{" +
                "idiomaNatiu='" + idiomaNatiu + '\'' +
                ", idiomaAngles='" + idiomaAngles + '\'' +
                ", temas=" + temas +
                '}';
    }

    /*
    protected String batallesEnCurs[];
    protected Tema temes[];


    public String generarAparellaments(Rapero raperos[]){



    }

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
