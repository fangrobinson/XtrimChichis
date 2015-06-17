package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Ubicable;

public abstract class ConstructorDeEdificios {
	
	
	public static boolean nuevoEdificio(String tipoDeEdificio,Terran razaTerran, Terreno unTerreno){
		
		
		if( tipoDeEdificio == "Barraca" ){
			
			 Barraca nuevoEdificio = new Barraca(unTerreno);
			 razaTerran.agregarBarraca(nuevoEdificio);
		}
		
		if( tipoDeEdificio == "Fabrica" ){
			
			if(razaTerran.tieneBarracas()){
				Fabrica nuevoEdificio = new Fabrica(unTerreno);
				razaTerran.agregarFabrica(nuevoEdificio);
			}else{
				throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
			}		
			
		}
		
		if( tipoDeEdificio == "Puerto Estelar" ){
			
			if(razaTerran.tieneFabricas()){
				PuertoEstelar nuevoEdificio = new PuertoEstelar(unTerreno);
				razaTerran.agregarPuertoEstelar(nuevoEdificio);
			}else{
				throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
			}
			
		}
		
		if( tipoDeEdificio == "Recolector de Mineral" ){
			
			if(unTerreno.tieneMineral()){
				RecolectorDeMineral nuevoEdificio = new RecolectorDeMineral(unTerreno);
				razaTerran.agregarEdificioRecolector(nuevoEdificio);
			}else{
				throw new IllegalArgumentException("No se puede contruir una recolector de mineral sobre un terreno sin mineral.");
			}
			
		}
		
		if( tipoDeEdificio == "Recolector de Gas Vespeno" ){
			
			if(unTerreno.tieneGasVespeno()){
				RecolectorDeGasVespeno nuevoEdificio = new RecolectorDeGasVespeno(unTerreno);
				razaTerran.agregarEdificioRecolector(nuevoEdificio);
			}else{
				throw new IllegalArgumentException("No se puede contruir una refineria sobre un terreno sin gas vespeno.");
			}
			
		}
		
		if( tipoDeEdificio == "Deposito de Suministros" ){
			
			DepositoDeSuministros nuevoEdificio = new DepositoDeSuministros(unTerreno);
			razaTerran.agregarDepositoDeSuministros(nuevoEdificio);
		}
		else{
			int nuevoEdificio = 0;
			throw new IllegalArgumentException("El tipo de edificio que busca crear no existe");
		}
		
		Ubicable nuevoEdificio = (Ubicable)nuevoEdificio;
		if(unTerreno.ubicar(nuevoEdificio)){
			
			return true;
			
		}
		
		return false;
	
		
	}
}
