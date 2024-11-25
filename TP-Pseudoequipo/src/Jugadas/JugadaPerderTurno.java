package Jugadas;

import Cartas.Carta;
import Main.*;

/**
 * El jugador decide a quien le hace perder el turno
 */
public class JugadaPerderTurno extends Jugada {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param carta no puede ser nula
     * @throws Exception si la carta es nula
     */
    public JugadaPerderTurno(Carta carta) throws Exception {
        super(carta);
    }

//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre:
     * @param partida no puede ser nulo
     * @param turnoActual no puede ser nulo
     * @throws Exception si algun parametro es nulo
     * post: le pierde un turno al jugador que se le pregunta al jugador actual
     */
    @Override
    public void jugar(Partida partida, Turno turnoActual) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(partida, "Partida");
        ValidacionesUtiles.validarSiEsNulo(turnoActual, "Turno");

        boolean esNombreInvalido = true;
        do {
            try {
                System.out.print("\nIngrese el nombre del jugador al que hacerle perder un turno: ");
                String nombreBuscado = Teclado.leerCadena();
                Jugador jugadorBuscado = partida.getJugadores().obtener(nombreBuscado);
                Turno turno = partida.getTurnos().obtener(jugadorBuscado);

                turno.incrementarBloqueosRestantes(1);
                
                System.out.println("\nEl jugador " + jugadorBuscado + " ha perdido un turno");
                esNombreInvalido = false;
            } catch (Exception e) {
                UtilesVarios.mostrarError(e);
            }
        } while (esNombreInvalido);
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}