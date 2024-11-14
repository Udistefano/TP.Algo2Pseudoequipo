package Estructuras;

public class Lista<T> {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------

	private Nodo<T> primero = null;
	private int longitud = 0;
	private Nodo<T> cursor = null;

	//CONSTRUCTORES -------------------------------------------------------------------------------------------

	/**
	 * pre:
	 * pos: crea una lista vacia
	 */
	public Lista() {
		this.primero = null;
		this.longitud = 0;
		this.cursor = null;
	}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return una cadena con todos los elementos de la lista
	 */
	@Override
	public String toString() {
	    if (this.estaVacia()) {
	        return "Lista vacía";  // Si la lista está vacía, devolvemos un mensaje adecuado
	    }
	    
	    String resultado = "[";  // Iniciamos la cadena con un corchete para indicar el inicio de la lista
	    Nodo<T> actual = this.primero;  // Empezamos desde el primer nodo
	    
	    while (actual != null) {
	        resultado += actual.getDato();  // Añadimos el dato del nodo actual a la cadena
	        
	        if (actual.getSiguiente() != null) {
	            resultado += ", ";  // Si no es el último nodo, añadimos una coma para separar los elementos
	        }
	        
	        actual = actual.getSiguiente();  // Avanzamos al siguiente nodo
	    }
	    
	    resultado += "]";  // Cerramos la representación de la lista con un corchete
	    return resultado;
	}
	
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre:
	 * pos: indica si la Lista tiene algún elemento.
	 */
	public boolean estaVacia() {
		return (this.longitud == 0);
	}

	/**
	 * pre: -
	 * pos: agrega el elemento al final de la Lista, en la posición:
	 *       contarElementos() + 1.
	 */
	public void agregar(T elemento) throws Exception {
		this.agregar(this.getLongitud() + 1, elemento);
	}

	/**
	 * pre: posición pertenece al intervalo: [1, contarElementos() + 1]
	 * pos: agrega el elemento en la posición indicada.
	 */
	public void agregar(int posicion, T elemento) throws Exception {
		validarPosicion(posicion);
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if (posicion == 1) {
			nuevo.setSiguiente( this.primero);
			this.primero = nuevo;
		} else {
			Nodo<T> anterior = this.getNodo(posicion -1);
			nuevo.setSiguiente( anterior.getSiguiente());
			anterior.setSiguiente( nuevo );
		}
		this.longitud++;
	}

	/**
	 * pre : posición pertenece al intervalo: [1, contarElementos()]
	 * post: remueve de la Lista el elemento en la posición indicada.
	 */
	public void remover(int posicion) throws Exception {
		validarPosicion(posicion);
		this.cursor = null;
		Nodo<T> removido = null;
		if (posicion == 1) {
			removido = this.primero;
			this.primero = removido.getSiguiente();
		} else {
			Nodo<T> anterior = this.getNodo(posicion -1);
			removido = anterior.getSiguiente();
			anterior.setSiguiente( removido.getSiguiente());
		}
		this.longitud--;
	}

	/**
	 * pre:
	 * @param valor: un valor de la lista
	 * @throws Exception si el valor no existe en la lista
	 * post: elimina el valor de la lista, y pone el cursor en nulo
	 */
	public void remover(T valor) throws Exception {
		Nodo<T> cursor = this.primero;
		int posicion = 1;
		while (cursor != null) {
			if (cursor.getDato().equals(valor)) {
				remover(posicion);
				return;
			}
			cursor = cursor.getSiguiente();
			posicion++;
		}
		throw new Exception("El valor '" + valor + "' no existe");
	}

	/**
	 * pre : posición pertenece al intervalo: [1, contarElementos()]
	 * pos: devuelve el dato de la posicion
	 */
	public T obtener(int posicion) throws Exception {
		validarPosicion(posicion);
		return this.getNodo(posicion).getDato();
	}

