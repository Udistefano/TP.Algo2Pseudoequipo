package Main;

public class Turno {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private int cantidadDeSubturnos = 1;
	private int bloqueosRestantes = 0;
	private Jugador jugador = null;

	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param jugador no puede ser nulo
	 * @throws Exception si jugador es nulo
	 * post: inicializa el turno con el jugador pasado por parametro, con un subturno y ningun bloqueo
	 *       restante
	 */
	public Turno(Jugador jugador) throws Exception {
		if (jugador == null) {
			throw new Exception("El jugador no puede ser nulo");
		}
		this.jugador = jugador;
	}

	/**
	 * pre:
	 * @param cantidadDeBloqueos no puede ser negativo
	 * post: le suma a la cantidad de bloqueos restantes del turno, la cantidad pasada por parametro
	 */
	public void incrementarBloqueosRestantes(int cantidadDeBloqueos) throws Exception {
		// FIXME: la cantidad de bloqueos debe ser positiva?
//        if (cantidadDeBloqueos < 0) {
//            throw new Exception("La cantidad de bloqueos no puede ser negativa");
//        }
		this.bloqueosRestantes += cantidadDeBloqueos;
	}

	/**
	 * pre: --
	 * post: si al turno le quedan bloqueos restantes, le resta uno
	 */
	public void terminarTurno() {
		if (this.bloqueosRestantes > 0) {
			this.bloqueosRestantes--;
		}
	}

	/**
	 * pre: --
	 * post: incrementa en una la cantidad de subturnos del turno
	 */
	public void iniciarTurno() {
		this.agregarSubturno();
	}

	/**
	 * pre: --
	 * post: incrementa en una la cantidad de subturnos del turno
	 */
	public void agregarSubturno() {
		this.cantidadDeSubturnos++;
	}

	/**
	 * pre: --
	 * @return verdadero si al turno le quedan subturnos disponibles, falso si no le quedan
	 */
	public boolean haySubturnos() {
		// FIXME: el profe cantidadDeSubturnos >= 0, no deberia ser > 0 ?????
		return this.cantidadDeSubturnos >= 0;
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return el jugador del turno
	 */
	public Jugador getJugador() {
		return this.jugador;
	}

	/**
	 * pre: --
	 * @return verdadero si al turno le quedan bloqueos restantes, falso si no
	 */
	public boolean estaBloqueado() {
		return this.bloqueosRestantes <= 0;
	}

	/**
	 * pre: --
	 * @return la cantidad de subturnos
	 */
	public int getCantidadDeSubturnos() {
		return this.cantidadDeSubturnos;
	}

	/**
	 * pre: --
	 * post: utiliza un subturno
	 */
	public void utilizarSubturno() {
		this.cantidadDeSubturnos--;
	}

	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
