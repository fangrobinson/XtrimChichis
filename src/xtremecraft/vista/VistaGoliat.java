package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaGoliat extends VistaIdentificable{
	
	static String nombreGoliat = "Goliat";
	
	public VistaGoliat(){
		
		super(nombreGoliat);
		this.color = new Color(0,102,51);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombreGoliat;
		
	}
		
}
