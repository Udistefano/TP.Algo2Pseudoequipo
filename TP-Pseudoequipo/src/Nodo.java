package tp2Prueba;
 
public class Nodo <Dato> {
	private Dato dato ;
	private Nodo<Dato> siguiente;
	
	public Nodo(Dato dato){
		this.dato = dato;
		this.siguiente = null;
	}

	public Dato devolverDato(){
		return this.dato;
	}
	public Nodo<Dato> devolverSiguiente(){
		return this.siguiente;
	}
	public void cambiarSiguiente(Nodo<Dato> nuevo){
		this.siguiente = nuevo;
	}
		
	
	
}