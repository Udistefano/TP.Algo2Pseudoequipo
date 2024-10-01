package tp2Prueba;

public class Lista<Dato> {
	private Nodo<Dato> inicio = null;
	private Nodo<Dato> actual = null;
	private int cantidad = 0;
 	
	/**
	 * 
	 * @param cantidad
	 * @throws Exception
	*/
	public Lista(int cantidad)throws Exception{
		if(this.inicio != null){
			throw new Exception("lista ya iniciada");
		}else{ 
			this.inicio  = new Nodo<Dato>(null);
			this.actual = inicio;
			this.cantidad = 1;
			for(int i = 1; i < cantidad ; i++){
				agregarNodo(null);
			}  
		} 
	}

	/**
	 * pre:dato para inicialiar
	 * @param inicio:
	 * @param actual:
	*/
	public Lista(Dato dato)throws Exception{
		if (inicio == null) {
			this.inicio = new Nodo<Dato>(dato);
			this.cantidad = 1;
		}else{
			throw new Exception("lista ya iniciada");
		}
	}
	private void agregarNodo(Dato dato){
		actual = inicio;
		irAlUltimo();
		actual.cambiarSiguiente(new Nodo(dato));

	} 
	private void irAlUltimo(){
		actual = inicio;
		while (actual.devolverSiguiente() != null) {
			actual = actual.devolverSiguiente();
		}

	}
}
