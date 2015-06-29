package xtremecraft.vista;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VistaGas extends Vista{
	
	static String nombreGas = "Gas Vespeno";
	
	public VistaGas(String estadoInicialVisible, ArrayList<String> accionesDisponibles){
		
		super(nombreGas,estadoInicialVisible,accionesDisponibles);
		this.color = new Color(153,0,153);
		
	}

	@Override
	public ArrayList<String> mostrarOpcionesAccion() {
		
		ArrayList<String> opciones = new ArrayList<String>();
		opciones.add("Crear recolector de gas vespeno");
		
		return opciones;
		
	}
	
}
