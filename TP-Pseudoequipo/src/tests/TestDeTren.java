//package tests;
//
//
//// Ejemplo en clase para usar Junit
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class TestDeTren {
//
//	/**
//	 *
//	 * @throws Exception
//	 */
//	@Test
//	public void TestDeTren() throws Exception{
//		Assertions.assertThrows(Exception.class, ()-> {
//		    new Tren(56);
//		}, "El constructor no tiro error");
//
//		Tren tren = new Tren(1);
//		Assertions.assertEquals(1, tren.getCantidadLocomotoras(), "El tren no tiene 1 locomotora");
//
//
//		Vagon vagon = new Vagon(500);
//		vagon.agregarCarga(456);
//		vagon.quitarCarga(56);
//
//		Assertions.assertThrows(Exception.class, ()-> {
//			vagon.quitarCarga(5676);
//		}, "El metodo no tiro error");
//
//
//		//Testeo
//		Assertions.assertEquals(100, vagon.getCargaRestante(), "Al vagon nole quedan 56 kilos");
//		Assertions.assertEquals(400, vagon.getCarga(), "El vagon tiene 400 kg de carga");
//
//
//		Vagon vagon2 = new Vagon(500);
//		Vagon vagon3 = new Vagon(500);
//
//		tren.agregarVagon(vagon2);
//		Assertions.assertEquals(1, tren.getCantidadDeVagones(), "El tren no tiene 1 vagon");
//
//		tren.agregarCarga(300);
//		tren.agregarVagon(vagon3);
//
//		Assertions.assertEquals(8, tren.getCantidadDeVagonesFaltantes(), "El tren no le quedan por poner 8 vagones");
//		Assertions.assertEquals(10, tren.getCantidadMaximaDeVagones(), "El tren no tiene 10 vagones maximo");
//		Assertions.assertEquals(300, tren.getCarga(), "El tren no tiene 300 kg de carga");
//		Assertions.assertEquals(700, tren.getCapacidadDeCargaRestante(), "El tren no tiene 700 kg restantes");
//	}
//
//
//
//	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//	//ATRIBUTOS -----------------------------------------------------------------------------------------------
//	//CONSTRUCTORES -------------------------------------------------------------------------------------------
//	//METODOS DE CLASE ----------------------------------------------------------------------------------------
//	//METODOS GENERALES ---------------------------------------------------------------------------------------
//	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//
//}
