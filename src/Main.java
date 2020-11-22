public class Main {
    public static void main(String[] args) {
        Fitxer fitxer = new Fitxer();
        Competicio competicio = new Competicio();
        fitxer.llegirCompeticio(args[0], competicio);
    }
}
