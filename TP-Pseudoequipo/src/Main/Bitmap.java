package Main;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

public class Bitmap {
	public class Bitmap {
		
		public static void main(String[] arg) { 
			
	    int alto = 1000; 
	    int[] pixelData = {255, 0, 0};
	    int altoTablero = 300;
		    int rd = 63;
		    int gd = 196;
		 	int bd= 63;
			
	 	int color = ( rd << 16) | ( gd <<8 )|  bd ; //"<<" es desplazamiento de bit
	 	
	 	
	    int colorVerde = color;
	    //--------------------------------
	    int[][] matriz = new int[alto][alto];
	    
	    int[][] matrizTablero = new int[altoTablero][altoTablero]; 
	    //--------------------------------
	    
	    for(int i = 0 ; i < alto; i++) {
	   	for(int j = 0 ; j < alto ; j++) {
	  	    int r = (i * 46)/alto;
	   	    int g = (j * 46)/alto;
	   	 	int b = 111;
	   		
		 	int rgb = (r << 16) | ( g <<8 )| b;//"<<" es desplazamiento de bit
		 	matriz[i][j] = rgb;

	   		}
	   	}
	    //--------------------------------
	    for(int i = 0 ; i < altoTablero; i++) {
		   	for(int j = 0 ; j < altoTablero ; j++) {
		  	    int r = 63;
		   	    int g = 196;
		   	 	int b = 63;
		   		
			 	int rgb = (r << 16) | ( g <<8 )| b; //"<<" es desplazamiento de bit
			 	matrizTablero[i][j] = rgb;
		
		   		}
		   	}
	    //--------------------------------
			BufferedImage imagen = new BufferedImage(alto, alto , BufferedImage.TYPE_INT_RGB);
			
			WritableRaster  tablero = imagen.getRaster();
		    //--------------------------------
			for(int i = 0; i < alto; i++) {
				for(int j = 0 ; j < alto ; j++) {
					imagen.setRGB(i,j,matriz[i][j]);
				}
			}
			
			for(int z = 0 ; z < 3 ;z++){
				
			for(int i = 0; i < altoTablero; i++) {
				for(int j = 0 ; j < altoTablero ; j++) {
					tablero.setPixel(z*(300+10)+i,  j,pixelData );
				}  
			} 
		}
			
			try {
				File archivo = new File("salida5.bmp");
				ImageIO.write(imagen,"bmp",archivo);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}

}
