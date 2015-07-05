package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaMinerales extends Vista {
	
	static String nombreMarine = "Mineral";
	
	public VistaMinerales(ArrayList<Vista> vistasInferiores){
		
		super(nombreMarine, vistasInferiores);
		this.nombre = nombreMarine;
		this.color = new Color(0,204,204);
		
	}

}
