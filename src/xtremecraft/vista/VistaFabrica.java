package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaFabrica extends VistaIdentificable implements IdentificableVisible{
	
	static String nombreFabrica = "Fabrica";

	public VistaFabrica(ArrayList<Vista> vistasInferiores){
		
		super(nombreFabrica, vistasInferiores);
		this.color = new Color(173,156,121);
	
	}

}
