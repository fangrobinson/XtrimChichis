package xtremecraft.edificios;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;
import xtremecraft.unidades.Espectro;
import xtremecraft.unidades.NaveCiencia;
import xtremecraft.unidades.NaveTransporte;

public class PuertoEstelar extends Edificio{
	
	private static int vida = 100;
	private static int tiempoDeConstruccion = 10;
	
	protected PuertoEstelar(int fila, int columna){
		
		super(fila,columna,vida);
		this.tiempoConstruccion = tiempoDeConstruccion;
		
	}
	

	public static PuertoEstelar nuevoPuertoEstelar(Terran razaTerran,int fila,int columna) {
		
		if(razaTerran.tieneFabricas()){
			PuertoEstelar nuevoPuertoEstelar = new PuertoEstelar(fila,columna);
			razaTerran.agregarPuertoEstelar(nuevoPuertoEstelar);
			return nuevoPuertoEstelar;
		}
		
		throw new IllegalArgumentException("Para construir puertos estelares debes contar con al menos una fabrica");

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

	public boolean puedeUbicarseEnTierra() {
		
		return true;
		
	}

	public boolean puedeUbicarseEnAire() {
		
		return false;
		
	}



}
