package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public abstract class ConstructorDeEdificios {
	
	
	public static boolean nuevoEdificio(String tipoDeEdificio,Terran razaTerran, Terreno unTerreno){
		
		@SuppressWarnings("unused")
		Edificio nuevoEdificio = null;
		
		if( tipoDeEdificio == "Barraca" ){
			
			 nuevoEdificio = new Barraca(unTerreno);
			
		}
		
		if( tipoDeEdificio == "Fabrica" ){
			
			if(razaTerran.tieneBarracas()){
				nuevoEdificio = new Fabrica(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
			}		
			
		}
		
		if( tipoDeEdificio == "Puerto Estelar" ){
			
			if(razaTerran.tieneFabricas()){
				nuevoEdificio = new PuertoEstelar(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
			}
			
		}
		
		if( tipoDeEdificio == "Recolector de Mineral" ){
			
			if(unTerreno.tieneMineral()){
				nuevoEdificio = new RecolectorDeMineral(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir una recolector de mineral sobre un terreno sin mineral.");
			}
			
		}
		
		if( tipoDeEdificio == "Recolector de Gas Vespeno" ){
			
			if(unTerreno.tieneGasVespeno()){
				nuevoEdificio = new RecolectorDeGasVespeno(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir una refineria sobre un terreno sin gas vespeno.");
			}
			
		}
		
		if( tipoDeEdificio == "Deposito de Suministros" ){
			
			nuevoEdificio = new DepositoDeSuministros(unTerreno);
			
		}
	
		/*if(unTerreno.ubicar(nuevoEdificio)){
			
			razaTerran.agregarEdificio(nuevoEdificio);
			return true;
			
		}*/
		
		return false;
	
		
	}
}
