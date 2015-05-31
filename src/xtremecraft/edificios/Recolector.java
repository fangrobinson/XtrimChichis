package xtremecraft.edificios;

import xtremecraft.raza.Raza;

public class Recolector extends Edificio {
	
	private int reservas;

	public Recolector(Raza unaRaza){

		super(unaRaza);
		this.reservas=0;


	}

	public int getReservas() {
		
		return reservas;
	}

}
