 

public enum Color  {
    LINEAS(16777215),
    CASILLERO( 4730480),
    ROJO(16711680),
    //JUGADOR1( ),
    //JUGADOR2( ), 
    //JUGADOR3( ), 
    GRIS( 7368816),
    NEGRO(0);

private final int valorRGB;

Color(int valorRGB){
    this.valorRGB = valorRGB;
}
public int getRGB() {
    return valorRGB;
}

}
