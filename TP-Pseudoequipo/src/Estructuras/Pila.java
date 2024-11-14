package Estructuras;

public class Pila<T> {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------

	private Nodo<T> tope = null;
	private int tamanio = 0;

	//CONSTRUCTORES -------------------------------------------------------------------------------------------

	/**
	 * pre:
	 * post: inicializa la pila vacia para su uso
	 */
	public Pila() {
		this.tope = null;
		this.tamanio = 0;
	}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	
	// Método toString para imprimir los elementos de la pila
    @Override
    public String toString() {
        if (this.estaVacia()) {
            return "Pila vacía";
        }

        String resultado = "";
        Nodo<T> actual = this.tope;

        // Recorremos los nodos de la pila desde el tope hasta el fondo
        while (actual != null) {
            resultado += actual.getDato();  // Agregamos el dato del nodo a la cadena
            if (actual.getSiguiente() != null) {
                resultado += " , ";  // Si no es el último nodo, agregamos una flecha
            } else {
            	resultado += "";
            }
            actual = actual.getSiguiente();  // Avanzamos al siguiente nodo
        }

        return resultado;
    }
    
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/*
	 * post: indica si la cola tiene algún elemento.
	 */
	public boolean estaVacia() {
		return (this.tamanio == 0);
	}

	/*
	 * pre: el elemento no es vacio
	 * post: agrega el elemento a la pila
	 */
	public void apilar(T elemento) {
		Nodo<T>nuevo = new Nodo<T>(elemento);
		nuevo.setSiguiente(this.tope);
		this.tope = nuevo;
		this.tamanio++;
	}

	/*
	 * pre: el elemento no es vacio
	 * post: agrega el elemento a la pila
	 */
	public void apilar(Lista<T> lista) {
		//validar
		lista.iniciarCursor();
		while (!lista.avanzarCursor()) {
			this.apilar(lista.obtenerCursor());
		}
	}

	/*
	 * pre :
	 * post: devuelve el elemento en el tope de la pila y achica la pila en 1.
	 */
	public T desapilar() {
		T elemento = null;
		if (!this.estaVacia()) {
			elemento = this.tope.getDato();
			this.tope = this.tope.getSiguiente();
			this.tamanio--;
		}
		return elemento;
	}

	/**
	 * pre: -
	 * post: devuelve el elemento en el tope de la pila (solo lectura)
	 */
	public T obtener() {
		T elemento = null;
		if (!this.estaVacia()) {
			elemento = this.tope.getDato();
		}
		return elemento;
	}

	/*
	 * post: devuelve la cantidad de elementos que tiene la cola.
	 */
	public int contarElementos() {
		return this.tamanio;
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	


}
