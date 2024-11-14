


import java.awt.image.BufferedImage;
import java.io.File; 
import javax.imageio.ImageIO;
 
public class Bitmap {
    private int alto  ; 
    private int ancho ; 
    private int x;
    private int y;
    private int z; 

    private int dimencionCacillerox= 0;   
    private int dimencionTableroX= 0; 
	private int dimencionCacilleroY = 0;    
	private int dimencionTableroY = 0;

    private int[][] matrizCentral ;
    private int[][] matrizTablero;
    
    private BufferedImage imagen ;  

//------------------------------CONSTRUCTOR----------------------------------------
    /**
     * pre: 
     * @return 
     */
public Bitmap(int x ,int y, int z  ){ 
	this.alto = 500;
	this.ancho = 1000; 
	
	this.x =x ;
	this.y =y ;
	this.z = z;
	this.dimencionTableroX = (this.ancho/this.z)-10 ;
	this.dimencionCacillerox = this.dimencionTableroX/this.x;

	this.dimencionTableroY = (this.alto/2);
	this.dimencionCacilleroY = this.dimencionTableroY/this.y;
	  
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
	 escribirArcrivo();
	
}
//----------------------------------------------------------------------

/**
 * pre: 
 * @return 
 */ 
public void colorMatrizCentral() {
    for(int i = 0 ; i < this.ancho; i++) {
	   	for(int j = 0 ; j < this.alto ; j++) {   
	   		this.matrizCentral[i][j] = 1013760;
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
    this.matrizTablero = new int[this.dimencionTableroX][this.dimencionTableroX];   
    for(int i = 0 ; i <dimencionTableroX; i++) {
	   	for(int j = 0 ; j < dimencionTableroX ; j++) {     
	   		this.matrizTablero[i][j] =   0 ;   
	   	} 
    }
    }

	//----------------------------------------------------------------------
public void colorearLineasMatrizTablero() {
    for(int i = 0 ; i <   this.x; i++) { 
    	for(int j = 0 ; j < dimencionTableroX ; j++) {
			this.matrizTablero[this.dimencionCacillerox*i][j] = 16777215;
			} 
    	}
    for(int i = 0 ; i <  this.y ; i++) { 
    	for(int j = 0 ; j < dimencionTableroX ; j++) {
			this.matrizTablero[j][this.dimencionCacilleroY*i] = 16777215;
			} 
    	}
	}  
/**
 * pre: 
 * @return 
 */
// TODO:  
public void escribirTablero( ) {
		for(int z = 0 ; z < this.z ;z++){ 
			for(int i = 0; i < this.dimencionTableroX; i++) {
				for(int j = 0 ; j < this.dimencionTableroX ; j++) { 
					this.imagen.setRGB(z*(this.dimencionTableroX +10)+i ,  100+j , this.matrizTablero[i][j]  );
				}  
			} 
		} 
} 
/**
 * pre: 
 * @return 
 */
// TODO:  


public void escribirArcrivo() {
	try {
			File archivo = new File("salida.bmp");
			ImageIO.write(imagen,"bmp",archivo);
		}catch(Exception e){
			e.printStackTrace();
		}
		
}
}

	