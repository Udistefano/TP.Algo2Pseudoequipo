package Main;

public class ValidacionUtiles {
    /**
     * pre:
     * @param objeto de cualquier tipo
     * @throws Exception si el objeto es nulo
     */
    public static void validarSiEsNulo(Object objeto) throws Exception {
        if (objeto == null) {
            throw new Exception("El objeto no puede ser nulo");
        }
    }
}
