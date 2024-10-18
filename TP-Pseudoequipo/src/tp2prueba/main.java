package tp2prueba;


public class main {
    public static void main(String[] args) throws Exception {
        public static final int MAX_C = 10;
        //TestDeDado test = new TestDeDado();
        //inicio jugadores 
        
        Tablero<Ficha> tablero = new Tablero<Ficha>(ConstantesGlobales.ancho,ConstantesGlobales.alto,ConstantesGlobales.profundidad );
        Lista<Jugador> jugadores = new Lista<Jugador>(); 
        Mazo mazo = new Mazo(); 
        Dado dado = new Dado();
        Juego tateti = new Juego(tablero, jugadores, mazo, dado);
        tateti.iniciarJuego();
        
    }
}
