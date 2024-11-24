package Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bitmap {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    
    private static String RUTA_RELATIVA_IMAGENES = "./src/Imagenes/";
    private static String RUTA_IMAGEN_POR_DEFECTO = RUTA_RELATIVA_IMAGENES + "colorVerde.bmp";
    private static String NOMBRE_ARCHIVO_SALIDA = "salida.bmp";
	private static int COLOR_FONDO = 7368816;
	private static int COLOR_CASILLERO = 4730480;
	private static int COLOR_LINEAS = 16777215;
    
    private static int ancho = 1000;
    private static int alto = 500;
    private static int dimensionX = 0;
    private static int dimensionY = 0;
    private static int dimensionZ = 0;
    
    private static int dimensionCasilleroX = 0;
    private static int dimensionTableroX = 0;
    private static int dimensionCasilleroY = 0;
    private static int dimensionTableroY = 0;
    
    private static int[][] matrizCentral = null;
    private static int[][] matrizTablero = null;
    
    private static BufferedImage imagen = null;
    
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param x no puede ser menor a 1
     * @param y no puede ser menor a 1
     * @param z no puede ser menor a 1
     * @throws Exception cuando alguno de los parametros es menor a 1
     * post: inicializa el bitmap, con los parametros x, y, z, como las dimensiones
     */
    public static void inicializar(int x, int y, int z) throws Exception {
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(x, "X");
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(y, "Y");
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(z, "Z");

        dimensionX = x;
        dimensionY = y;
        dimensionZ = z;
        dimensionTableroX = (ancho / dimensionZ) - 10;
        dimensionCasilleroX = dimensionTableroX / dimensionX;

        dimensionTableroY = ((alto - 100) / 2);
        dimensionCasilleroY = dimensionTableroY / dimensionY;

        matrizCentral = new int[ancho][alto];
        matrizTablero = new int[dimensionTableroX][dimensionTableroY];

        imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        crearImagen();
        escribirArchivo();
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * post: crea la imagen de los tableros del juego, y la exporta al archivo salida
     */
    public static void crearImagen() {
        colorMatrizCentral();
        escribirImagen();

        colorearMatrizTablero();
        colorearLineasMatrizTablero();
        escribirTablero();
    }

    /**
     * pre: --
     * post: colorea la matriz central
     */
    private static void colorMatrizCentral() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                matrizCentral[i][j] = COLOR_FONDO;
            }
        }
    }

    /**
     * pre: --
     * post: escribe la matriz central a la imagen
     */
    private static void escribirImagen() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                imagen.setRGB(i, j, matrizCentral[i][j]);
            }
        }
    }

    /**
     * pre: --
     * post: colorea los casilleros de los tableros del juego
     */
    private static void colorearMatrizTablero() {
        for (int i = 0; i < dimensionTableroX; i++) {
            for (int j = 0; j < dimensionTableroY; j++) {
                matrizTablero[i][j] = COLOR_CASILLERO;
            }
        }
    }

    /**
     * pre: --
     * post: colorea las lineas de los tableros del juego
     */
    private static void colorearLineasMatrizTablero() {
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionTableroY; j++) {
                matrizTablero[dimensionCasilleroX * i][j] = COLOR_LINEAS;
            }
        }
        for (int i = 0; i < dimensionY; i++) {
            for (int j = 0; j < dimensionTableroX; j++) {
                matrizTablero[j][dimensionCasilleroY * i] = COLOR_LINEAS;
            }
        }
    }

    /**
     * pre:
     * @param rutaImagen no puede ser nulo ni vacio
     * @throws Exception si rutaImagen es nulo o vacio
     * post: lee la imagen en rutaImagen
     */
    private static BufferedImage cargarImagen(String rutaImagen) throws Exception {
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(rutaImagen, "Ruta de imagen");

        try {
            return ImageIO.read(new File(generarRutaRelativa(rutaImagen)));  // Cargar la imagen desde el archivo
        } catch (Exception e) {
            throw new Exception("Hubo un error al intentar leer la imagen " + rutaImagen);
        }
    }

    /**
     * pre: --
     * post: escribe en la imagen los tableros del juego
     */
    private static void escribirTablero() {
        for (int k = 0; k < dimensionZ; k++) {
            for (int i = 0; i < dimensionTableroX; i++) {
                for (int j = 0; j < dimensionTableroY; j++) {
                    imagen.setRGB(k * (dimensionTableroX + 10) + i, 100 + j, matrizTablero[i][j]);
                }
            }
        }
    }

    /**
     * pre: --
     * post: escribe la imagen del juego al archivo NOMBRE_ARCHIVO_SALIDA
     */
    public static void escribirArchivo() {
        try {
            File archivo = new File(generarRutaRelativa(NOMBRE_ARCHIVO_SALIDA));
            ImageIO.write(imagen, "bmp", archivo);
        } catch (Exception e) {
            System.out.println("Hubo un error al intentar escribir la imagen del juego al archivo " + NOMBRE_ARCHIVO_SALIDA);
            e.printStackTrace();
        }
    }

    /**
     * pre:
     * @param casillero no puede ser nulo
     * @param color no puede ser nulo
     * @throws Exception si alguno de los parametros es nulo
     * post: escribe al casillero la ficha con el color del jugador en la imagen salida
     */
    public static void escribirFicha(Casillero<Ficha> casillero, Color color) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(color, "Color");
        Bitmap.escribirFicha(casillero.getX(), casillero.getY(), casillero.getZ(), Color.getRutaDeImagen(color));
    }

    /**
     * pre:
     * @param x no puede ser menor a 1
     * @param y no puede ser menor a 1
     * @param z no puede ser menor a 1
     * @param rutaImagen no puede ser nulo ni vacio
     * @throws Exception si alguno de los parametros es invalido
     * post: coloca en las coordenadas x, y, z del tablero del juego, la ficha que se lee de la imagen rutaImagen
     */
    public static void escribirFicha(int x, int y, int z, String rutaImagen) throws Exception {
        ValidacionesUtiles.validarCoordenadaDeImagen(x, dimensionX);
        ValidacionesUtiles.validarCoordenadaDeImagen(y, dimensionY);
        ValidacionesUtiles.validarCoordenadaDeImagen(z, dimensionZ);
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(rutaImagen, "Ruta de imagen");

        // Cargar la imagen desde el archivo
        BufferedImage imagenFicha = null;
        try {
            imagenFicha = cargarImagen(rutaImagen);
        } catch (Exception e) {
            // No es la mejor manera de manejar un error de lectura de imagen, una mejor alternativa
            // seria crear una imagen de X color
            imagenFicha = cargarImagen(RUTA_IMAGEN_POR_DEFECTO);
        }

        // Calcular las coordenadas donde se colocará la imagen
        int anchoImagen = imagenFicha.getWidth();
        int altoImagen = imagenFicha.getHeight();

        // Posición del casillero (izquierda superior) en el tablero
        int posX = (z - 1) * (dimensionTableroX + 10) + (x - 1) * dimensionCasilleroX;
        int posY = 100 + (dimensionCasilleroY) * (y - 1);

        // Calcular el desplazamiento para centrar la imagen
        int desplazamientoX = (dimensionCasilleroX - anchoImagen) / 2;
        int desplazamientoY = (dimensionCasilleroY - altoImagen) / 2;

        // Ajustar la posición de la imagen para que quede centrada
        posX += desplazamientoX;
        posY += desplazamientoY;

        // Asegurarse de que la imagen no se salga de los límites del tablero
        if ((posX + anchoImagen > ancho) ||
                (posY + altoImagen > alto)) {
            throw new Exception("La imagen no cabe en el tablero.");
        }

        // Colocar los píxeles de la imagen en la matriz del tablero
        for (int i = 0; i < anchoImagen; i++) {
            for (int j = 0; j < altoImagen; j++) {
                int colorPixel = imagenFicha.getRGB(i, j); // Obtener el color del píxel de la imagen
                int pixelX = posX + i;
                int pixelY = posY + j;

                // Asegurarse de que los píxeles estén dentro de los límites del tablero
                if (pixelX >= 0 && pixelX < ancho && pixelY >= 0 && pixelY < alto) {
                    imagen.setRGB(pixelX, pixelY, colorPixel); // Colocar el píxel de la imagen en el tablero
                }
            }
        }

        Bitmap.escribirArchivo();
    }

    /**
     * pre:
     * @param casillero no puede ser nulo
     * @throws Exception si casillero es nulo
     * post: quita de la imagen salida, la ficha del casillero
     */
    public static void quitarFicha(Casillero<Ficha> casillero) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        Bitmap.quitarFicha(casillero.getX(), casillero.getY(), casillero.getZ());
    }

    /**
     * pre:
     * @param x no puede ser menor a uno
     * @param y no puede ser menor a uno
     * @param z no puede ser menor a uno
     * @throws Exception si algun parametro es menor a uno
     * post: quita de la imagen salida, la ficha del casillero con coordenadas (x, y, z)
     */
    public static void quitarFicha(int x, int y, int z) throws Exception {
        ValidacionesUtiles.validarCoordenadaDeImagen(x, dimensionX);
        ValidacionesUtiles.validarCoordenadaDeImagen(y, dimensionY);
        ValidacionesUtiles.validarCoordenadaDeImagen(z, dimensionZ);

        // Calcular las coordenadas y dimensiones de la ficha
        int posX = (z - 1) * (dimensionTableroX + 10) + (x - 1) * dimensionCasilleroX;
        int posY = 100 + (dimensionCasilleroY) * (y - 1);

        // Las dimensiones de la ficha colocada (aquí usamos la misma lógica de colocar la ficha)
        int anchoImagen = dimensionCasilleroX - 10; // Esto es un supuesto: si la ficha cubre toda la casilla, su tamaño es el de la casilla
        int altoImagen = dimensionCasilleroY - 10;  // Lo mismo para la altura

        int desplazamientoX = (dimensionCasilleroX - anchoImagen) / 2;
        int desplazamientoY = (dimensionCasilleroY - altoImagen) / 2;

        posX += desplazamientoX;
        posY += desplazamientoY;

        // Restaurar las casillas en la región afectada por la ficha
        for (int i = 0; i < anchoImagen; i++) {
            for (int j = 0; j < altoImagen; j++) {
                int pixelX = posX + i;
                int pixelY = posY + j;

                // Restaurar el color original de la casilla (color de fondo)
                if (pixelX >= 0 && pixelX < ancho && pixelY >= 0 && pixelY < alto) {
                    imagen.setRGB(pixelX, pixelY, COLOR_CASILLERO);  // Asumimos que el color original es el color de fondo
                }
            }
        }

        colorearLineasMatrizTablero();
        escribirArchivo();
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre:
     * @param nombreDeArchivo no puede ser nulo ni vacio
     * @return una cadena del formato RUTA_RELATIVA_IMAGENES/nombreDeArchivo
     * @throws Exception si nombreDeArchivo es invalido
     */
    private static String generarRutaRelativa(String nombreDeArchivo) throws Exception {
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(nombreDeArchivo, "Nombre de archivo");
        return RUTA_RELATIVA_IMAGENES + nombreDeArchivo;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}

