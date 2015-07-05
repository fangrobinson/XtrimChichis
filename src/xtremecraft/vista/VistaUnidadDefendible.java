package xtremecraft.vista;

import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class VistaUnidadDefendible extends VistaIdentificable {
	
	public VistaUnidadDefendible(String nombre, ArrayList<Vista> vistasInferiores){
		
		super(nombre, vistasInferiores);
		
	}
	
}
