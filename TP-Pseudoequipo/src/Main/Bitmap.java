

 
import java.awt.image.BufferedImage;
import java.io.File; 
import javax.imageio.ImageIO;
 
public class Bitmap {
    private int alto  ; 
    private int ancho ; 
    private int x;
    private int y;
    private int z; 

    private int dimencionCasilleroX= 0;   
    private int dimencionTableroX= 0; 
	private int dimencionCasilleroY = 0;    
	private int dimencionTableroY = 0; 

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
	this.dimencionTableroX = (this.ancho/this.z)-10 ;
	this.dimencionCasilleroX = this.dimencionTableroX/this.x;

	this.dimencionTableroY = ((this.alto-100)/2);
	this.dimencionCasilleroY = this.dimencionTableroY/this.y;
	  
    this.matrizCentral = new int[ancho][alto];  
    this.matrizTablero = new int[dimencionTableroX][dimencionTableroY]; 
    
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
    for(int i = 0 ; i <dimencionTableroX; i++) {
	   	for(int j = 0 ; j < dimencionTableroY ; j++) {     
	   		this.matrizTablero[i][j] = Color.CASILLERO.getRGB() ;   
	   	} 
    }
    }

	//----------------------------------------------------------------------
public void colorearLineasMatrizTablero() {
    for(int i = 0 ; i <  this.x ; i++) { 
    	for(int j = 0 ; j < dimencionTableroY ; j++) {
			this.matrizTablero[this.dimencionCasilleroX*i][j] = Color.LINEAS.getRGB();
			} 
    	}
    for(int i = 0 ; i <  this.y ; i++) { 
    	for(int j = 0 ; j < dimencionTableroX  ; j++) {
			this.matrizTablero[j][this.dimencionCasilleroY*i] = Color.LINEAS.getRGB();
			} 
    	}
	}  
	/**
 * pre: 
 * @return 
 */ 
public void colocarFicha(int x,int y,int z , int color){ 
	for(int i = -1 ; i< 2 ; i++){ 
		this.imagen.setRGB((z-1)*(this.dimencionTableroX +10)+((x-1)*dimencionCasilleroX )+dimencionCasilleroX/2, (100+(dimencionCasilleroY)*(y-1))+i+dimencionCasilleroY/2, Color.ROJO.getRGB());
	}
}
/**
 * pre: 
 * @return 
 */ 
public void escribirTablero( ) {
		for(int z = 0 ; z < this.z ;z++){ 
			for(int i = 0; i < this.dimencionTableroX; i++) {
				for(int j = 0 ; j < this.dimencionTableroY ; j++) { 
					this.imagen.setRGB(z*(this.dimencionTableroX +10)+i ,  100+j , this.matrizTablero[i][j]  );
				}  
			} 
		} 
} 
/**
 * pre: 
 * @return 
 */ 
public void escribirArcrivo() {
	try {
			File archivo = new File("salida1.bmp");
			ImageIO.write(imagen,"bmp",archivo);
		}catch(Exception e){
			e.printStackTrace();
		} 
}
}

	