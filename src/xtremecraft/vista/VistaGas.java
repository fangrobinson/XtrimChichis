package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaGas extends Vista{
	
	public VistaGas(){
		
		this.color = new Color(153,0,153);
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear recolector de gas vespeno");
		
		return opciones;
		
	}

	
}
