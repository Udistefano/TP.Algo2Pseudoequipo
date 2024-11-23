package Tests;
import Main.Bitmap;

public class TestBitmap {
    public static void main(String[] args) throws Exception {
        // Crear un objeto de la clase Bitmap con dimensiones de 5x5 casilleros y 3 tableros
        Bitmap.inicializar(3, 3, 3);
        // Crear la imagen
        Bitmap.crearImagen();
        Bitmap.escribirFicha(2, 2, 2, "colorRojo.bmp");
        Bitmap.escribirFicha(2, 1, 2, "colorAzul.bmp");
        Bitmap.escribirFicha(1, 3, 2, "colorAzul.bmp");
        Bitmap.escribirFicha(2, 3, 2, "colorRojo.bmp");
        Bitmap.escribirFicha(1, 3, 3, "colorNaranja.bmp");
        //Casillero casillero = new Casillero(1 , 3, 3);
        Bitmap.quitarFicha(1, 3, 3);
        Bitmap.escribirArchivo();
    }
}

