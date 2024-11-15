package Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bitmap {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    private static final String RUTA_RELATIVA_IMAGENES = "./src/Imagenes/";

    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private int ancho = 0;
    private int x = 0;
    private int alto = 0;
    private int y = 0;
    private int z = 0;

    private int dimensionCasilleroX = 0;
    private int dimensionTableroX = 0;
    private int dimensionCasilleroY = 0;
    private int dimensionTableroY = 0;

    private int[][] matrizCentral = null;
    private int[][] matrizTablero = null;

    private BufferedImage imagen = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param x no puede ser menor a 1
     * @param y no puede ser menor a 1
     * @param z no puede ser menor a 1
     * @throws Exception cuando alguno de los parametros es menor a 1
     * post: inicializa el bitmap, con el alto, ancho, x, y, z, y las dimensiones
     */
    public Bitmap(int x, int y, int z) throws Exception {
        Validacion.validarSiNumeroEsMenorAUno(x, "X");
        Validacion.validarSiNumeroEsMenorAUno(x, "Y");
        Validacion.validarSiNumeroEsMenorAUno(x, "Z");

        this.alto = 500;
        this.ancho = 1000;

        this.x = x;
        this.y = y;
        this.z = z;
        this.dimensionTableroX = (this.ancho / this.z) - 10;
        this.dimensionCasilleroX = this.dimensionTableroX / this.x;

        this.dimensionTableroY = ((this.alto - 100) / 2);
        this.dimensionCasilleroY = this.dimensionTableroY / this.y;

        this.matrizCentral = new int[ancho][alto];
        this.matrizTablero = new int[dimensionTableroX][dimensionTableroY];

        this.imagen = new BufferedImage(this.ancho, this.alto, BufferedImage.TYPE_INT_RGB);
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * post: crea la imagen de los tableros del juego, y la exporta al archivo salida
     */
    public void crearImagen() {
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
    public void colorMatrizCentral() {
        for (int i = 0; i < this.ancho; i++) {
            for (int j = 0; j < this.alto; j++) {
                this.matrizCentral[i][j] = 7368816;
            }
        }
    }

    /**
     * pre: --
     * post: escribe la matriz central a la imagen
     */
    public void escribirImagen() {
        for (int i = 0; i < this.ancho; i++) {
            for (int j = 0; j < this.alto; j++) {
                this.imagen.setRGB(i, j, this.matrizCentral[i][j]);
            }
        }
    }

    /**
     * pre: --
     * post: colorea los casilleros de los tableros del juego
     */
    public void colorearMatrizTablero() {
        for (int i = 0; i < dimensionTableroX; i++) {
            for (int j = 0; j < dimensionTableroY; j++) {
                this.matrizTablero[i][j] = 4730480;
            }
        }
    }

    /**
     * pre: --
     * post: colorea las lineas de los tableros del juego
     */
    public void colorearLineasMatrizTablero() {
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < dimensionTableroY; j++) {
                this.matrizTablero[this.dimensionCasilleroX * i][j] = 16777215;
            }
        }
        for (int i = 0; i < this.y; i++) {
            for (int j = 0; j < dimensionTableroX; j++) {
                this.matrizTablero[j][this.dimensionCasilleroY * i] = 16777215;
            }
        }
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
    public void colocarFicha(int x, int y, int z, String rutaImagen) throws Exception {
        Validacion.validarSiNumeroEsMenorAUno(x, "X");
        Validacion.validarSiNumeroEsMenorAUno(x, "Y");
        Validacion.validarSiNumeroEsMenorAUno(x, "Z");
        Validacion.validarSiEsUnaCadenaVacia(rutaImagen, "Ruta de imagen");

        // Cargar la imagen desde el archivo
        BufferedImage imagenFicha = cargarImagen(rutaImagen);
        if (imagenFicha == null) {
            // Aca podriamos poner una imagen por defecto
//            throw new Exception("Hubo un error al intentar leer la imagen " + rutaImagen);
//            System.out.println("Hubo un error al intentar leer la imagen " + rutaImagen);
            return;
        }

        // Calcular las coordenadas donde se colocará la imagen
        int anchoImagen = imagenFicha.getWidth();
        int altoImagen = imagenFicha.getHeight();

        // Posición del casillero (izquierda superior) en el tablero
        int posX = (z - 1) * (this.dimensionTableroX + 10) + (x - 1) * dimensionCasilleroX;
        int posY = 100 + (dimensionCasilleroY) * (y - 1);

        // Calcular el desplazamiento para centrar la imagen
        int desplazamientoX = (dimensionCasilleroX - anchoImagen) / 2;
        int desplazamientoY = (dimensionCasilleroY - altoImagen) / 2;

        // Ajustar la posición de la imagen para que quede centrada
        posX += desplazamientoX;
        posY += desplazamientoY;

        // Asegurarse de que la imagen no se salga de los límites del tablero
        if (posX + anchoImagen > this.ancho || posY + altoImagen > this.alto) {
            throw new Exception("La imagen no cabe en el tablero.");
        }

        // Colocar los píxeles de la imagen en la matriz del tablero
        for (int i = 0; i < anchoImagen; i++) {
            for (int j = 0; j < altoImagen; j++) {
                int colorPixel = imagenFicha.getRGB(i, j);  // Obtener el color del píxel de la imagen
                int pixelX = posX + i;
                int pixelY = posY + j;

                // Asegurarse de que los píxeles estén dentro de los límites del tablero
                if (pixelX >= 0 && pixelX < this.ancho && pixelY >= 0 && pixelY < this.alto) {
                    this.imagen.setRGB(pixelX, pixelY, colorPixel);  // Colocar el píxel de la imagen en el tablero
                }
            }
        }
    }

    /**
     * pre:
     * @param rutaImagen no puede ser nulo ni vacio
     * @throws Exception si rutaImagen es nulo o vacio
     * post: lee la imagen en rutaImagen
     */
    public BufferedImage cargarImagen(String rutaImagen) throws Exception {
        Validacion.validarSiEsUnaCadenaVacia(rutaImagen, "Ruta de imagen");

        try {
            return ImageIO.read(new File(generarRutaRelativa(rutaImagen)));  // Cargar la imagen desde el archivo
        } catch (Exception e) {
            System.out.println("Hubo un error al intentar leer la imagen " + rutaImagen);
            e.printStackTrace();
            // FIXME: aca no tendria que tirar exception, en vez de retornar null???????, y luego en colocarFicha
            //        catcheamos la exception, y colocamos una ficha por defecto
            // throw new Exception("Hubo un error al intentar leer la imagen");
            return null;
        }
    }

    /**
     * pre: --
     * post: escribe en la imagen los tableros del juego
     */
    public void escribirTablero() {
        for (int z = 0; z < this.z; z++) {
            for (int i = 0; i < this.dimensionTableroX; i++) {
                for (int j = 0; j < this.dimensionTableroY; j++) {
                    this.imagen.setRGB(z * (this.dimensionTableroX + 10) + i, 100 + j, this.matrizTablero[i][j]);
                }
            }
        }
    }

    /**
     * pre: --
     * post: escribe la imagen del juego al archivo "salida1.bmp"
     */
    public void escribirArchivo() {
        try {
            File archivo = new File(generarRutaRelativa("salida1.bmp"));
            ImageIO.write(imagen, "bmp", archivo);
        } catch (Exception e) {
            System.out.println("Hubo un error al intentar escribir la imagen del juego al archivo salida1.bmp");
            e.printStackTrace();
        }
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre:
     * @param nombreDeArchivo no puede ser nulo ni vacio
     * @return una cadena del formato RUTA_RELATIVA_IMAGENES/nombreDeArchivo
     * @throws Exception si nombreDeArchivo es invalido
     */
    public String generarRutaRelativa(String nombreDeArchivo) throws Exception {
        Validacion.validarSiEsUnaCadenaVacia(nombreDeArchivo, "Nombre de archivo");
        return RUTA_RELATIVA_IMAGENES + nombreDeArchivo;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}

