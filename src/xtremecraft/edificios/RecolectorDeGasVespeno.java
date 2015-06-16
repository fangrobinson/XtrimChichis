package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.recursos.Recurso;

public class RecolectorDeGasVespeno extends Recolector {
	
	private Recurso volcan;
	private static int tiempoDeConstruccion = 6;
	
	public RecolectorDeGasVespeno(Terreno terreno){
		
		super(terreno);
		this.volcan = terreno.getRecurso();
		this.tiempoConstruccion = tiempoDeConstruccion; 
		
	}

	public void pasarTiempo(){
		//revisar modelado de paso del tiempo
		if(this.tiempoDeConstruccionActual < this.tiempoConstruccion) this.tiempoDeConstruccionActual += 1;
		else this.reservas += this.volcan.explotar(this.aumentoDeReservaEnTurno);
		
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
