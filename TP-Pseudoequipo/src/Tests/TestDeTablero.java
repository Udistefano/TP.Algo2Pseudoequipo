package Tests;

import Main.Casillero;
import Main.Color;
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
            new Tablero<Ficha>(0, -3, 334);
        });
    }

    @Test
    public void testAgregarFicha() throws Exception {
        // Testeamos que creamos el tablero y podemos acceder a sus coordenadas
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);

        //creo Ficha ficha
        Ficha ficha = new Ficha(Color.CELESTE);

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

    // Si es el casillero central (2, 2, 2) en un tablero 3x3x3 entonces todos los demas casilleros sera sus vecinos,
    // deberia tener 27 vecinos incluyendose
    @Test
    public void testVecinosDeCasilleroEnElCentro() throws Exception {
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);
        Casillero<Ficha> casilleroCentral = tablero.getCasillero(2, 2, 2);
        Casillero<Ficha>[][][] vecinos = casilleroCentral.getCasillerosVecinos();
        int cantidadDeVecinos = 0;

        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = 0; z <= 2; z++) {
                    testQueCasilleroNoSeaNulo(x, y, z, vecinos, casilleroCentral);
                    cantidadDeVecinos++;
                }
            }
        }

        // Si el casillero esta en (2, 2, 2) deberia tener 27 vecinos incluyendose
        Assertions.assertEquals(cantidadDeVecinos, 27);
    }

    // Si es el casillero central izquierdo (2, 2, 1) en un tablero 3x3x3 entonces todos los demas casilleros seran sus vecinos,
    // exceptuando el plano con profundidad 3 (la matriz de vecinos no deberia tener vecinos en z = 0, osea a su
    // izquierda), deberia tener 18 vecinos incluyendose
    @Test
    public void testVecinosDeCasilleroEnElCentroIzquierdo() throws Exception {
        Tablero<Ficha> tablero = new Tablero<Ficha>(3, 3, 3);
        Casillero<Ficha> casilleroCentralIzquierdo = tablero.getCasillero(2, 2, 1);
        Casillero<Ficha>[][][] vecinos = casilleroCentralIzquierdo.getCasillerosVecinos();
        int cantidadDeVecinos = 0;

        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = 0; z <= 2; z++) {
                    if (z == 0) {
                        // Si estamos en el plano izquierdo de vecinos, entonces deberia ser un vecino nulo
                        Assertions.assertNull(vecinos[x][y][z]);
                    } else {
                        testQueCasilleroNoSeaNulo(x, y, z, vecinos, casilleroCentralIzquierdo);
                        cantidadDeVecinos++;
                    }
                }
            }
        }

        // Si el casillero esta en (2, 2, 1) deberia tener 18 vecinos incluyendose
        Assertions.assertEquals(cantidadDeVecinos, 18);
    }

    // Test util, que reutilizamos en varios tests, comprueba que el vecino en las coordenadas (x, y, z) no sea nulo,
    // y que si son las coordenadas (1, 1, 1) este vecino sea el casilleroActual
    public void testQueCasilleroNoSeaNulo(int x, int y, int z, Casillero<Ficha>[][][] vecinos, Casillero<Ficha> casilleroActual) {
        // Comprobamos que el vecino no sea nulo
        Assertions.assertNotNull(vecinos[x][y][z]);

        // Conseguimos el casillero vecino
        Casillero<Ficha> casilleroVecino = vecinos[x][y][z];

        // Comprobamos que el vecino central sea el mismo casillero central
        if ((x == 1)
                && (y == 1)
                && (z == 1)) {
            Assertions.assertEquals(casilleroVecino, casilleroActual);
        }
    }
}
