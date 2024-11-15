package Jugadas;

import Cartas.Carta;
import Main.Casillero;
import Main.Ficha;
import Main.Partida;
import Main.Turno;
import Main.Validacion;

public class JugadaBloquearFicha extends Jugada {
	 //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param carta no puede ser nulo
     * @throws Exception si carta es nulo
     *                   post: inicializa la jugada anular casillero, con la carta pasada por parametro
     */
    public JugadaBloquearFicha(Carta carta) throws Exception {
        super(carta);
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: El casillero debe estar en el tablero
     * @param partida no puede ser nulo
     * @param turnoActual no puede ser nulo
     * @throws Exception si alguno de los parametros es nulo, o si la ficha en el casillero a jugar esta bloqueada
     * post: anula el casillero seleccionado
     */
    @Override
    public void jugar(Partida partida, Turno turnoActual) throws Exception {
        Validacion.validarSiEsNulo(partida, "Partida");
        Validacion.validarSiEsNulo(turnoActual, "Turno");

        System.out.println("\nIngrese las coordenadas del casillero del cual se bloqueara a la ficha en el:");
        Casillero<Ficha> casillero = partida.preguntarCasillero();
        if (!casillero.estaOcupado()) {
            throw new Exception("\nEl casillero " + casillero + " no tiene ninguna ficha para bloquear");
        }
        Validacion.validarSiFichaEstaBloqueada(casillero.getDato());

        casillero.getDato().bloquear();
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}

