package xtremecraft.vista;

import java.awt.Color;

@SuppressWarnings("serial")
public class VistaFabrica extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreFabrica = "Fabrica";

	public VistaFabrica(){
		
		super(nombreFabrica);
		this.color = new Color(173,156,121);
	
	}

}
