package Jugadas;

import Cartas.Carta;
import Estructuras.ListaSimple;
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
                String nombreBuscado = Teclado.preguntarNombreDeJugador("\nIngrese el nombre del jugador al que" +
                                                                " hacerle perder un turno: ");
                ListaSimple<Jugador> jugadores = partida.getJugadores();
                Jugador jugadorBuscado = null;
                // TODO: hacer un jugadores.obtener(nombre) en jugadaPerderTurno y jugadaEliminarCartasDelJugador
                jugadores.iniciarCursor();
                while(jugadores.avanzarCursor()) {
                    if (jugadores.obtenerCursor().getNombre().equals(nombreBuscado)) {
                        jugadorBuscado = jugadores.obtenerCursor();
                    }
                }
                if (jugadorBuscado == null) {
                    throw new Exception("No se hallo el jugador " + nombreBuscado + " al cual perderle el turno");
                }
                
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