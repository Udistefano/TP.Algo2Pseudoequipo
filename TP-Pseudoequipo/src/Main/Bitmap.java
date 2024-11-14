package Main;


import java.awt.image.BufferedImage;
import java.io.File; 
import javax.imageio.ImageIO;

public class Bitmap {
	private int alto  ; 
	private int ancho ; 
	private int x;
	private int y;
	private int z; 

	private int dimensionCasilleroX= 0;   
	private int dimensionTableroX= 0; 
	private int dimensionCasilleroY = 0;    
	private int dimensionTableroY = 0; 

	private int[][] matrizCentral ;
	private int[][] matrizTablero;



	private BufferedImage imagen ;  

	//------------------------------CONSTRUCTOR----------------------------------------
	/**
	 * pre: 
	 * @return 
	 */
	public Bitmap(int x ,int y, int z ){ 
		this.alto = 500;
		this.ancho = 1000; 

		this.x =x ;
		this.y =y ;
		this.z = z;
		this.dimensionTableroX = (this.ancho/this.z)-10 ;
		this.dimensionCasilleroX = this.dimensionTableroX/this.x;

		this.dimensionTableroY = ((this.alto-100)/2);
		this.dimensionCasilleroY = this.dimensionTableroY/this.y;

		this.matrizCentral = new int[ancho][alto];  
		this.matrizTablero = new int[dimensionTableroX][dimensionTableroY]; 

		this.imagen = new BufferedImage(this.ancho, this.alto , BufferedImage.TYPE_INT_RGB); 
	}
	//----------------------------------------------------------------------
	/**
	 * pre: 
	 * @return º
	 */
	public void crearImagen(){ 
		colorMatrizCentral(); 
		escribirImagen();

		colorearMatrizTablero();
		colorearLineasMatrizTablero(); 
		escribirTablero(); 

	}
	//----------------------------------------------------------------------

	/**
	 * pre: 
	 * @return 
	 */ 
	public void colorMatrizCentral() {
		for(int i = 0 ; i < this.ancho; i++) {
			for(int j = 0 ; j < this.alto ; j++) {   
				this.matrizCentral[i][j] = Color.GRIS.getRGB();
			}
		} 
	}
	//----------------------------------------------------------------------
	/**
	 * pre: 
	 * @return 
	 */
	public void escribirImagen() {
		for(int i = 0; i < this.ancho; i++) {
			for(int j = 0 ; j < this.alto ; j++) {
				this.imagen.setRGB(i,j,this.matrizCentral[i][j]);
			}
		}
	}
	//----------------------------------------------------------------------
	public void colorearMatrizTablero(   ) { 
		for(int i = 0 ; i <dimensionTableroX; i++) {
			for(int j = 0 ; j < dimensionTableroY ; j++) {     
				this.matrizTablero[i][j] = Color.CASILLERO.getRGB() ;   
			} 
		}
	}

	//----------------------------------------------------------------------
	public void colorearLineasMatrizTablero() {
		for(int i = 0 ; i <  this.x ; i++) { 
			for(int j = 0 ; j < dimensionTableroY ; j++) {
				this.matrizTablero[this.dimensionCasilleroX*i][j] = Color.LINEAS.getRGB();
			} 
		}
		for(int i = 0 ; i <  this.y ; i++) { 
			for(int j = 0 ; j < dimensionTableroX  ; j++) {
				this.matrizTablero[j][this.dimensionCasilleroY*i] = Color.LINEAS.getRGB();
			} 
		}
	}  
	/**
	 * pre: 
	 * @return 
	 */ 
	public void colocarFicha(int x, int y, int z, String rutaImagen) {
	    // Cargar la imagen desde el archivo
	    BufferedImage imagenFicha = cargarImagen(rutaImagen);
	    if (imagenFicha == null) {
	        System.out.println("Error al cargar la imagen.");
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
	        System.out.println("La imagen no cabe en el tablero.");
	        return;
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
	
	public BufferedImage cargarImagen(String rutaImagen) {
	    try {
	        return ImageIO.read(new File(rutaImagen));  // Cargar la imagen desde el archivo
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * pre: 
	 * @return 
	 */ 
	public void escribirTablero( ) {
		for(int z = 0 ; z < this.z ;z++){ 
			for(int i = 0; i < this.dimensionTableroX; i++) {
				for(int j = 0 ; j < this.dimensionTableroY ; j++) { 
					this.imagen.setRGB(z*(this.dimensionTableroX +10)+i ,  100+j , this.matrizTablero[i][j]  );
				}  
			} 
		} 
	} 
	/**
	 * pre: 
	 * @return 
	 */ 
	public void escribirArchivo() {
		try {
			File archivo = new File("salida1.bmp");
			ImageIO.write(imagen,"bmp",archivo);
		}catch(Exception e){
			e.printStackTrace();
		} 
	}
	
}

