package xtremecraft.edificios;

import xtremecraft.interfaces.Atacable;
import xtremecraft.interfaces.Ubicable;
import xtremecraft.mapa.Coordenada;
import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.Goliat;
import xtremecraft.unidades.Marine;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelar implements CreadorDeUnidadesTerrestres, Ubicable, Atacable{
	
	Fabrica fabricaBase;
	
	protected PuertoEstelar(Fabrica unaFabrica){
		
		this.fabricaBase = unaFabrica;
		
	}
	

	public static PuertoEstelar nuevoPuertoEstelar(Terran unTerran,Fabrica unaFabrica) {
		
		PuertoEstelar nuevoPuertoEstelar = new PuertoEstelar(unaFabrica);
		unTerran.agregarPuertoEstelar(nuevoPuertoEstelar);
		return nuevoPuertoEstelar;

	}
	
	public int getVida() {
		
		return this.fabricaBase.getVida();
		
	}

	public void recibirDanio(int valorDanio) {
		
		this.fabricaBase.recibirDanio(valorDanio);
		
	}
	
	public Coordenada getUbicacionActual(){
		
		return this.fabricaBase.getUbicacionActual();
		
	}

	public Marine entrenarMarine(Terreno unTerreno){
		
		return this.fabricaBase.entrenarMarine(unTerreno);
		
	}

	public Goliat entrenarGoliat(Terreno unTerreno){
		
		return this.fabricaBase.entrenarGoliat(unTerreno);
		
	}
	
	public Espectro crearEspectro(Terreno unTerreno){
		
		return new Espectro(unTerreno);
		
	}
	

	public NaveCiencia crearNaveCiencia(Terreno unTerreno){
		
		return new NaveCiencia(unTerreno);
		
	}
	

	public NaveTransporte crearNaveTransporte(Terreno unTerreno){
		
		return new NaveTransporte(unTerreno);
		
	}


	public void actualizarUbicacion(Terreno terreno) {
		
		this.fabricaBase.actualizarUbicacion(terreno);
		
	}


	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}


	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}


	public Fabrica getFabricaBase() {
		
		return this.fabricaBase;
		
	}


}
