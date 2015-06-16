package xtremecraft.sistema;

import xtremecraft.raza.Terran;

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
	
}
