package Main;

import Cartas.Carta;
import Estructuras.Lista;
import Estructuras.Cola;


public class Jugador {
	
	
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
	private String nombre = null;
    private char simbolo = 0;
    private Lista<Ficha> fichas = null;
    public Lista<Carta> mano = null;

 
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	/**
	 * pre:
	 * @param simbolo no puede ser vacio
	 * @throws Exception si simbolo es vacio
	 * post: inicializa una ficha con el simbolo pasado por parametro, y jugador nulo
	 */
    public Jugador(char simbolo, String nombre) {
    	this.nombre = nombre;
        this.simbolo = simbolo;
        this.fichas = new Lista<Ficha>();
        this.mano = new Lista<Carta>();

    }  
	/**
	 * pre:
	 * @param cacillero casillero
	 * @throws  
	 * post: 
	 */
    public void jugarFicha(Casillero<Ficha> casillero ) throws Exception{ 
    	if(casillero.estaOcupado()) {
    		throw new Exception("Casillero ocupado");
    	}else { 
    		Ficha nuevaFicha = new Ficha(this);
    		casillero.setDato(nuevaFicha);  
    	
    }  
    }
	/**
	 * pre:
	 * @param tablero: tablero de juego 
	 * @param casillero: 
	 * @throws Exception 
	 * @throws  
	 * post: 
	 */
    public void moverFicha( Casillero<Ficha>  casillero) throws Exception { 
    	if(casillero.getDato().getjugador() == this) {
    		//mover a vecino
    		Movimiento movimiento = null;//solicitar movimiento?
    		if(casillero.existeElVecino(movimiento) && !casillero.getCasilleroVecino(movimiento).estaOcupado()){
    			casillero.setCasilleroVecino(casillero.setCasilleroVecino(movimiento), simbolo, simbolo, simbolo);;
    		}
    		
    			
    		
    	}
    } 
	/**
	 * pre:
	 * @param 
	 * @throws  
	 * post: 
	 */
    public void usarCarta(){  
    	
    } 
	/**
	 * pre:
	 * @param 
	 * @throws  
	 * post: 
	 */
    public void tomar_cartas(Carta carta) throws Exception {
    	mano.agregar(carta);
    }  
    /**
	 * pre:
	 * @param 
	 * @throws  
	 * post: 
	 */
    public TipoDeCarta devolveCartas() {
    	//implementar seleccionar carta
		return this.mano.obtenerCursor().getTipo(); 
    	
    }
    
    // Soy ulises, creo que esto esta bien implementado, cualquier cosa mandenme
    /**
     * pre: metodo para agregar a su lista fichas cuando se usa la carta para cambiar color de su ficha
     * @param ficha
     * @throws Exception
     * post: Agrega la ficha que cambió de color a su lista
     */
    public void agregarFicha(Ficha ficha) throws Exception {
    	if(ficha == null) {
    		throw new Exception("La ficha no puede ser null");
    	}
    	this.fichas.agregar(ficha);
    }



    /**
	 * pre:
	 * @param 
	 * @throws  
	 * post: 
	 */
    public char getSimbolo(){
    	return this.simbolo;
    }
	public String getNombre() {
		return nombre;
	}
    
    
    
}
