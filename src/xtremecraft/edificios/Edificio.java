package xtremecraft.edificios;
import xtremecraft.raza.Terran;


abstract class Edificio {
	

	public Edificio(){
		
		
	}
	
	
	public static CentroDeMineral nuevoRecolectorDeMineral(Terran razaTerran){
		
		CentroDeMineral nuevoCentroMineral= new CentroDeMineral();
		razaTerran.agregarCentroMineral(nuevoCentroMineral);
		return nuevoCentroMineral;
		
		
	}

}
