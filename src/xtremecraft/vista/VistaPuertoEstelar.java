package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaPuertoEstelar extends VistaIdentificable implements IdentificableVisible{

	public VistaPuertoEstelar(){
		
		super();
		this.color = new Color(125,156,190);
	
	}
	
	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear espectro");
		opciones.add("Crear nave de transporte");
		opciones.add("Crear nave ciencia");
		
		return opciones;
	}

}
