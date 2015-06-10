package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.recursos.MinaDeMinerales;

public class RecolectorDeMineral extends Recolector{

	private MinaDeMinerales minaDeMinerales;
	private static int tiempoDeConstruccion = 4;

	protected RecolectorDeMineral(MinaDeMinerales unaMinaDeMinerales, Terreno terreno) {
		
		super(terreno);
		this.minaDeMinerales = unaMinaDeMinerales;
		this.tiempoConstruccion = tiempoDeConstruccion;
	
	}
	
	public void aumentarReservasEnTurno(){
		
		this.reservas += this.minaDeMinerales.explotar(this.aumentoDeReservaEnTurno);
		
	}

	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}

	
	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}

	public static RecolectorDeMineral nuevoRecolectorDeMineral(Terran razaTerran,MinaDeMinerales nodoMineral, Terreno terreno){
		
		RecolectorDeMineral nuevoRecolectorDeMineral= new RecolectorDeMineral(nodoMineral,terreno);
		razaTerran.agregarEdificioRecolectorDeMineral(nuevoRecolectorDeMineral);
		return nuevoRecolectorDeMineral;
		
	}
	

}
