package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

public class VistaAire extends Vista{
	
	private static final long serialVersionUID = 1L;

	static String nombreAire = "Terreno Aire";
	
	public VistaAire(){
		
		super(nombreAire);
		this.color = new Color(216, 255, 249);
		
	}


	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		return null;
	}

}
