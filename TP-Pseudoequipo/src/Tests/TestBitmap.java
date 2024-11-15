package Tests;
import Main.Bitmap;
import Main.Color;
//import Main.ColorFichasRojo;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class TestBitmap {
    public static void main(String[] args) throws Exception {
        // Crear un objeto de la clase Bitmap con dimensiones de 5x5 casilleros y 3 tableros
        Bitmap bitmap = new Bitmap(3, 3, 3);
        // Crear la imagen
        bitmap.crearImagen();
        bitmap.colocarFicha(2, 2, 2, "colorRojo.bmp");
        bitmap.colocarFicha(2, 1, 2, "colorAzul.bmp");
        bitmap.colocarFicha(1, 3, 2, "colorAzul.bmp");
        bitmap.colocarFicha(2, 3, 2, "colorRojo.bmp");
        bitmap.colocarFicha(1, 3, 3, "colorNaranja.bmp");
        
        bitmap.escribirArchivo();
    }
}

