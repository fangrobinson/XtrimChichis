package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaPuertoEstelar extends VistaIdentificable implements IdentificableVisible{
	
	static String nombrePuerto = "Puerto Estelar";
	
	public VistaPuertoEstelar(){
		
		super(nombrePuerto);
		this.color = new Color(125,156,190);
	
	}

	@Override
	public void setNombre() {
		
		this.nombre = nombrePuerto;
		
	}
	
}
