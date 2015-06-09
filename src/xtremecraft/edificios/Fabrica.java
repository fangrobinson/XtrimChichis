package xtremecraft.edificios;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;

public class Fabrica implements CreadorDeMarines,Ubicable,Atacable{
	
	Barraca barracaBase;

	protected Fabrica(Barraca unaBarraca){
		
		this.barracaBase = unaBarraca;

	}
	
	public static Fabrica nuevaFabrica(Barraca unaBarraca){
		
		//AGREGAR A LAS REFERENCIAS DE LA RAZA TERRAN.
		return new Fabrica(unaBarraca);
		
	}

	public int getVida(){
		
		return this.barracaBase.getVida();
		
	}

	public void recibirDanio(int valorDanio){
		
		this.barracaBase.recibirDanio(valorDanio);
		
	}


	public Marine entrenarMarine(Terreno unTerreno){
		
		return this.barracaBase.entrenarMarine(unTerreno);
		
	}
	
	public Goliat entrenarGoliat(Terreno unTerreno){
		
		return new Goliat(unTerreno);
		
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.barracaBase.getUbicacionActual();
		
	}

	@Override
	public void actualizarUbicacion(int columna, int fila) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean puedeUbicarseEnTierra() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean puedeUbicarseEnAire() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
