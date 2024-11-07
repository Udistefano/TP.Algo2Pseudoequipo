package Main;

public class Turno {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Jugador jugador = null;
	private int cantidadDeSubTurnos = 1;
	private int bloqueosRestantes = 0;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	public void incrementarBloqueosRestantes(int cantidadDeBloqueos) {
		this.bloqueosRestantes += cantidadDeBloqueos;
	}
	
	public void terminarTurno() {
		if (this.bloqueosRestantes > 0) {
			this.bloqueosRestantes--;
		}
	}
	
	public void iniciarTurno() {
		this.cantidadDeSubTurnos += 1;
	}
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public boolean estaBloqueado() {
		return this.bloqueosRestantes <= 0;
	}
	
	public int getCantidadDeSubTurnos() {
		return this.cantidadDeSubTurnos;
	}
	
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
