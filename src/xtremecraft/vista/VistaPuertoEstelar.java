package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

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
	
	public ArrayList<String> devolverAcciones(){
		
		ArrayList<String> acciones = new ArrayList<String>();
		acciones.add("Crear nave transporte");
		acciones.add("Crear nave ciencia");
		acciones.add("Crear espectro");
		return acciones;
	}

}
