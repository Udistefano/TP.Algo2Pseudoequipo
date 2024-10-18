package tp2prueba;

public class Jugador {
    private String nombre = "";
    private Lista<Ficha> fichas = null;
    private Lista<Carta> mano = null;

 
    public Jugador(String nombre, int C) {
        this.nombre = nombre; 
        this.fichas = new Lista<Ficha>();
        this.mano = new Lista<Carta>();
     }    
/**
     *pre: 
     * @param tablero: tda tablero 
     * @param coordenada: verctor int con coordenadas [x,y,z]
     */
    public void jugarFicha(Tablero<Ficha> tablero,int[] coordenada) throws Exception{
        //jugada necesita tablero y crear una nueva ficha
        this.fichas.agregar(new Ficha( this.nombre.charAt(1)));  
        tablero.agregar(coordenada[0], coordenada[1], coordenada[2], fichas.obtener(fichas.getLongitud()));
    }

    /**pre: 
     * @param tablero: tda tablero 
     * @param origen: verctor int con coordenadas [x,y,z]
     * @param destino: verctor int con coordenadas[x,y,z]
     */
    public void moverFicha(Tablero tablero) throws Exception{
        //hayFichaEnOrigen
        int[] origen = null;
        //int[] destino
        while (!tablero.validarOrigen(origen)){
            
        }
        if(tablero.valido){
            //relacion?
            //sacar ficha origen 
            //poner ficha destino
        }else{
            throw new Exception("No existe ficha en la posicion de origen");
        }
    } 

    /**pre: 
    * @param tablero: tda tablero 
    * @param coordenada: verctor int con coordenadas [x,y,z]
    
    */
    public void tomarCartas(int cantidad, Mazo mazo) throws Exception{
      for(int i = cantidad; i < cantidad && this.mano.getLongitud() < ConstantesGlobales.MAX_C ;i++) {
        
      }
    }

    /**pre:  
    *pos:
    */
    public Carta devolverCartas() throws Exception{
        //seleccionar carta
        return this.mano.obtenerCursor();
    }
}
