package Tests;

import Main.Casillero;
import Main.Ficha;
import Main.Tablero;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestDeTablero {
    @Test
    public void testCrearTablero() throws Exception {
        // Testeamos que creamos el tablero y podemos acceder a sus coordenadas
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);

        Assertions.assertEquals(3, tablero.getAncho(), "El casillero en x no tiene valor 3");
        Assertions.assertEquals(3, tablero.getAlto(), "El casillero en y no tiene valor 3");
        Assertions.assertEquals(3, tablero.getProfundidad(), "El casillero en z no tiene valor 3");

        // Se valida que la excepción funcione
        Assertions.assertThrows(Exception.class, () -> {
            Tablero<Ficha> tablero2 = new Tablero<Ficha>(0, -3, 334);
        });
    }

    @Test
    public void testAgregarFicha() throws Exception {
        // Testeamos que creamos el tablero y podemos acceder a sus coordenadas
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);

        //creo Ficha ficha
        Ficha ficha = new Ficha('x');

        // se agrega la ficha en el casillero con coordenadas (3, 2, 1)
        tablero.agregar(3, 1, 2, ficha);

        // se comprueba que la ficha esté en las coordenadas (3, 2, 1)
        Assertions.assertEquals(ficha, tablero.obtener(3, 1, 2), "Ficha no esta en 3, 1, 2");
        Assertions.assertEquals(ficha, tablero.getCasillero(3, 1, 2).getDato(), "Ficha no esta en 3, 1, 2");
    }

    @Test
    public void testCasillerosSonCreadosBien() throws Exception {
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);

        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                for (int z = 1; z <= 3; z++) {
                    // Conseguimos el casillero en las coordenadas (x, y, z)
                    Casillero<Ficha> casillero = tablero.getCasillero(x, y, z);

                    // Comprobamos que sus coordenadas esten bien
                    Assertions.assertEquals(casillero.getX(), x);
                    Assertions.assertEquals(casillero.getY(), y);
                    Assertions.assertEquals(casillero.getZ(), z);
                }
            }
        }
    }

    // Si es el casillero central en un tablero 3x3x3 entonces todos los demas casilleros seran
    // sus vecinos
    @Test
    public void testVecinosDeCasilleroEnElCentro() throws Exception {
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);
        // Conseguimos el casillero central en las coordenadas (2, 2, 2)
        Casillero<Ficha> casilleroCentral = tablero.getCasillero(2, 2, 2);
        // Conseguimos los vecinos del casillero central
        Casillero<Ficha>[][][] vecinos = casilleroCentral.getCasillerosVecinos();

        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = 0; z <= 2; z++) {
                    // Comprobamos que el vecino no sea nulo
                    Assertions.assertNotNull(vecinos[x][y][z]);

                    // Conseguimos el casillero vecino
                    Casillero<Ficha> casilleroVecino = vecinos[x][y][z];

                    // Comprobamos que sus coordenadas esten bien
                    Assertions.assertEquals(casilleroVecino.getX(), x + 1);
                    Assertions.assertEquals(casilleroVecino.getY(), y + 1);
                    Assertions.assertEquals(casilleroVecino.getZ(), z + 1);

                    // Comprobamos que el vecino central sea el mismo casillero central
                    if ((x == 1)
                            && (y == 1)
                            && (z == 1)) {
                        Assertions.assertEquals(casilleroVecino, casilleroCentral);
                    }
                }
            }
        }
    }

    // Si es el casillero central izquierdo en un tablero 3x3x3 entonces todos los demas casilleros seran
    // sus vecinos, exceptuando el plano con profundidad 3
    @Test
    public void testVecinosDeCasilleroEnElCentroIzquierdo() throws Exception {
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);
        // Conseguimos el casillero central en las coordenadas (2, 2, 2)
        Casillero<Ficha> casilleroCentralIzquierdo = tablero.getCasillero(2, 2, 1);
        // Conseguimos los vecinos del casillero central
        Casillero<Ficha>[][][] vecinos = casilleroCentralIzquierdo.getCasillerosVecinos();

        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = 0; z <= 2; z++) {
                    if (z == 2) {
                        // Si estamos en el plano con profundidad 3, entonces deberia ser un vecino nulo
                        Assertions.assertNull(vecinos[x][y][z]);
                    } else {
                        // Comprobamos que el vecino no sea nulo
                        Assertions.assertNotNull(vecinos[x][y][z]);

                        // Conseguimos el casillero vecino
                        Casillero<Ficha> casilleroVecino = vecinos[x][y][z];

                        // Comprobamos que sus coordenadas esten bien
                        Assertions.assertEquals(casilleroVecino.getX(), x + 1);
                        Assertions.assertEquals(casilleroVecino.getY(), y + 1);
                        Assertions.assertEquals(casilleroVecino.getZ(), z + 1);

                        // Comprobamos que el vecino central sea el mismo casillero central
                        if ((x == 1)
                                && (y == 1)
                                && (z == 0)) {
                            Assertions.assertEquals(casilleroVecino, casilleroCentralIzquierdo);
                        }
                    }
                }
            }
        }
    }
}
