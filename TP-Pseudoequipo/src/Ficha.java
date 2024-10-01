package tp2Prueba;

public class Ficha {
	private Jugador jugador= null;
	private boolean estado = false ;

	/**
	 * 
	 * @param jugador
	 * @throws Exception
	*/
	public Ficha(Jugador jugador)throws Exception {
		this.jugador = jugador;
		this.estado = true;
}
}
