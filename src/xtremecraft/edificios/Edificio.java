package xtremecraft.edificios;
import xtremecraft.raza.Raza;


abstract class Edificio {
	
	Raza razaCreadora;
	
	public Edificio(Raza unaRaza){
		
		razaCreadora=unaRaza;
		
	}

}
