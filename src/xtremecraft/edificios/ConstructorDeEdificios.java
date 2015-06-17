package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Ubicable;

public abstract class ConstructorDeEdificios {
	
	
	public static Edificio nuevoEdificio(String tipoDeEdificio,Terran razaTerran, Terreno unTerreno){
		
		Edificio nuevaEdificacion = null;
	
		if( tipoDeEdificio == "Barraca" ){
			
			nuevaEdificacion = new Barraca(unTerreno);
			
		}
		
		if( tipoDeEdificio == "Fabrica" ){
			
			if(razaTerran.tieneBarracas()){
				nuevaEdificacion = new Fabrica(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
			}		
			
		}
		
		if( tipoDeEdificio == "Puerto Estelar" ){
			
			if(razaTerran.tieneFabricas()){
				nuevaEdificacion = new PuertoEstelar(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir una fabrica si no se contruyo al menos una Barraca.");
			}
			
		}
		
		if( tipoDeEdificio == "Recolector de Mineral" ){
			
			if(unTerreno.tieneMineral()){
				nuevaEdificacion = new RecolectorDeMineral(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir un recolector de mineral sobre un terreno sin mineral.");
			}
			
		}
		
		if( tipoDeEdificio == "Recolector de Gas Vespeno" ){
			
			if(unTerreno.tieneGasVespeno()){
				nuevaEdificacion = new RecolectorDeGasVespeno(unTerreno);
			}else{
				throw new IllegalArgumentException("No se puede contruir una refineria sobre un terreno sin gas vespeno.");
			}
			
		}
		
		if( tipoDeEdificio == "Deposito de Suministros" ){
			
			nuevaEdificacion = new DepositoDeSuministros(unTerreno);
			
		}

		ubicarEdificioEnTerreno(nuevaEdificacion, unTerreno);
		
		razaTerran.agregarEdificio(nuevaEdificacion);
		
		return nuevaEdificacion;
		
	}
	
	private static void ubicarEdificioEnTerreno(Edificio unEdificio, Terreno unTerreno){
		
		Ubicable nuevoEdificio = (Ubicable)unEdificio;
		
		if(!unTerreno.ubicar(nuevoEdificio)) throw new IllegalArgumentException("No se pudo crear el edificio");
		
	}
}
