package xtremecraft.unidades;

import xtremecraft.edificios.Edificio;

public class Danio implements Ataque{
	int aire;
	int tierra;

	public Danio(int danioAire, int danioTierra){
		this.aire = danioAire;
		this.tierra = danioTierra;
	}
	
	private int getDanio(boolean estaElevado){
		if(estaElevado){
			return this.aire;
		}
		return this.tierra;
	}

	@Override
	public void afectar(Unidad unidad) {

		unidad.recibirAtaqueFisico(this.getDanio(unidad.estaElevado()));
		
	}
	
	@Override
	public void afectar(Edificio edificio){
		
		int danio = this.getDanio(edificio.estaElevado());
		
		if(edificio.estaEnConstruccion()){
			
			int turnosARestar = danio % (edificio.vidaMax()/edificio.tiempoConstruccion());
			edificio.restarTurnosConstruccion(turnosARestar);
    		
		}
		
		else{
			edificio.recibirAtaqueFisico(danio);
		}
    	
	}
	
}
