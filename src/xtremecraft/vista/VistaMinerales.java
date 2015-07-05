package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaMinerales extends Vista {
	
	static String nombreMarine = "Mineral";
	
	public VistaMinerales(){
		
		super(nombreMarine);
		this.nombre = nombreMarine;
		this.color = new Color(0,204,204);
		
	}

}