	/**
	 * pre:
	 * @param dato con lo que buscamos identificar algun elemento de la lista
	 * @return el elemento de la lista, el cual fue identificado con el dato
	 * @throws Exception si no se logro encontrar ningun elemento de la lista, identificado con dato
	 */
	public T obtener(Object dato) throws Exception {
		Nodo<T> cursor = this.primero;
		while (cursor != null) {
			if (cursor.getDato().equals(dato)) {
				return cursor.getDato();
			}
			cursor = cursor.getSiguiente();
		}
		throw new Exception("No se encontro ningun nodo con ese valor!");
	}

	/**
	 * pre : posición pertenece al intervalo: [1, contarElementos()]
	 * pos: cambia el elemento de la posicion
	 */
	public void cambiar(T elemento, int posicion) throws Exception {
		validarPosicion(posicion);
		this.getNodo(posicion).setDato(elemento);
	}

	/**
	 * pre: --
	 * post: deja el cursor de la Lista preparado para hacer un nuevo
	 *       recorrido sobre sus elementos.
	 */
	public void iniciarCursor() {
		this.cursor = null;
	}

	/**
	 * pre : se ha iniciado un recorrido (invocando el método
	 *       iniciarCursor()) y desde entonces no se han agregado o
	 *       removido elementos de la Lista.
	 * post: mueve el cursor y lo posiciona en el siguiente elemento
	 *       del recorrido.
	 *       El valor de retorno indica si el cursor quedó posicionado
	 *       sobre un elemento o no (en caso de que la Lista esté vacía o
	 *       no existan más elementos por recorrer.)
	 */
	public boolean avanzarCursor() {
		if (this.cursor == null) {
			this.cursor = this.primero;
		} else {
			this.cursor = this.cursor.getSiguiente();
		}

		/* pudo avanzar si el cursor ahora apunta a un nodo */
		return (this.cursor != null);
	}

	/**
	 * pre : el cursor está posicionado sobre un elemento de la Lista,
	 *       (fue invocado el método avanzarCursor() y devolvió true)
	 * post: devuelve el elemento en la posición del cursor.
	 *
	 */
	public T obtenerCursor() {
		T elemento = null;
		if (this.cursor != null) {
			elemento = this.cursor.getDato();
		}
		return elemento;
	}

	/**
	 * pre:
	 * @param valor: -
	 * post: devuelve verdadero si el valor existe en la lista
	 */
	public boolean existe(T valor) {
		Nodo<T> cursor = this.primero;
		while (cursor != null) {
			if (cursor.getDato().equals(valor)) {
				return true;
			}
			cursor = cursor.getSiguiente();
		}
		return false;
	}

	/**
	 * pre:
	 * @param valor: -
	 * post: devuelve la cantidad de apariciones del valor en la lista
	 */
	public int contarOcurrencias(T valor) {
		Nodo<T> cursor = this.primero;
		int cantidadDeOcurrencias = 0;
		while (cursor != null) {
			if (cursor.getDato().equals(valor)) {
				cantidadDeOcurrencias++;
			}
			cursor = cursor.getSiguiente();
		}
		return cantidadDeOcurrencias;
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * post: devuelve la cantidad de elementos que tiene la Lista.
	 */
	public int getLongitud() {
		return this.longitud;
	}

	/**
	 * pre : posición pertenece al intervalo: [1, contarElementos()]
	 * post: devuelve el nodo en la posición indicada.
	 */
	private Nodo<T> getNodo(int posicion) throws Exception {
		validarPosicion(posicion);
		Nodo<T> actual = this.primero;
		for(int i = 1; i < posicion; i++) {
			actual = actual.getSiguiente();
		}
		return actual;
	}

	/**
	 * pre: posicion
	 * post: valida que la posicion este entre 1 y this.longitud + 1
	 */
	private void validarPosicion(int posicion) throws Exception {
		if ((posicion < 1) ||
				(posicion > this.longitud + 1)) {
			throw new Exception("La posicion debe estar entre 1 y tamaño + 1");
		}
	}

	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}