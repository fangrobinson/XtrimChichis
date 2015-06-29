package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaMinerales extends Vista {
	
	public VistaMinerales(){
		
		super();
		this.color = new Color(0,204,204);
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear recolector de mineral");
		
		return opciones;
	}	

}
