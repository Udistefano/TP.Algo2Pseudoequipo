package tp2Prueba;

public class Jugador {
	private String nombre = "";
	private Lista<Ficha> fichas = null;
	private Lista mano = null ;

	/**
	 * 
	*/
	public Jugador(String nombre,int C){
		this.nombre = nombre;
		//iniciar lista de fichas
		this.mano = new lista(C);
		
	} /*adsfasdfad*/
	public void jugar_ficha(ficha, coordenadas){
	}

	public void mover_ficha(origen, destino){

	}
	public void usar_carta(carta){

	}
	public void tomar_cartas(cartas){

	}
	public Carta devolver_cartas(cartas){

	}
}
