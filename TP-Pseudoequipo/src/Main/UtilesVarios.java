package Main;

public class UtilesVarios {
    /**
     * pre:
     * @param texto no puede ser nulo
     * @throws Exception si texto es nulo
     * post: imprime el texto con lineas horizontales arriba y abajo suyo
     */
    public static void mostrarTextoAlrededorDeLineas(String texto) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(texto, "Texto");
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(texto);
        System.out.println("--------------------------------------------------------------------------------");
    }

    /**
     * pre:
     * @param e no puede ser nulo
     * post: muestra el mensaje del error
     */
    public static void mostrarError(Exception e) {
        System.out.println("\nError: " + e.getMessage());
    }
}
