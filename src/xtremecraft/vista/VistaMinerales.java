package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaMinerales extends Vista {
	
	static String nombreMarine = "Mineral";
	
	public VistaMinerales(){
		
		super(nombreMarine);
		this.nombre = nombreMarine;
		this.color = new Color(0,204,204);
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear recolector de mineral");
		
		return opciones;
	}

}
