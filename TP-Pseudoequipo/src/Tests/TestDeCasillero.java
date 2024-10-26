package Tests;

import Main.Ficha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Main.Casillero;

public class TestDeCasillero {
    @Test
    public void TestCreacionCasillero() throws Exception {
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

    @Test
    public void TestValidarCoordenadas() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            Casillero.validarCoordenada(-1);
        });

        Assertions.assertThrows(Exception.class, () -> {
            Casillero.validarCoordenadas(-25, 0, 0);
        });
    }

    @Test
    public void TestOperacionesBasicasFuncionan() throws Exception {
        Casillero<Ficha> casillero = new Casillero<Ficha>(2, 3, 1);

        // Comprobamos que sus coordenadas esten bien
        Assertions.assertEquals(casillero.getX(), 2);
        Assertions.assertEquals(casillero.getY(), 3);
        Assertions.assertEquals(casillero.getZ(), 1);

        // Comprobamos que no esta ocupado
        Assertions.assertFalse(casillero.estaOcupado());

        // Creamos una ficha
        Ficha ficha = new Ficha('p');

        // Comprobamos que el casillero no tenga la ficha como dato todavia
        Assertions.assertNotEquals(casillero.getDato(), ficha);
        Assertions.assertFalse(casillero.tiene(ficha));

        // Comprobamos que el casillero no este ocupado
        Assertions.assertFalse(casillero.estaOcupado());

        // Le colocamos al ficha al casillero como dato
        casillero.setDato(ficha);

        // Comprobamos que el casillero tenga la ficha como dato
        Assertions.assertEquals(casillero.getDato(), ficha);
        Assertions.assertTrue(casillero.tiene(ficha));

        // Comprobamos que el casillero este ocupado
        Assertions.assertTrue(casillero.estaOcupado());

        // Vaciamos el casillero (osea le ponemos de dato nulo)
        casillero.vaciar();

        // Comprobamos que el casillero no este ocupado
        Assertions.assertFalse(casillero.estaOcupado());

        // Comprobamos que el casillero no tenga la ficha como dato
        Assertions.assertNotEquals(casillero.getDato(), ficha);
        Assertions.assertFalse(casillero.tiene(ficha));
    }
}
