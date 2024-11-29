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

    private static int MARGEN_SUPERIOR = 100;
    private static int MARGEN_ENTRE_TABLEROS = 10;
    
    private static int anchoImagenCompleta = 1000;
    private static int altoImagenCompleta = 500;
    private static int cantidadDeCasillerosEnEjeX = 0;
    private static int cantidadDeCasillerosEnEjeY = 0;
    private static int cantidadDeCasillerosEnEjeZ = 0;
    
    private static int anchoCasillero = 0;
    private static int anchoTablero = 0;
    private static int altoCasillero = 0;
    private static int altoTablero = 0;
    
    private static int[][] matrizImagenCompleta = null;
    private static int[][] matrizTableroIndividual = null;
    
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

        cantidadDeCasillerosEnEjeX = x;
        cantidadDeCasillerosEnEjeY = y;
        cantidadDeCasillerosEnEjeZ = z;

        anchoTablero = (anchoImagenCompleta / cantidadDeCasillerosEnEjeZ) - MARGEN_ENTRE_TABLEROS;
        anchoCasillero = anchoTablero / cantidadDeCasillerosEnEjeX;

        altoTablero = ((altoImagenCompleta - MARGEN_SUPERIOR) / 2);
        altoCasillero = altoTablero / cantidadDeCasillerosEnEjeY;

        matrizImagenCompleta = new int[anchoImagenCompleta][altoImagenCompleta];
        matrizTableroIndividual = new int[anchoTablero][altoTablero];

        imagen = new BufferedImage(anchoImagenCompleta, altoImagenCompleta, BufferedImage.TYPE_INT_RGB);
        
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
        colorearMatrizImagenCompleta();
        escribirImagenCompleta();

        colorearMatrizTablero();
        colorearLineasMatrizTablero();
        escribirTableroVacio();
    }

    /**
     * pre: --
     * post: pintamos la matriz de la imagen completa con el color de fondo, y escribimos la imagen completa
     */
    private static void colorearMatrizImagenCompleta() {
        for (int i = 0; i < anchoImagenCompleta; i++) {
            for (int j = 0; j < altoImagenCompleta; j++) {
                matrizImagenCompleta[i][j] = COLOR_FONDO;
            }
        }
    }

    /**
     * pre: --
     * post: escribe la matriz central a la imagen
     */
    private static void escribirImagenCompleta() {
        for (int i = 0; i < anchoImagenCompleta; i++) {
            for (int j = 0; j < altoImagenCompleta; j++) {
                imagen.setRGB(i, j, matrizImagenCompleta[i][j]);
            }
        }
    }

    /**
     * pre: --
     * post: colorea los casilleros de los tableros del juego con el color de casillero
     */
    private static void colorearMatrizTablero() {
        for (int i = 0; i < anchoTablero; i++) {
            for (int j = 0; j < altoTablero; j++) {
                matrizTableroIndividual[i][j] = COLOR_CASILLERO;
            }
        }
    }

    /**
     * pre: --
     * post: colorea las lineas de los tableros del juego con el color de lineas
     */
    private static void colorearLineasMatrizTablero() {
        for (int i = 0; i < cantidadDeCasillerosEnEjeX; i++) {
            for (int j = 0; j < altoTablero; j++) {
                matrizTableroIndividual[anchoCasillero * i][j] = COLOR_LINEAS;
            }
        }
        for (int i = 0; i < cantidadDeCasillerosEnEjeY; i++) {
            for (int j = 0; j < anchoTablero; j++) {
                matrizTableroIndividual[j][altoCasillero * i] = COLOR_LINEAS;
            }
        }
    }

    /**
     * pre: --
     * post: escribe en la imagen completa la matriz de los tableros del juego vacios de fichas
     */
    public static void escribirTableroVacio() {
        for (int k = 0; k < cantidadDeCasillerosEnEjeZ; k++) {
            for (int i = 0; i < anchoTablero; i++) {
                for (int j = 0; j < altoTablero; j++) {
                    imagen.setRGB(k * (anchoTablero + MARGEN_ENTRE_TABLEROS) + i, MARGEN_SUPERIOR + j,
                                  matrizTableroIndividual[i][j]);
                }
            }
        }
    }

    /**
     * pre:
     * @param rutaImagen no puede ser nulo ni vacio
     * @throws Exception si rutaImagen es nulo o vacio
     * post: lee la imagen en rutaImagen y la retorna
     */
    private static BufferedImage cargarImagen(String rutaImagen) throws Exception {
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(rutaImagen, "Ruta de imagen");

        try {
            return ImageIO.read(new File(generarRutaRelativa(rutaImagen)));
        } catch (Exception e) {
            throw new Exception("Hubo un error al intentar leer la imagen " + rutaImagen);
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
     * @param tablero no puede ser nulo
     * @throws Exception si tablero es nulo
     * post: escribe en la imagen salida, las fichas del tablero
     */
    public static void escribirTableroActualizado(Tablero<Ficha> tablero) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");

        for (int x = 1; x <= tablero.getAncho(); x++) {
            for (int y = 1; y <= tablero.getAlto(); y++) {
                for (int z = 1; z <= tablero.getProfundidad(); z++) {
                    Casillero<Ficha> casillero = tablero.getCasillero(x, y, z);
                    Ficha ficha = casillero.getDato();
                    if (ficha != null) {
                        escribirFicha(casillero, ficha.getColor());
                    } else {
                        quitarFicha(casillero);
                    }
                }
            }
        }

        Bitmap.escribirArchivo();
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
        ValidacionesUtiles.validarCoordenadaDeImagen(x, cantidadDeCasillerosEnEjeX);
        ValidacionesUtiles.validarCoordenadaDeImagen(y, cantidadDeCasillerosEnEjeY);
        ValidacionesUtiles.validarCoordenadaDeImagen(z, cantidadDeCasillerosEnEjeZ);
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(rutaImagen, "Ruta de imagen");

        BufferedImage imagenFicha = null;
        try {
            imagenFicha = cargarImagen(rutaImagen);
        } catch (Exception e) {
            // No es la mejor manera de manejar un error de lectura de imagen, una mejor alternativa
            // seria crear una imagen de X color
            imagenFicha = cargarImagen(RUTA_IMAGEN_POR_DEFECTO);
        }

        // En escribirFicha y quitarFicha le restamos 1 a las coordenadas para que coincidan con las coordenadas del
        // tablero, que recordar que empiezan en 1, en cambio en una imagen empiezan en 0
        
        // Calculamos la posicion de la ficha en la imagen del tablero, lo de la izquierda es para elegir en cual
        // tablero, y lo de la derecha es para elegir en que columna (coordenada X) de ese tablero
        int posX = (z - 1) * (anchoTablero + MARGEN_ENTRE_TABLEROS) + (x - 1) * anchoCasillero;

        // Ponemos un margen de MARGEN_SUPERIOR pixeles desde la parte superior de la imagen y elegimos la fila
        // (coordenada Y)
        int posY = MARGEN_SUPERIOR + (altoCasillero) * (y - 1);

        // Calculamos el desplazamiento para centrar la imagen
        int desplazamientoX = (anchoCasillero - imagenFicha.getWidth()) / 2;
        int desplazamientoY = (altoCasillero - imagenFicha.getHeight()) / 2;

        // Aca ajustamos la posicion de la imagen para que quede centrada
        posX += desplazamientoX;
        posY += desplazamientoY;

        // Validamos que la imagen no se salga de los limites del tablero
        if ((posX + imagenFicha.getWidth() > anchoImagenCompleta) ||
                (posY + imagenFicha.getHeight() > altoImagenCompleta)) {
            throw new Exception("La imagen no cabe en el tablero.");
        }

        // Recorremos la imagen de la ficha, y la escribimos en la imagen del tablero
        for (int i = 0; i < imagenFicha.getWidth(); i++) {
            for (int j = 0; j < imagenFicha.getHeight(); j++) {
                int colorPixel = imagenFicha.getRGB(i, j);
                int pixelX = posX + i;
                int pixelY = posY + j;

                // Si el pixel esta dentro de los limites de la imagen, lo escribimos
                if (pixelEstaEnTablero(pixelX, pixelY)) {
                    imagen.setRGB(pixelX, pixelY, colorPixel);
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
        ValidacionesUtiles.validarCoordenadaDeImagen(x, cantidadDeCasillerosEnEjeX);
        ValidacionesUtiles.validarCoordenadaDeImagen(y, cantidadDeCasillerosEnEjeY);
        ValidacionesUtiles.validarCoordenadaDeImagen(z, cantidadDeCasillerosEnEjeZ);

        // En escribirFicha y quitarFicha le restamos 1 a las coordenadas para que coincidan con las coordenadas del
        // tablero, que recordar que empiezan en 1, en cambio en una imagen empiezan en 0

        // Calculamos la posicion de la ficha en la imagen del tablero, lo de la izquierda es para elegir en cual
        // tablero, y lo de la derecha es para elegir en que columna (coordenada X) de ese tablero
        int posX = (z - 1) * (anchoTablero + MARGEN_ENTRE_TABLEROS) + (x - 1) * anchoCasillero;
        
        // Ponemos un margen de MARGEN_SUPERIOR pixeles desde la parte superior de la imagen y elegimos la fila
        // (coordenada Y)
        int posY = MARGEN_SUPERIOR + (altoCasillero) * (y - 1);

        // Las dimensiones de la ficha colocada (usamos la misma logica de colocar la ficha)
        int anchoImagenFicha = anchoCasillero - MARGEN_ENTRE_TABLEROS;
        int altoImagenFicha = altoCasillero - MARGEN_ENTRE_TABLEROS;
        
        int desplazamientoX = (anchoCasillero - anchoImagenFicha) / 2;
        int desplazamientoY = (altoCasillero - altoImagenFicha) / 2;

        posX += desplazamientoX;
        posY += desplazamientoY;

        // Pintamos el casillero con el color de fondo
        for (int i = 0; i < anchoImagenFicha; i++) {
            for (int j = 0; j < altoImagenFicha; j++) {
                int pixelX = posX + i;
                int pixelY = posY + j;
                
                // Pintamos el pixel con el color de fondo
                if (pixelEstaEnTablero(pixelX, pixelY)) {
                    imagen.setRGB(pixelX, pixelY, COLOR_CASILLERO);
                }
            }
        }

        escribirArchivo();
    }

    /**
     * pre:
     * @param x numero entre 0 y anchoImagenCompleta
     * @param y numero entre 0 y altoImagenCompleta
     * post: retorna true si el pixel esta dentro de los limites del tablero, false en caso contrario
     */
    public static boolean pixelEstaEnTablero(int x, int y) {
        return (x >= 0) &&
                (x < anchoImagenCompleta) &&
                (y >= 0) &&
                (y < altoImagenCompleta);
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

