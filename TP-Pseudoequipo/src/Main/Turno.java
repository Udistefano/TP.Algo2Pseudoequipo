package Main;

public class Turno {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private int cantidadDeSubturnos = 0;
	private int bloqueosRestantes = 0;
	private Jugador jugador = null;

	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre:
	 * @param jugador no puede ser nulo
	 * @throws Exception si jugador es nulo
	 * post: inicializa el turno con el jugador pasado por parametro, con un subturno y ningun bloqueo
	 *       restante
	 */
	public Turno(Jugador jugador) throws Exception {
		ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
		this.jugador = jugador;
	}
	
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	/**
     * pre:
     * @param obj con el cual comparar a turno actual
     * @return verdadero si obj equivale a turno actual, falso si no
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
		}
        if (obj == null) {
            return false;
		}
		if (obj instanceof Jugador) {
			Jugador other = (Jugador) obj;
			return this.jugador.equals(other);
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Turno other = (Turno) obj;
		return this.jugador.equals(other.jugador);
    }
	
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param cantidadDeBloqueos no puede ser negativo
	 * post: le suma a la cantidad de bloqueos restantes del turno, la cantidad pasada por parametro
	 */
	public void incrementarBloqueosRestantes(int cantidadDeBloqueos) throws Exception {
	   ValidacionesUtiles.validarSiNumeroEsMenorAUno(cantidadDeBloqueos, "Cantidad de bloqueos");
		this.bloqueosRestantes += cantidadDeBloqueos;
	}

	/**
	 * pre: --
	 * post: si al turno le quedan bloqueos restantes, le resta uno
	 */
	public void terminarTurno() {
		if (bloqueosRestantes > 0) {
			bloqueosRestantes--;
		}
	}

	/**
	 * pre: --
	 * post: incrementa en una la cantidad de subturnos del turno
	 */
	public void iniciarTurno() {
		agregarSubturno();
	}

	/**
	 * pre: --
	 * post: incrementa en una la cantidad de subturnos del turno
	 */
	public void agregarSubturno() {
		cantidadDeSubturnos++;
	}

	/**
	 * pre: --
	 * @return verdadero si al turno le quedan subturnos disponibles, falso si no le quedan
	 */
	public boolean haySubturnos() {
		return this.cantidadDeSubturnos > 0;
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return el jugador del turno
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * pre: --
	 * @return verdadero si al turno le quedan bloqueos restantes, falso si no
	 */
	public boolean estaBloqueado() {
		return bloqueosRestantes > 0;
	}

	/**
	 * pre: --
	 * @return la cantidad de subturnos
	 */
	public int getCantidadDeSubturnos() {
		return cantidadDeSubturnos;
	}

	/**
	 * pre: --
	 * post: utiliza un subturno
	 */
	public void utilizarSubturno() {
		cantidadDeSubturnos--;
	}

	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}