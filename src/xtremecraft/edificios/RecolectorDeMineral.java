package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.recursos.Recurso;

public class RecolectorDeMineral extends Recolector{

	private Recurso minaDeMinerales;
	private static int tiempoDeConstruccion = 4;

	public RecolectorDeMineral(Terreno terreno) {
		
		super(terreno);
		this.minaDeMinerales = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion;
	
	}
	
	public void pasarTiempo(){
		//revisar modelado de paso del tiempo
		if(this.estaEnConstruccion()) this.tiempoDeConstruccionActual += 1;
		else this.reservas += this.minaDeMinerales.explotar(this.aumentoDeReservaEnTurno);
		
	}

	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}

	
	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}
	
	public boolean puedeUbicarseSobreRecursoNatural() {
	
		return true;
		
	}
	

}
