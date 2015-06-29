package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaMarine extends VistaUnidadDefendible{
	
	static String nombreMarine = "Marine";
	
	public VistaMarine(){
		
		super(nombreMarine);
		this.color = new Color(255,102,0);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreMarine;
		
	}

}
