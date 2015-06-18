package xtremecraft.sistema;

import xtremecraft.raza.Terran;
import xtremecraft.unidades.Ubicable;

public class Jugador {
	String nombre;
	Terran nacion;
	
	public Jugador (String nombre) throws NombreMuyCortoException{
		if (nombre.length() < 4){
			throw new NombreMuyCortoException();
		}
		this.nombre = nombre;
		this.nacion = new Terran();
	}

	public boolean estaEnJuego(){
		return this.nacion.estaViva();
	}
	
	public boolean esDeMiPropiedad(Ubicable ubicable){
		return this.nacion.esDeMiPropiedad(ubicable);
	}
	
	//TODO: implementar los metodos de creacion de unidades y edificios.
	/*
	public crearBarraca();
	
	public crearFabrica();
	
	public crearPuertoEstelar();
	
	public crearRecolectorDeGasVespeno();
	
	public crearRecolectorDeMineral();
	
	public crearMarine();
	
	public crearGoliat();
	
	public crearEspectro();
	
	public crearNaveCiencia();
	*/
}
