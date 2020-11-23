public class Main {
    public static void main(String[] args) {
        Competicio competicio = new Competicio();
        Fitxer f = new Fitxer();

        f.llegirCompeticio("src/competicio.json", competicio);
        System.out.println(competicio);

    }
}
