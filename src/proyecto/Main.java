package proyecto;

import proyecto.aplicacion.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller("src/proyecto/datos/json/competicio.json", "src/proyecto/datos/json/batalles.json");

        controller.mostrarMenu();

    }
}
