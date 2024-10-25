package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Main.Casillero;

public class TestDeCasillero {

    @Test
    public void TestDeCasillero() throws Exception {
        // Testeamos que creamos el casillero y podemos acceder a sus coordenadas
        Casillero<Integer> casillero = new Casillero<Integer>(2, 3, 3);

        Assertions.assertEquals(2, casillero.getX(), "El casillero en x no tiene valor 2");
        Assertions.assertEquals(3, casillero.getY(), "El casillero en y no tiene valor 3");
        Assertions.assertEquals(3, casillero.getZ(), "El casillero en z no tiene valor 3");

        // Testeamos que podemos setear el dato del casillero
        casillero.setDato(25);

        Assertions.assertEquals(25, casillero.getDato(), "El casillero no tiene dato 25");

        // Testeamos que tira error crear un casillero con coordenadas invalidas
        Assertions.assertThrows(Exception.class, () -> {
            Casillero<Integer> casillero2 = new Casillero<Integer>(5,-22, 11);
        });
    }
}
