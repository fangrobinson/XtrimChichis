package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaNaveCiencia extends VistaIdentificable {
	
	static String nombreNave = "Nave Ciencia";
	
	public VistaNaveCiencia(ArrayList<Vista> vistasInferiores){
		
		super(nombreNave, vistasInferiores);
		this.color = new Color(153,153,0);
	
	}

}
