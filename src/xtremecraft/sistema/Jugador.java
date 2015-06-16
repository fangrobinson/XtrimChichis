package xtremecraft.sistema;

import xtremecraft.mapa.Terreno;
import xtremecraft.raza.Terran;

public class Jugador {
	String nombre;
	Terran nacion;
	
	public Jugador (String nombre, Terreno terreno) throws NombreMuyCortoException{
		if (nombre.length() < 4){
			throw new NombreMuyCortoException();
		}
		this.nombre = nombre;
		this.nacion = new Terran(terreno);
	}

	public boolean estaEnJuego(){
		return this.nacion.estaViva();
	}
	
}
